package me.jcotton42.deluxemetals.block

import me.jcotton42.deluxemetals.Registration
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class ProcessorBlock : BaseEntityBlock(Properties.of()
    .strength(3.5f)
    .noCollission()
    .requiresCorrectToolForDrops()
    .sound(SoundType.METAL)
) {
    companion object {
        val BUTTON00 = BooleanProperty.create("button00")
        val BUTTON01 = BooleanProperty.create("button01")
        val BUTTON10 = BooleanProperty.create("button10")
        val BUTTON11 = BooleanProperty.create("button11")

        private val SHAPE_DOWN = Shapes.box(0.0, 2.0/16.0, 0.0, 1.0, 1.0, 1.0);
        private val SHAPE_UP = Shapes.box(0.0, 0.0, 0.0, 1.0, 14.0/16.0, 1.0);
        private val SHAPE_NORTH = Shapes.box(0.0, 0.0, 2.0/16.0, 1.0, 1.0, 1.0);
        private val SHAPE_SOUTH = Shapes.box(0.0, 0.0, 0.0, 1.0, 1.0, 14.0/16);
        private val SHAPE_WEST = Shapes.box(2.0/16, 0.0, 0.0, 1.0, 1.0, 1.0);
        private val SHAPE_EAST = Shapes.box(0.0, 0.0, 0.0, 14.0/16.0, 1.0, 1.0);
    }

    override fun getShape(
        state: BlockState,
        getter: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape = when (state.getValue(BlockStateProperties.FACING)) {
        Direction.DOWN -> SHAPE_DOWN
        Direction.UP -> SHAPE_UP
        Direction.NORTH -> SHAPE_NORTH
        Direction.SOUTH -> SHAPE_SOUTH
        Direction.WEST -> SHAPE_WEST
        Direction.EAST -> SHAPE_EAST
    }

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity = ProcessorBlockEntity(pos, state)

    override fun <T : BlockEntity?> getTicker(
        level: Level,
        state: BlockState,
        blockEntityType: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        if (level.isClientSide) return null

        return createTickerHelper(blockEntityType, Registration.PROCESSOR_BLOCK_ENTITY.get()) { _, _, _, entity ->
            entity.tickServer()
        }
    }

    override fun getStateForPlacement(pContext: BlockPlaceContext): BlockState? {
        return super.getStateForPlacement(pContext)
    }
}