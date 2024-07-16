package me.jcotton42.alloyance.worldgen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.Registration
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest

object AlloyanceConfiguredFeatures {
    val DEEP_IRON_ORE_KEY = registerKey("deep_iron_ore")

    fun bootstrap(context: BootstapContext<ConfiguredFeature<*, *>>) {
        val stoneReplaceable = TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES)
        val deepIronOres = listOf(
            OreConfiguration.target(stoneReplaceable, Registration.DEEP_IRON_ORE.get().defaultBlockState())
        )
        // TODO check vein size
        register(context, DEEP_IRON_ORE_KEY, Feature.ORE, OreConfiguration(deepIronOres, 9))
    }

    private fun registerKey(name: String): ResourceKey<ConfiguredFeature<*, *>> {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation(Alloyance.ID, name))
    }

    private fun <FC: FeatureConfiguration, F: Feature<FC>> register(
        context: BootstapContext<ConfiguredFeature<*, *>>,
        key: ResourceKey<ConfiguredFeature<*, *>>,
        feature: F,
        configuration: FC
    ) {
        context.register(key, ConfiguredFeature(feature, configuration))
    }
}