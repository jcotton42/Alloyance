package me.jcotton42.deluxemetals.block

import me.jcotton42.deluxemetals.Registration
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState

class ComplexBlock : BaseEntityBlock(
    BlockBehaviour.Properties.of()
        .strength(3.5F)
        .requiresCorrectToolForDrops()
        .sound(SoundType.METAL)
) {
    override fun newBlockEntity(pos: BlockPos, state: BlockState) = ComplexBlockEntity(pos, state)

    override fun <T : BlockEntity?> getTicker(
        level: Level,
        state: BlockState,
        blockEntityType: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        if (level.isClientSide) return null

        return createTickerHelper(blockEntityType, Registration.COMPLEX_BLOCK_ENTITY.get()) { _, _, _, entity ->
            entity.tickServer()
        }
    }
}