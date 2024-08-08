package me.jcotton42.alloyance.machine.crusher

import me.jcotton42.alloyance.machine.ExtractOnlyItemHandler
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.HolderLookup
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.neoforged.neoforge.items.IItemHandler
import net.neoforged.neoforge.items.ItemStackHandler
import net.neoforged.neoforge.items.wrapper.CombinedInvWrapper
import net.neoforged.neoforge.items.wrapper.RangedWrapper

class CrusherBlockEntity(pos: BlockPos, state: BlockState): BlockEntity(AlloyanceBlocks.CRUSHER_BLOCK_ENTITY.get(), pos, state) {
    companion object {
        const val INVENTORY_TAG = "Inventory"
        const val SLOT_COUNT = 5
        const val INPUT_SLOT = 0
        const val FUEL_SLOT = 1
        const val OUTPUT_SLOT = 2
    }

    val inventory = object: ItemStackHandler(5) {
        override fun onContentsChanged(slot: Int) {
            setChanged()
        }

        override fun isItemValid(slot: Int, stack: ItemStack): Boolean = when (slot) {
            FUEL_SLOT -> stack.getBurnTime(RecipeType.SMELTING) > 0
            else -> true
        }
    }

    val input = RangedWrapper(inventory, INPUT_SLOT, FUEL_SLOT)
    val fuel = RangedWrapper(inventory, FUEL_SLOT, OUTPUT_SLOT)
    val outputs = ExtractOnlyItemHandler(RangedWrapper(inventory, OUTPUT_SLOT, SLOT_COUNT))
    val all = CombinedInvWrapper(input, fuel, outputs)

    fun tickSerer() {
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
        if (tag.contains(INVENTORY_TAG)) {
            inventory.deserializeNBT(registries, tag.getCompound(INVENTORY_TAG))
        }
    }
}