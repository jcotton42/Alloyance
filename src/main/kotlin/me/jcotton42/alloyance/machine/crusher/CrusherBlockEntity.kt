package me.jcotton42.alloyance.machine.crusher

import me.jcotton42.alloyance.machine.BaseMachineBlock
import me.jcotton42.alloyance.machine.BaseMachineBlockEntity
import me.jcotton42.alloyance.machine.ExtractOnlyItemHandler
import me.jcotton42.alloyance.machine.getFuelSpeed
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import me.jcotton42.alloyance.registration.AlloyanceRecipes
import me.jcotton42.alloyance.registration.AlloyanceSounds
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.HolderLookup
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.sounds.SoundSource
import net.minecraft.world.MenuProvider
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerData
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.RecipeManager
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.item.crafting.SingleRecipeInput
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.neoforged.neoforge.items.IItemHandler
import net.neoforged.neoforge.items.ItemStackHandler
import net.neoforged.neoforge.items.wrapper.CombinedInvWrapper
import net.neoforged.neoforge.items.wrapper.RangedWrapper
import kotlin.math.max

class CrusherBlockEntity(
    pos: BlockPos,
    state: BlockState
): BaseMachineBlockEntity(
    AlloyanceBlocks.CRUSHER_BLOCK_ENTITY.get(),
    pos,
    state
), MenuProvider {
    companion object {
        // fuel burn times are scaled to this
        const val NOMINAL_CRUSHING_TIME = 140
        const val SLOT_COUNT = 5
        const val INPUT_SLOT = 0
        const val FUEL_SLOT = 1
        const val OUTPUT_SLOT_1 = 2
        const val OUTPUT_SLOT_2 = 3
        const val OUTPUT_SLOT_3 = 4

        const val CRUSHING_TIME_INDEX = 0
        const val TOTAL_CRUSHING_TIME_INDEX = 1
        const val BURN_TIME_REMAINING_INDEX = 2
        const val TOTAL_BURN_TIME_INDEX = 3
        const val DATA_SLOT_COUNT = 4
    }

    private var crushingTime: Int = 0
    private var totalCrushingTime: Int = 0
    private var burnTimeRemaining: Int = 0
    private var totalBurnTime: Int = 0
    private var crushProgressPerTick: Int = 0
    private var ambienceTimer: Int = 0

    override val defaultName: Component = Component.translatable(AlloyanceBlocks.CRUSHER.id.toLanguageKey("block"))

    override val inventory = object: ItemStackHandler(SLOT_COUNT) {
        override fun onContentsChanged(slot: Int) {
            // TODO maybe add a flag here to suppress updates when doing serverTick stuff, to reduce packets
            setChanged()
        }

        override fun isItemValid(slot: Int, stack: ItemStack): Boolean = when (slot) {
            FUEL_SLOT -> stack.getBurnTime(RecipeType.SMELTING) > 0
            else -> true
        }
    }

    private val input = RangedWrapper(inventory, INPUT_SLOT, FUEL_SLOT)
    private val fuel = RangedWrapper(inventory, FUEL_SLOT, OUTPUT_SLOT_1)
    private val outputs = ExtractOnlyItemHandler(RangedWrapper(inventory, OUTPUT_SLOT_1, OUTPUT_SLOT_3 + 1))
    private val all = CombinedInvWrapper(input, fuel, outputs)

    private val containerData = object: ContainerData {
        override fun get(index: Int): Int = when (index) {
            CRUSHING_TIME_INDEX -> crushingTime
            TOTAL_CRUSHING_TIME_INDEX -> totalCrushingTime
            BURN_TIME_REMAINING_INDEX -> burnTimeRemaining
            TOTAL_BURN_TIME_INDEX -> totalBurnTime
            else -> throw NotImplementedError("Missing data index in crusher: $index")
        }

        override fun set(index: Int, value: Int) = when (index) {
            CRUSHING_TIME_INDEX -> crushingTime = value
            TOTAL_CRUSHING_TIME_INDEX -> totalCrushingTime = value
            BURN_TIME_REMAINING_INDEX -> burnTimeRemaining = value
            TOTAL_BURN_TIME_INDEX -> totalBurnTime = value
            else -> throw NotImplementedError("Missing data index in crusher: $index")
        }

        override fun getCount(): Int = DATA_SLOT_COUNT
    }

    private val quickCheck = RecipeManager.createCheck(AlloyanceRecipes.CRUSHER_TYPE.get())

    override fun tickServer(level: Level, pos: BlockPos, state: BlockState) {
        val inputStack = inventory.getStackInSlot(INPUT_SLOT)
        val fuelStack = inventory.getStackInSlot(FUEL_SLOT)

        val wasBurning = isBurning()
        burnTimeRemaining = max(burnTimeRemaining - 1, 0)
        if (inputStack.isEmpty) {
            crushingTime = 0
            updateLitState(wasBurning, isBurning(), level, pos, state)
            return
        }

        val canCrush = tryCrush(inputStack, level, simulate = true)
        if (!wasBurning && !fuelStack.isEmpty && canCrush) {
            crushProgressPerTick = fuelStack.getFuelSpeed() ?: 1
            totalBurnTime = fuelStack.getBurnTime(RecipeType.SMELTING) * NOMINAL_CRUSHING_TIME / 200
            burnTimeRemaining = totalBurnTime
            if (fuelStack.hasCraftingRemainingItem()) {
                inventory.setStackInSlot(FUEL_SLOT, fuelStack.craftingRemainingItem)
            } else {
                fuelStack.shrink(1)
            }
        }

        if (canCrush) {
            if (isBurning()) {
                if (ambienceTimer == 0) {
                    level.playSound(null, pos, AlloyanceSounds.CRUSHER_WINDUP.get(), SoundSource.BLOCKS)
                }

                ambienceTimer++

                // after 6 seconds or every 16.5 seconds from the offset start
                if (ambienceTimer == 120 || ambienceTimer % 330 == 120) {
                    level.playSound(null, pos, AlloyanceSounds.CRUSHER_AMBIENCE.get(), SoundSource.BLOCKS)
                }

                crushingTime += crushProgressPerTick
            }
            if (crushingTime >= totalCrushingTime) {
                tryCrush(inputStack, level, simulate = false)
                level.playSound(null, pos, AlloyanceSounds.CRUSHER_IMPACT.get(), SoundSource.BLOCKS)
                crushingTime = 0
            }
        } else {
            crushingTime = 0
            ambienceTimer = 0
        }

        updateLitState(wasBurning, isBurning(), level, pos, state)
    }

    private fun isBurning(): Boolean = burnTimeRemaining > 0

    private fun tryCrush(inputStack: ItemStack, level: Level, simulate: Boolean): Boolean {
        val input = SingleRecipeInput(inputStack)
        val recipeHolder = quickCheck.getRecipeFor(input, level).orElse(null)
            ?: return false
        val recipe = recipeHolder.value
        totalCrushingTime = recipe.crushingTime
        var result = recipe.assemble(input, level.registryAccess())
        var actualResult = result.copy()
        for (slot in OUTPUT_SLOT_1..OUTPUT_SLOT_3) {
            result = inventory.insertItem(slot, result, true)
            if (result.isEmpty) break
        }

        if (simulate) return result.isEmpty
        if (!result.isEmpty) return false

        for (slot in OUTPUT_SLOT_1..OUTPUT_SLOT_3) {
            actualResult = inventory.insertItem(slot, actualResult, false)
            if (actualResult.isEmpty) break
        }

        inputStack.shrink(1)
        recipesUsed.addTo(recipeHolder.id, 1)
        return true
    }

    private fun updateLitState(wasBurning: Boolean, isBurning: Boolean, level: Level, pos: BlockPos, state: BlockState) {
        if (isBurning != wasBurning) {
            val newState = state.setValue(BaseMachineBlock.LIT, isBurning)
            level.setBlockAndUpdate(pos, newState)
        }
    }

    fun getItemHandler(side: Direction?): IItemHandler = when (side) {
        Direction.UP -> input
        Direction.DOWN -> outputs
        null -> all
        else -> fuel
    }

    override fun saveAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        super.saveAdditional(tag, registries)
        tag.putInt("crushing_time", crushingTime)
        tag.putInt("total_crushing_time", totalCrushingTime)
        tag.putInt("burn_time_remaining", burnTimeRemaining)
        tag.putInt("total_burn_time", totalBurnTime)
        tag.putInt("crush_progress_per_tick", crushProgressPerTick)
        tag.putInt("ambience_timer", ambienceTimer)
    }

    override fun loadAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        super.loadAdditional(tag, registries)

        crushingTime = tag.getInt("crushing_time")
        totalCrushingTime = tag.getInt("total_crushing_time")
        burnTimeRemaining = tag.getInt("burn_time_remaining")
        totalBurnTime = tag.getInt("total_burn_time")
        crushProgressPerTick = tag.getInt("crush_progress_per_tick")
        ambienceTimer = tag.getInt("ambience_timer")
    }

    override fun createMenu(containerId: Int, playerInventory: Inventory, player: Player): AbstractContainerMenu? {
        return if(canUnlock(player, lockKey, displayName)) {
            CrusherMenu(containerId, playerInventory, all, containerData, ::awardUsedRecipesAndPopExperience, blockPos)
        } else {
            null
        }
    }
}
