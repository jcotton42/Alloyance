package me.jcotton42.alloyance.worldgen

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import me.jcotton42.alloyance.registration.AlloyancePlacementModifierTypes
import net.minecraft.core.BlockPos
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.RandomSource
import net.minecraft.world.level.levelgen.placement.PlacementContext
import net.minecraft.world.level.levelgen.placement.PlacementFilter
import net.minecraft.world.level.levelgen.placement.PlacementModifierType
import net.minecraft.world.level.levelgen.structure.Structure
import kotlin.jvm.optionals.getOrNull

class StructureProximityFilter(
    val structureId: ResourceLocation,
    val radius: Int,
    val noStructureChance: Float): PlacementFilter() {

    constructor(structure: ResourceKey<Structure>, radius: Int, noStructureChance: Float) : this(
        structure.location(),
        radius,
        noStructureChance
    )

    override fun type(): PlacementModifierType<*> = AlloyancePlacementModifierTypes.STRUCTURE_PROXIMITY_FILTER.get()

    override fun shouldPlace(context: PlacementContext, random: RandomSource, pos: BlockPos): Boolean {
        val default = random.nextFloat() < noStructureChance

        val structureLookup = context.level.registryAccess().registryOrThrow(Registries.STRUCTURE)
        val structure = structureLookup.getHolder(structureId).getOrNull()
            ?: return default

        val serverLevel = context.level.level
        return when (context.generator()
            .findNearestMapStructure(serverLevel, HolderSet.direct(structure), pos, radius, false)) {
            null -> default
            else -> true
        }
    }

    companion object {
        val CODEC = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                ResourceLocation.CODEC.fieldOf("structure").forGetter(StructureProximityFilter::structureId),
                Codec.INT.fieldOf("radius").forGetter(StructureProximityFilter::radius),
                Codec.FLOAT.fieldOf("no_structure_chance").forGetter(StructureProximityFilter::noStructureChance)
            ).apply(instance, ::StructureProximityFilter)
        }
    }
}