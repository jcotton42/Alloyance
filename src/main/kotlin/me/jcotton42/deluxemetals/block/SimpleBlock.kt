package me.jcotton42.deluxemetals.block

import net.minecraft.core.BlockPos
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult

class SimpleBlock : Block(Properties.of()
    .strength(3.5f)
    .requiresCorrectToolForDrops()
    .sound(SoundType.METAL)
    .randomTicks()) {
    override fun randomTick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        level.sendParticles(ParticleTypes.SMOKE, pos.x + 0.5, pos.y + 1.5, pos.z + 0.5, 10, 0.0, 0.0, 0.0, 0.15)
    }

    override fun use(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hitResult: BlockHitResult
    ): InteractionResult {
        if (!level.isClientSide) {
            return InteractionResult.PASS
        }

        level.explode(null, pos.x + 0.5, pos.y + 0.5, pos.z + 0.5, 0.2f, false, Level.ExplosionInteraction.MOB)
        return InteractionResult.SUCCESS
    }
}