package me.jcotton42.alloyance.worldgen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest
import java.util.function.Supplier

object AlloyanceConfiguredFeatures {
    val DEEP_IRON_ORE = registerKey("deep_iron_ore")
    val PROMETHEUM_ORE = registerKey("prometheum_ore")
    val TIN_ORE = registerKey("tin_ore")
    val ZINC_ORE = registerKey("zinc_ore")
    val OSMIUM_ORE = registerKey("osmium_ore")
    val SILVER_ORE = registerKey("silver_ore")
    val INFUSCOLIUM_ORE = registerKey("infuscolium_ore")
    val MANGANESE_ORE = registerKey("manganese_ore")
    val ASTRAL_SILVER_ORE = registerKey("astral_silver_ore")
    val IGNATIUS_ORE = registerKey("ignatius_ore")
    val OURECLASE_ORE = registerKey("oureclase_ore")
    val RUBRACIUM_ORE = registerKey("rubracium_ore")
    val SHADOW_IRON_ORE = registerKey("shadow_iron_ore")
    val CERUCLASE_ORE = registerKey("ceruclase_ore")
    val EXIMITE_ORE = registerKey("eximite_ore")
    val KALENDRITE_ORE = registerKey("kalendrite_ore")
    val MIDASIUM_ORE = registerKey("midasium_ore")
    val ORICHALCUM_ORE = registerKey("orichalcum_ore")
    val PLATINUM_ORE = registerKey("platinum_ore")
    val VULCANITE_ORE = registerKey("vulcanite_ore")
    val CARMOT_ORE = registerKey("carmot_ore")

    fun bootstrap(context: BootstrapContext<ConfiguredFeature<*, *>>) {
        val deepIronOres = listOf(inStone(AlloyanceBlocks.DEEP_IRON_ORE), inDeepslate(AlloyanceBlocks.DEEPSLATE_DEEP_IRON_ORE))
        val prometheumOres = listOf(inStone(AlloyanceBlocks.PROMETHEUM_ORE), inDeepslate(AlloyanceBlocks.DEEPSLATE_PROMETHEUM_ORE))
        val tinOres = listOf(inStone(AlloyanceBlocks.TIN_ORE))
        val zincOres = listOf(inStone(AlloyanceBlocks.ZINC_ORE), inDeepslate(AlloyanceBlocks.DEEPSLATE_ZINC_ORE))
        val osmiumOres = listOf(inDeepslate(AlloyanceBlocks.DEEPSLATE_OSMIUM_ORE))
        val silverOres = listOf(inStone(AlloyanceBlocks.SILVER_ORE), inDeepslate(AlloyanceBlocks.DEEPSLATE_SILVER_ORE))
        val infuscoliumOres = listOf(inStone(AlloyanceBlocks.INFUSCOLIUM_ORE), inDeepslate(AlloyanceBlocks.DEEPSLATE_INFUSCOLIUM_ORE))
        val manganeseOres = listOf(inStone(AlloyanceBlocks.MANGANESE_ORE), inDeepslate(AlloyanceBlocks.DEEPSLATE_MANGANESE_ORE))
        val astralSilverOres = listOf(inStone(AlloyanceBlocks.ASTRAL_SILVER_ORE))
        val ignatiusOres = listOf(inNetherrack(AlloyanceBlocks.NETHER_IGNATIUS_ORE))
        val oureclaseOres = listOf(inStone(AlloyanceBlocks.OURECLASE_ORE), inDeepslate(AlloyanceBlocks.DEEPSLATE_OURECLASE_ORE))
        val rubraciumOres = listOf(inStone(AlloyanceBlocks.RUBRACIUM_ORE), inDeepslate(AlloyanceBlocks.DEEPSLATE_RUBRACIUM_ORE))
        val shadowIronOres = listOf(inNetherrack(AlloyanceBlocks.NETHER_SHADOW_IRON_ORE))
        val ceruclaseOres = listOf(inNetherrack(AlloyanceBlocks.NETHER_CERUCLASE_ORE))
        val eximiteOres = listOf(inEndStone(AlloyanceBlocks.END_EXIMITE_ORE))
        val kalendriteOres = listOf(inNetherrack(AlloyanceBlocks.NETHER_KALENDRITE_ORE))
        val midasiumOres = listOf(inNetherrack(AlloyanceBlocks.NETHER_MIDASIUM_ORE))
        val orichalcumOres = listOf(inStone(AlloyanceBlocks.ORICHALCUM_ORE), inDeepslate(AlloyanceBlocks.DEEPSLATE_ORICHALCUM_ORE))
        val platinumOres = listOf(inStone(AlloyanceBlocks.PLATINUM_ORE), inDeepslate(AlloyanceBlocks.DEEPSLATE_PLATINUM_ORE))
        val vulcaniteOres = listOf(inNetherrack(AlloyanceBlocks.NETHER_VULCANITE_ORE))
        val carmotOres = listOf(inStone(AlloyanceBlocks.CARMOT_ORE), inDeepslate(AlloyanceBlocks.DEEPSLATE_CARMOT_ORE))

        register(context, DEEP_IRON_ORE, Feature.ORE, OreConfiguration(deepIronOres, 5))
        register(context, PROMETHEUM_ORE, Feature.ORE, OreConfiguration(prometheumOres, 6))
        register(context, TIN_ORE, Feature.ORE, OreConfiguration(tinOres, 8))
        register(context, ZINC_ORE, Feature.ORE, OreConfiguration(zincOres, 8))

        register(context, OSMIUM_ORE, Feature.ORE, OreConfiguration(osmiumOres, 6))
        register(context, SILVER_ORE, Feature.ORE, OreConfiguration(silverOres, 8))
        register(context, INFUSCOLIUM_ORE, Feature.ORE, OreConfiguration(infuscoliumOres, 5))
        register(context, MANGANESE_ORE, Feature.ORE, OreConfiguration(manganeseOres, 8))

        register(context, ASTRAL_SILVER_ORE, Feature.ORE, OreConfiguration(astralSilverOres, 6))
        register(context, IGNATIUS_ORE, Feature.ORE, OreConfiguration(ignatiusOres, 8))
        register(context, OURECLASE_ORE, Feature.ORE, OreConfiguration(oureclaseOres, 6))
        register(context, RUBRACIUM_ORE, Feature.ORE, OreConfiguration(rubraciumOres, 6))
        register(context, SHADOW_IRON_ORE, Feature.ORE, OreConfiguration(shadowIronOres, 7))

        register(context, CERUCLASE_ORE, Feature.ORE, OreConfiguration(ceruclaseOres, 5))
        register(context, EXIMITE_ORE, Feature.ORE, OreConfiguration(eximiteOres, 7))
        register(context, KALENDRITE_ORE, Feature.ORE, OreConfiguration(kalendriteOres, 5))
        register(context, MIDASIUM_ORE, Feature.ORE, OreConfiguration(midasiumOres, 6))
        register(context, ORICHALCUM_ORE, Feature.ORE, OreConfiguration(orichalcumOres, 6))
        register(context, PLATINUM_ORE, Feature.ORE, OreConfiguration(platinumOres, 4))
        register(context, VULCANITE_ORE, Feature.ORE, OreConfiguration(vulcaniteOres, 5))

        register(context, CARMOT_ORE, Feature.ORE, OreConfiguration(carmotOres, 4))
    }

    private fun inStone(replacement: Supplier<Block>): OreConfiguration.TargetBlockState {
        return OreConfiguration.target(TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), replacement.get().defaultBlockState())
    }

    private fun inDeepslate(replacement: Supplier<Block>): OreConfiguration.TargetBlockState {
        return OreConfiguration.target(TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), replacement.get().defaultBlockState())
    }

    private fun inEndStone(replacement: Supplier<Block>): OreConfiguration.TargetBlockState {
        return OreConfiguration.target(BlockMatchTest(Blocks.END_STONE), replacement.get().defaultBlockState())
    }

    private fun inNetherrack(replacement: Supplier<Block>): OreConfiguration.TargetBlockState {
        return OreConfiguration.target(BlockMatchTest(Blocks.NETHERRACK), replacement.get().defaultBlockState())
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
