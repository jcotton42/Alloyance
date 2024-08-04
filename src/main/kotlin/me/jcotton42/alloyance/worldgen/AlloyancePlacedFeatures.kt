package me.jcotton42.alloyance.worldgen

import me.jcotton42.alloyance.Alloyance
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.placement.BiomeFilter
import net.minecraft.world.level.levelgen.placement.CountPlacement
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement
import net.minecraft.world.level.levelgen.placement.InSquarePlacement
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraft.world.level.levelgen.placement.PlacementModifier
import net.minecraft.world.level.levelgen.placement.RarityFilter
import net.minecraft.world.level.levelgen.placement.SurfaceRelativeThresholdFilter

private val COMMON = 10
private val UNCOMMON = 7
private val RARE = 5
private val VERY_RARE = 3
private val ULTRA_RARE = 1

object AlloyancePlacedFeatures {
    val DEEP_IRON_ORE = registerKey("deep_iron_ore")
    val UNDERWATER_DEEP_IRON_ORE = registerKey("underwater_deep_iron_ore")
    val PROMETHEUM_ORE_UPPER = registerKey("prometheum_ore_upper")
    val PROMETHEUM_ORE_LOWER = registerKey("prometheum_ore_lower")
    val TIN_ORE = registerKey("tin_ore")
    val ZINC_ORE = registerKey("zinc_ore")

    fun bootstrap(context: BootstrapContext<PlacedFeature>) {
        val configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE)

        register(
            context,
            DEEP_IRON_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.DEEP_IRON_ORE),
            commonOrePlacement(
                COMMON,
                HeightRangePlacement.triangle(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(0))
            )
        )
        register(
            context,
            UNDERWATER_DEEP_IRON_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.DEEP_IRON_ORE),
            orePlacement(
                CountPlacement.of(UniformInt.of(44, 52)),
                HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(256)),
                SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, -32, 2)
            )
        )
        register(
            context,
            PROMETHEUM_ORE_UPPER,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.PROMETHEUM_ORE),
            commonOrePlacement(
                COMMON,
                HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(64))
            )
        )
        register(
            context,
            PROMETHEUM_ORE_LOWER,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.PROMETHEUM_ORE),
            commonOrePlacement(
                COMMON,
                HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-16))
            )
        )
        register(
            context,
            TIN_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.TIN_ORE),
            commonOrePlacement(
                COMMON,
                HeightRangePlacement.triangle(VerticalAnchor.absolute(-10), VerticalAnchor.absolute(45))
            )
        )
        register(
            context,
            ZINC_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.ZINC_ORE),
            commonOrePlacement(
                COMMON,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(10), VerticalAnchor.absolute(30))
            )
        )
    }

    private fun registerKey(path: String): ResourceKey<PlacedFeature> =
        ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, path))

    private fun register(
        context: BootstrapContext<PlacedFeature>,
        key: ResourceKey<PlacedFeature>,
        configuration: Holder<ConfiguredFeature<*, *>>,
        modifiers: List<PlacementModifier>
    ) {
        context.register(key, PlacedFeature(configuration, modifiers))
    }

    private fun orePlacement(
        countPlacement: PlacementModifier,
        vararg placementModifiers: PlacementModifier
    ): List<PlacementModifier> {
        return listOf(countPlacement, InSquarePlacement.spread(), *placementModifiers, BiomeFilter.biome())
    }

    private fun commonOrePlacement(count: Int, vararg placementModifiers: PlacementModifier): List<PlacementModifier> {
        return orePlacement(CountPlacement.of(count), *placementModifiers)
    }

    private fun rareOrePlacement(chance: Int, vararg placementModifiers: PlacementModifier): List<PlacementModifier> {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), *placementModifiers)
    }
}
