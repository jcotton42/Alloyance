package me.jcotton42.alloyance.worldgen

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import me.jcotton42.alloyance.registration.AlloyancePlacementModifierTypes
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.RandomSource
import net.minecraft.world.level.ChunkPos
import net.minecraft.world.level.chunk.status.ChunkStatus
import net.minecraft.world.level.levelgen.placement.PlacementContext
import net.minecraft.world.level.levelgen.placement.PlacementFilter
import net.minecraft.world.level.levelgen.placement.PlacementModifierType
import net.minecraft.world.level.levelgen.structure.Structure

class StructureInChunkFilter(
    val structureId: ResourceLocation,
    val noStructureChance: Float): PlacementFilter() {

    constructor(structure: ResourceKey<Structure>, noStructureChance: Float) : this(
        structure.location(),
        noStructureChance
    )

    override fun type(): PlacementModifierType<*> = AlloyancePlacementModifierTypes.STRUCTURE_IN_CHUNK_FILTER.get()

    override fun shouldPlace(context: PlacementContext, random: RandomSource, pos: BlockPos): Boolean {
        val default = random.nextFloat() < noStructureChance

        val level = context.level

        val structureLookup = level.registryAccess().registryOrThrow(Registries.STRUCTURE)
        val structure = structureLookup.get(structureId)
            ?: return default

        val chunkPos = ChunkPos(pos)
        val chunk = level.getChunk(chunkPos.x, chunkPos.z, ChunkStatus.STRUCTURE_REFERENCES, false)
        val hasStructure = chunk?.getReferencesForStructure(structure)?.isNotEmpty()
        return hasStructure ?: false || default
    }

    companion object {
        val CODEC = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                ResourceLocation.CODEC.fieldOf("structure").forGetter(StructureInChunkFilter::structureId),
                Codec.FLOAT.fieldOf("no_structure_chance").forGetter(StructureInChunkFilter::noStructureChance)
            ).apply(instance, ::StructureInChunkFilter)
        }
    }
}