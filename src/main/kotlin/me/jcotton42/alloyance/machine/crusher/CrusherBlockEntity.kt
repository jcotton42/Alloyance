package me.jcotton42.alloyance.machine.crusher

import me.jcotton42.alloyance.registration.AlloyanceBlocks
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.neoforged.neoforge.items.IItemHandler
import net.neoforged.neoforge.items.ItemStackHandler

class CrusherBlockEntity(pos: BlockPos, state: BlockState): BlockEntity(AlloyanceBlocks.CRUSHER_BLOCK_ENTITY.get(), pos, state) {
    fun tickSerer() {
        ItemStackHandler
    }

    fun getItemHandler(side: Direction): IItemHandler = when (side) {
        Direction.UP ->
    }
}