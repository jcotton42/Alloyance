package me.jcotton42.alloyance.worldgen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest

object AlloyanceConfiguredFeatures {
    val DEEP_IRON_ORE = registerKey("deep_iron_ore")
    val PROMETHEUM_ORE = registerKey("prometheum_ore")
    val TIN_ORE = registerKey("tin_ore")
    val ZINC_ORE = registerKey("zinc_ore")

    fun bootstrap(context: BootstrapContext<ConfiguredFeature<*, *>>) {
        val stoneReplaceables = TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES)
        val deepslateReplaceables = TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES)
        val deepIronOres = listOf(
            OreConfiguration.target(
                stoneReplaceables,
                AlloyanceBlocks.DEEP_IRON_ORE.get().defaultBlockState()
            ),
            OreConfiguration.target(
                deepslateReplaceables,
                AlloyanceBlocks.DEEPSLATE_DEEP_IRON_ORE.get().defaultBlockState()
            )
        )
        val prometheumOres = listOf(
            OreConfiguration.target(
                stoneReplaceables,
                AlloyanceBlocks.PROMETHEUM_ORE.get().defaultBlockState()
            ),
            OreConfiguration.target(
                deepslateReplaceables,
                AlloyanceBlocks.DEEPSLATE_PROMETHEUM_ORE.get().defaultBlockState()
            )
        )
        val tinOres = listOf(
            OreConfiguration.target(
                stoneReplaceables,
                AlloyanceBlocks.TIN_ORE.get().defaultBlockState()
            )
        )
        val zincOres = listOf(
            OreConfiguration.target(
                stoneReplaceables,
                AlloyanceBlocks.ZINC_ORE.get().defaultBlockState()
            ),
            OreConfiguration.target(
                deepslateReplaceables,
                AlloyanceBlocks.DEEPSLATE_ZINC_ORE.get().defaultBlockState()
            )
        )
        register(context, DEEP_IRON_ORE, Feature.ORE, OreConfiguration(deepIronOres, 5))
        register(context, PROMETHEUM_ORE, Feature.ORE, OreConfiguration(prometheumOres, 6))
        register(context, TIN_ORE, Feature.ORE, OreConfiguration(tinOres, 8))
        register(context, ZINC_ORE, Feature.ORE, OreConfiguration(zincOres, 8))
    }

    private fun registerKey(path: String): ResourceKey<ConfiguredFeature<*, *>> =
        ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, path))

    private fun <FC : FeatureConfiguration, F : Feature<FC>> register(
        context: BootstrapContext<ConfiguredFeature<*, *>>,
        key: ResourceKey<ConfiguredFeature<*, *>>,
        feature: F,
        configuration: FC
    ) {
        context.register(key, ConfiguredFeature(feature, configuration))
    }
}
