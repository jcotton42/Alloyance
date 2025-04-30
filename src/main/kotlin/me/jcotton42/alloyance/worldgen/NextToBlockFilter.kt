package me.jcotton42.alloyance.worldgen

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import me.jcotton42.alloyance.registration.AlloyancePlacementModifierTypes
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.placement.PlacementContext
import net.minecraft.world.level.levelgen.placement.PlacementFilter
import net.minecraft.world.level.levelgen.placement.PlacementModifierType

class NextToBlockFilter(val predicate: BlockPredicate, val defaultChance: Float): PlacementFilter() {
    override fun type(): PlacementModifierType<*> = AlloyancePlacementModifierTypes.NEXT_TO_BLOCK_FILTER.get()

    override fun shouldPlace(context: PlacementContext, random: RandomSource, pos: BlockPos): Boolean {
        val level = context.level

        if (predicate.test(level, pos)) {
            return true
        }

        val mutablePos = pos.mutable()
        for (direction in Direction.entries) {
            mutablePos.move(direction)
            if (predicate.test(level, mutablePos)) {
                return true
            }
            mutablePos.move(direction.opposite)
        }

        return random.nextFloat() < defaultChance
    }

    companion object {
        val CODEC = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                BlockPredicate.CODEC.fieldOf("predicate").forGetter(NextToBlockFilter::predicate),
                Codec.FLOAT.fieldOf("default_chance").forGetter(NextToBlockFilter::defaultChance),
            ).apply(instance, ::NextToBlockFilter)
        }
    }
}