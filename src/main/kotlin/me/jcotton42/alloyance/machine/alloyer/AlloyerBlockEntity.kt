package me.jcotton42.alloyance.machine.alloyer

import me.jcotton42.alloyance.machine.BaseMachineBlock
import me.jcotton42.alloyance.machine.BaseMachineBlockEntity
import me.jcotton42.alloyance.machine.DualRecipeInput
import me.jcotton42.alloyance.machine.ExtractOnlyItemHandler
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import me.jcotton42.alloyance.registration.AlloyanceDataMaps
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
import net.minecraft.world.item.Items
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

class AlloyerBlockEntity(
    pos: BlockPos,
    state: BlockState
): BaseMachineBlockEntity(
    AlloyanceBlocks.ALLOYER_BLOCK_ENTITY.get(),
    pos,
    state
), MenuProvider {
    companion object {
        // fuel burn times are scaled to this
        const val NOMINAL_ALLOYING_TIME = 140
        const val SLOT_COUNT = 4
        const val INPUT_SLOT_1 = 0
        const val INPUT_SLOT_2 = 1
        const val FUEL_SLOT = 2
        const val OUTPUT_SLOT = 3

        const val ALLOYING_TIME_INDEX = 0
        const val TOTAL_ALLOYING_TIME_INDEX = 1
        const val BURN_TIME_REMAINING_INDEX = 2
        const val TOTAL_BURN_TIME_INDEX = 3
        const val DATA_SLOT_COUNT = 4
    }

    private var alloyingTime: Int = 0
    private var totalAlloyingTime: Int = 0
    private var burnTimeRemaining: Int = 0
    private var totalBurnTime: Int = 0
    private var alloyProgressPerTick: Int = 0
    private var ambienceTimer: Int = 0

    override val defaultName: Component = Component.translatable(AlloyanceBlocks.ALLOYER.id.toLanguageKey("block"))

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

    private val inputs = RangedWrapper(inventory, INPUT_SLOT_1, FUEL_SLOT)
    private val fuel = RangedWrapper(inventory, FUEL_SLOT, OUTPUT_SLOT)
    private val output = ExtractOnlyItemHandler(RangedWrapper(inventory, OUTPUT_SLOT, OUTPUT_SLOT + 1))
    private val all = CombinedInvWrapper(inputs, fuel, output)

    private val containerData = object: ContainerData {
        override fun get(index: Int): Int = when (index) {
            ALLOYING_TIME_INDEX -> alloyingTime
            TOTAL_ALLOYING_TIME_INDEX -> totalAlloyingTime
            BURN_TIME_REMAINING_INDEX -> burnTimeRemaining
            TOTAL_BURN_TIME_INDEX -> totalBurnTime
            else -> throw NotImplementedError("Missing data index in alloyer: $index")
        }

        override fun set(index: Int, value: Int) = when (index) {
            ALLOYING_TIME_INDEX -> alloyingTime = value
            TOTAL_ALLOYING_TIME_INDEX -> totalAlloyingTime = value
            BURN_TIME_REMAINING_INDEX -> burnTimeRemaining = value
            TOTAL_BURN_TIME_INDEX -> totalBurnTime = value
            else -> throw NotImplementedError("Missing data index in alloyer: $index")
        }

        override fun getCount(): Int = DATA_SLOT_COUNT
    }

    private val quickCheck = RecipeManager.createCheck(AlloyanceRecipes.ALLOYER_TYPE.get())

    override fun tickServer(level: Level, pos: BlockPos, state: BlockState) {
        val input1Stack = inventory.getStackInSlot(INPUT_SLOT_1)
        val input2Stack = inventory.getStackInSlot(INPUT_SLOT_2)
        val fuelStack = inventory.getStackInSlot(FUEL_SLOT)

        val wasBurning = isBurning()
        burnTimeRemaining = max(burnTimeRemaining - 1, 0)
        if (input1Stack.isEmpty || input2Stack.isEmpty) {
            alloyingTime = 0
            updateLitState(wasBurning, isBurning(), level, pos, state)
            return
        }

        val canAlloy = tryAlloy(input1Stack, input2Stack, level, simulate = true)
        if (!wasBurning && !fuelStack.isEmpty && canAlloy) {
            alloyProgressPerTick = fuelStack.itemHolder.getData(AlloyanceDataMaps.FUEL_SPEED)?.speed ?: 1
            totalBurnTime = fuelStack.getBurnTime(RecipeType.SMELTING) * NOMINAL_ALLOYING_TIME / 200
            burnTimeRemaining = totalBurnTime
            if (fuelStack.hasCraftingRemainingItem()) {
                inventory.setStackInSlot(FUEL_SLOT, fuelStack.craftingRemainingItem)
            } else {
                fuelStack.shrink(1)
            }
        }

        if (canAlloy) {
            if (isBurning()) {
                if (ambienceTimer == 0) {
                    level.playSound(null, pos, AlloyanceSounds.ALLOYER_WINDUP.get(), SoundSource.BLOCKS)
                }

                ambienceTimer++

                // after 4.5 seconds or every 18.5 seconds from the offset start
                if (ambienceTimer == 90 || ambienceTimer % 370 == 90) {
                    level.playSound(null, pos, AlloyanceSounds.ALLOYER_AMBIENCE.get(), SoundSource.BLOCKS)
                }

                alloyingTime += alloyProgressPerTick
            }
            if (alloyingTime >= totalAlloyingTime) {
                tryAlloy(input1Stack, input2Stack, level, simulate = false)
                level.playSound(null, pos, AlloyanceSounds.ALLOYER_IMPACT.get(), SoundSource.BLOCKS)
                alloyingTime = 0
            }
        } else {
            alloyingTime = 0
            ambienceTimer = 0
        }

        updateLitState(wasBurning, isBurning(), level, pos, state)
    }

    private fun isBurning(): Boolean = burnTimeRemaining > 0

    private fun tryAlloy(input1Stack: ItemStack, input2Stack: ItemStack, level: Level, simulate: Boolean): Boolean {
        val input = DualRecipeInput(input1Stack, input2Stack)
        val recipeHolder = quickCheck.getRecipeFor(input, level).orElse(null)
            ?: return false
        val recipe = recipeHolder.value
        totalAlloyingTime = recipe.alloyingTime
        var result = recipe.assemble(input, level.registryAccess())
        val actualResult = result.copy()
        result = inventory.insertItem(OUTPUT_SLOT, result, true)

        if (simulate) return result.isEmpty
        if (!result.isEmpty) return false

        inventory.insertItem(OUTPUT_SLOT, actualResult, false)

        if (recipe.ingredient1.test(input1Stack)) {
            input1Stack.shrink(recipe.ingredient1.count())
            input2Stack.shrink(recipe.ingredient2.count())
        } else {
            input1Stack.shrink(recipe.ingredient2.count())
            input2Stack.shrink(recipe.ingredient1.count())
        }

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
        Direction.UP -> inputs
        Direction.DOWN -> output
        null -> all
        else -> fuel
    }

    override fun saveAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        super.saveAdditional(tag, registries)
        tag.putInt("alloying_time", alloyingTime)
        tag.putInt("total_alloying_time", totalAlloyingTime)
        tag.putInt("burn_time_remaining", burnTimeRemaining)
        tag.putInt("total_burn_time", totalBurnTime)
        tag.putInt("alloy_progress_per_tick", alloyProgressPerTick)
        tag.putInt("ambience_timer", ambienceTimer)
    }

    override fun loadAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        super.loadAdditional(tag, registries)

        alloyingTime = tag.getInt("alloying_time")
        totalAlloyingTime = tag.getInt("total_alloying_time")
        burnTimeRemaining = tag.getInt("burn_time_remaining")
        totalBurnTime = tag.getInt("total_burn_time")
        alloyProgressPerTick = tag.getInt("alloy_progress_per_tick")
        ambienceTimer = tag.getInt("ambience_timer")
    }

    override fun createMenu(containerId: Int, playerInventory: Inventory, player: Player): AbstractContainerMenu? {
        return if(canUnlock(player, lockKey, displayName)) {
            AlloyerMenu(containerId, playerInventory, all, containerData, ::awardUsedRecipesAndPopExperience, blockPos)
        } else {
            null
        }
    }
}
