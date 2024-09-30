package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.BiomeTagsProvider
import net.minecraft.tags.BiomeTags
import net.minecraft.world.level.biome.Biomes
import net.neoforged.neoforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class AlloyanceBiomeTagsProvider(
    output: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>,
    existingFileHelper: ExistingFileHelper,
) : BiomeTagsProvider(
    output,
    lookupProvider,
    Alloyance.ID,
    existingFileHelper,
) {
    override fun addTags(provider: HolderLookup.Provider) {
        tag(AlloyanceBiomeTags.HAS_DEEP_IRON_ORE)
            .addTags(BiomeTags.IS_OCEAN, BiomeTags.IS_RIVER, BiomeTags.IS_BEACH)
        tag(AlloyanceBiomeTags.HAS_UNDERWATER_DEEP_IRON_ORE)
            .addTags(BiomeTags.IS_OCEAN)
        tag(AlloyanceBiomeTags.HAS_PROMETHEUM_ORE)
            .addTag(BiomeTags.IS_JUNGLE)
        tag(AlloyanceBiomeTags.HAS_TIN_ORE)
            .addTag(BiomeTags.IS_OVERWORLD)
        tag(AlloyanceBiomeTags.HAS_ZINC_ORE)
            .addTag(BiomeTags.IS_OVERWORLD)

        tag(AlloyanceBiomeTags.HAS_OSMIUM_ORE)
            .addTags(BiomeTags.IS_OVERWORLD)
        tag(AlloyanceBiomeTags.HAS_SILVER_ORE)
            .addTags(BiomeTags.IS_OVERWORLD)
        tag(AlloyanceBiomeTags.HAS_INFUSCOLIUM_ORE)
            .addTags(BiomeTags.IS_OVERWORLD)
        tag(AlloyanceBiomeTags.HAS_MANGANESE_ORE)
            .addTags(BiomeTags.IS_OVERWORLD)

        tag(AlloyanceBiomeTags.HAS_ASTRAL_SILVER_ORE)
            .addTags(BiomeTags.IS_OVERWORLD)
        tag(AlloyanceBiomeTags.HAS_IGNATIUS_ORE)
            .addTags(BiomeTags.IS_NETHER)
        tag(AlloyanceBiomeTags.HAS_OURECLASE_ORE)
            .addTags(BiomeTags.IS_OVERWORLD)
        tag(AlloyanceBiomeTags.HAS_RUBRACIUM_ORE)
            .addTags(BiomeTags.IS_OVERWORLD)
        tag(AlloyanceBiomeTags.HAS_SHADOW_IRON_ORE)
            .addTags(BiomeTags.IS_NETHER)

        tag(AlloyanceBiomeTags.HAS_CERUCLASE_ORE)
            .addTags(BiomeTags.IS_NETHER)
        tag(AlloyanceBiomeTags.HAS_EXIMITE_ORE)
            .add(Biomes.SMALL_END_ISLANDS)
        tag(AlloyanceBiomeTags.HAS_KALENDRITE_ORE)
            .addTags(BiomeTags.IS_NETHER)
        tag(AlloyanceBiomeTags.HAS_MIDASIUM_ORE)
            .addTags(BiomeTags.IS_NETHER)
        tag(AlloyanceBiomeTags.HAS_ORICHALCUM_ORE)
            .addTags(BiomeTags.IS_OVERWORLD)
    }
}
