package me.jcotton42.alloyance.machine.crusher

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.machine.ExtractOnlyItemHandler
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import me.jcotton42.alloyance.registration.AlloyanceDataMaps
import me.jcotton42.alloyance.registration.AlloyanceRecipes
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.HolderLookup
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
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
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.neoforged.neoforge.items.IItemHandler
import net.neoforged.neoforge.items.ItemStackHandler
import net.neoforged.neoforge.items.wrapper.CombinedInvWrapper
import net.neoforged.neoforge.items.wrapper.RangedWrapper
import kotlin.math.max

class CrusherBlockEntity(
    pos: BlockPos,
    state: BlockState
): BlockEntity(
    AlloyanceBlocks.CRUSHER_BLOCK_ENTITY.get(),
    pos,
    state
), MenuProvider {
    companion object {
        const val NAME_KEY = "menu.title.${Alloyance.ID}.crusher"
        const val INVENTORY_TAG = "Inventory"

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

    // TODO load/saveAdditional, item components
    private var crushingTime = 0
    private var totalCrushingTime = 0
    private var burnTimeRemaining = 0
    private var totalBurnTime = 0
    private var crushProgressPerTick = 0

    val inventory = object: ItemStackHandler(5) {
        override fun onContentsChanged(slot: Int) {
            // TODO maybe add a flag here to suppress updates when doing serverTick stuff, to reduce packets
            setChanged()
        }

        override fun isItemValid(slot: Int, stack: ItemStack): Boolean = when (slot) {
            FUEL_SLOT -> stack.getBurnTime(RecipeType.SMELTING) > 0 || stack.`is`(Items.BUCKET)
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
            else -> TODO("Missing data index $index")
        }

        override fun set(index: Int, value: Int) = when (index) {
            CRUSHING_TIME_INDEX -> crushingTime = value
            TOTAL_CRUSHING_TIME_INDEX -> totalCrushingTime = value
            BURN_TIME_REMAINING_INDEX -> burnTimeRemaining = value
            TOTAL_BURN_TIME_INDEX -> totalBurnTime = value
            else -> TODO("Missing data index $index")
        }

        override fun getCount(): Int = DATA_SLOT_COUNT
    }

    private val quickCheck = RecipeManager.createCheck(AlloyanceRecipes.CRUSHER_TYPE.get())

    fun tickSerer(level: Level, pos: BlockPos, state: BlockState) {
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
            crushProgressPerTick = fuelStack.itemHolder.getData(AlloyanceDataMaps.FUEL_SPEED)?.speed ?: 1
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
                crushingTime += crushProgressPerTick
            }
            if (crushingTime >= totalCrushingTime) {
                tryCrush(inputStack, level, simulate = false)
                crushingTime = 0
            }
        } else {
            crushingTime = 0
        }

        updateLitState(wasBurning, isBurning(), level, pos, state)
    }

    private fun isBurning(): Boolean = burnTimeRemaining > 0

    private fun tryCrush(inputStack: ItemStack, level: Level, simulate: Boolean): Boolean {
        val input = SingleRecipeInput(inputStack)
        val recipe = quickCheck.getRecipeFor(input, level).orElse(null)?.value
            ?: return false
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
        // TODO if the input stack doesn't properly clear, this is likely the culprit, might need setChanged on all the shrinks
        inputStack.shrink(1)
        return true
    }

    private fun updateLitState(wasBurning: Boolean, isBurning: Boolean, level: Level, pos: BlockPos, state: BlockState) {
        if (isBurning != wasBurning) {
            val newState = state.setValue(CrusherBlock.LIT, isBurning)
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
        tag.put(INVENTORY_TAG, inventory.serializeNBT(registries))
    }

    override fun loadAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        super.loadAdditional(tag, registries)
        inventory.deserializeNBT(registries, tag.getCompound(INVENTORY_TAG))
    }

    override fun createMenu(containerId: Int, playerInventory: Inventory, player: Player): AbstractContainerMenu {
        return CrusherMenu(containerId, playerInventory, all, containerData, blockPos)
    }

    override fun getDisplayName(): Component {
        return Component.translatable(NAME_KEY)
    }
}