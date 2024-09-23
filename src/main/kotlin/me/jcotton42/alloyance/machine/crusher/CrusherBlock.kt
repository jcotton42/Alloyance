package me.jcotton42.alloyance.machine.crusher

import com.mojang.serialization.MapCodec
import me.jcotton42.alloyance.machine.BaseMachineBlock
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class CrusherBlock(properties: BlockBehaviour.Properties): BaseMachineBlock<CrusherBlockEntity>(properties) {
    companion object {
        val OCCLUSION_SHAPE = Shapes.or(
            box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            box(1.0, 9.0, 1.0, 15.0, 16.0, 15.0)
        )
    }

    override fun getOcclusionShape(state: BlockState, level: BlockGetter, pos: BlockPos): VoxelShape {
        return OCCLUSION_SHAPE
    }

    override fun codec(): MapCodec<out BaseEntityBlock> = AlloyanceBlocks.CRUSHER_CODEC.value()

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity = CrusherBlockEntity(pos, state)

    override fun <T: BlockEntity> getTicker(level: Level, state: BlockState, blockEntityType: BlockEntityType<T>): BlockEntityTicker<T>? {
        if (level.isClientSide) {
            return null
        }

        return createTickerHelper(blockEntityType, AlloyanceBlocks.CRUSHER_BLOCK_ENTITY.get()) { level, pos, state, entity ->
            entity.tickServer(level, pos, state)
        }
    }
}