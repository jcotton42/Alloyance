package me.jcotton42.alloyance.worldgen

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import me.jcotton42.alloyance.registration.AlloyancePlacementModifierTypes
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.level.levelgen.placement.PlacementContext
import net.minecraft.world.level.levelgen.placement.PlacementFilter
import net.minecraft.world.level.levelgen.placement.PlacementModifierType

class BiomeTemperatureFilter private constructor(val minInclusive: Float, val maxExclusive: Float): PlacementFilter() {
    override fun type(): PlacementModifierType<*> = AlloyancePlacementModifierTypes.BIOME_TEMPERATURE_FILTER.get()

    override fun shouldPlace(context: PlacementContext, random: RandomSource, pos: BlockPos): Boolean {
        val temperature = context.level.getBiome(pos).value().baseTemperature
        return minInclusive <= temperature && temperature < maxExclusive
    }

    companion object {
        val CODEC = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                Codec.FLOAT.optionalFieldOf("min_inclusive", Float.MIN_VALUE).forGetter(BiomeTemperatureFilter::minInclusive),
                Codec.FLOAT.optionalFieldOf("max_exclusive", Float.MAX_VALUE).forGetter(BiomeTemperatureFilter::maxExclusive)
            ).apply(instance, ::BiomeTemperatureFilter)
        }

        fun coolerThan(maxExclusive: Float) = BiomeTemperatureFilter(Float.MIN_VALUE, maxExclusive)
        fun between(minInclusive: Float, maxExclusive: Float) = BiomeTemperatureFilter(minInclusive, maxExclusive)
        fun warmerThan(minInclusive: Float) = BiomeTemperatureFilter(minInclusive, Float.MAX_VALUE)
    }
}