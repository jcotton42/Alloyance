package me.jcotton42.alloyance.machine.crusher

import com.mojang.serialization.MapCodec
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.Containers
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class CrusherBlock(properties: BlockBehaviour.Properties): BaseEntityBlock(properties) {
    companion object {
        val FACING = BlockStateProperties.HORIZONTAL_FACING
        val LIT = BlockStateProperties.LIT
        val OCCLUSION_SHAPE = Shapes.or(
            box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            box(1.0, 9.0, 1.0, 15.0, 16.0, 15.0)
        )
    }

    init {
        registerDefaultState(stateDefinition.any()
            .setValue(FACING, Direction.NORTH)
            .setValue(LIT, false)
        )
    }

    override fun onRemove(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        newState: BlockState,
        movedByPiston: Boolean
    ) {
        if (newState.block != this) {
            val blockEntity = level.getBlockEntity(pos)
            if (blockEntity is CrusherBlockEntity && level is ServerLevel) {
                val itemHandler = blockEntity.inventory
                for (i in 0 until itemHandler.slots) {
                    Containers.dropItemStack(
                        level,
                        pos.x.toDouble(),
                        pos.y.toDouble(),
                        pos.z.toDouble(),
                        itemHandler.getStackInSlot(i)
                    )
                }
                blockEntity.getRecipesToAwardAndPopExperience(level, Vec3.atCenterOf(pos))
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(FACING, LIT)
    }

    override fun getOcclusionShape(state: BlockState, level: BlockGetter, pos: BlockPos): VoxelShape {
        return OCCLUSION_SHAPE
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        return defaultBlockState().setValue(FACING, context.horizontalDirection.opposite)
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)))
    }

    override fun mirror(state: BlockState, mirror: Mirror): BlockState {
        return rotate(state, mirror.getRotation(state.getValue(FACING)))
    }

    override fun codec(): MapCodec<out BaseEntityBlock> = AlloyanceBlocks.CRUSHER_CODEC.value()

    override fun getRenderShape(state: BlockState): RenderShape = RenderShape.MODEL

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity? = CrusherBlockEntity(pos, state)

    override fun <T: BlockEntity> getTicker(level: Level, state: BlockState, blockEntityType: BlockEntityType<T>): BlockEntityTicker<T>? {
        if (level.isClientSide) {
            return null
        }

        return createTickerHelper(blockEntityType, AlloyanceBlocks.CRUSHER_BLOCK_ENTITY.get()) { level, pos, state, entity ->
            entity.tickSerer(level, pos, state)
        }
    }

    override fun useWithoutItem(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hitResult: BlockHitResult
    ): InteractionResult {
        if (!level.isClientSide && player is ServerPlayer) {
            player.openMenu(getMenuProvider(state, level, pos)!!, pos)
        }
        return InteractionResult.sidedSuccess(level.isClientSide)
    }
}