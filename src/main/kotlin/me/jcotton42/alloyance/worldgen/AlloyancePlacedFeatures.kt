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
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight
import net.minecraft.world.level.levelgen.placement.BiomeFilter
import net.minecraft.world.level.levelgen.placement.CountPlacement
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement
import net.minecraft.world.level.levelgen.placement.InSquarePlacement
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraft.world.level.levelgen.placement.PlacementModifier
import net.minecraft.world.level.levelgen.placement.RarityFilter
import net.minecraft.world.level.levelgen.placement.SurfaceRelativeThresholdFilter

private const val COMMON = 10
private const val UNCOMMON = 7
private const val RARE = 5
private const val VERY_RARE = 3
private const val ULTRA_RARE = 1

object AlloyancePlacedFeatures {
    val DEEP_IRON_ORE = registerKey("deep_iron_ore")
    val UNDERWATER_DEEP_IRON_ORE = registerKey("underwater_deep_iron_ore")
    val PROMETHEUM_ORE_UPPER = registerKey("prometheum_ore_upper")
    val PROMETHEUM_ORE_LOWER = registerKey("prometheum_ore_lower")
    val TIN_ORE = registerKey("tin_ore")
    val ZINC_ORE = registerKey("zinc_ore")
    val OSMIUM_ORE = registerKey("osmium_ore")
    val SILVER_ORE_UPPER = registerKey("silver_ore_upper")
    val SILVER_ORE_LOWER = registerKey("silver_ore_lower")
    val INFUSCOLIUM_ORE = registerKey("infuscolium_ore")
    val MANGANESE_ORE = registerKey("manganese_ore")
    val ASTRAL_SILVER_ORE_UPPER = registerKey("astral_silver_ore_upper")
    val ASTRAL_SILVER_ORE_LOWER = registerKey("astral_silver_ore_lower")
    val IGNATIUS_ORE = registerKey("ignatius_ore")
    val OURECLASE_ORE = registerKey("oureclase_ore")
    val RUBRACIUM_ORE = registerKey("rubracium_ore")
    val SHADOW_IRON_ORE_UPPER = registerKey("shadow_iron_ore_upper")
    val SHADOW_IRON_ORE_LOWER = registerKey("shadow_iron_ore_lower")
    val CERUCLASE_ORE = registerKey("ceruclase_ore")
    val EXIMITE_ORE = registerKey("eximite_ore")
    val KALENDRITE_ORE = registerKey("kalendrite_ore")
    val MIDASIUM_ORE = registerKey("midasium_ore")
    val ORICHALCUM_ORE_UPPER = registerKey("orichalcum_ore_upper")
    val ORICHALCUM_ORE_MIDDLE = registerKey("orichalcum_ore_middle")
    val ORICHALCUM_ORE_LOWER = registerKey("orichalcum_ore_lower")
    val PLATINUM_ORE_UPPER = registerKey("platinum_ore_upper")
    val PLATINUM_ORE_LOWER = registerKey("platinum_ore_lower")
    val VULCANITE_ORE = registerKey("vulcanite_ore")
    val CARMOT_ORE_UPPER = registerKey("carmot_ore_upper")
    val CARMOT_ORE_LOWER = registerKey("carmot_ore_lower")
    val LEMURITE_ORE = registerKey("lemurite_ore")
    val MEUTOITE_ORE = registerKey("meutoite_ore")
    val MITHRIL_ORE = registerKey("mithril_ore")
    val MITHRIL_ORE_UPPER = registerKey("mithril_ore_upper")
    val MITHRIL_ORE_LOWER = registerKey("mithril_ore_lower")
    val SANGUINITE_ORE = registerKey("sanguinite_ore")
    val VYROXERES_ORE = registerKey("vyroxeres_ore")
    val ATLARUS_ORE_UPPER = registerKey("atlarus_ore_upper")
    val ATLARUS_ORE_LOWER = registerKey("atlarus_ore_lower")

    fun bootstrap(context: BootstrapContext<PlacedFeature>) {
        val configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE)

        register(
            context,
            DEEP_IRON_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.DEEP_IRON_ORE),
            countOrePlacement(
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
            countOrePlacement(
                COMMON,
                HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(64))
            )
        )
        register(
            context,
            PROMETHEUM_ORE_LOWER,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.PROMETHEUM_ORE),
            countOrePlacement(
                COMMON,
                HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-16))
            )
        )
        register(
            context,
            TIN_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.TIN_ORE),
            countOrePlacement(
                COMMON,
                HeightRangePlacement.triangle(VerticalAnchor.absolute(-10), VerticalAnchor.absolute(45))
            )
        )
        register(
            context,
            ZINC_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.ZINC_ORE),
            countOrePlacement(
                COMMON,
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(50), 30))
            )
        )

        register(
            context,
            OSMIUM_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.OSMIUM_ORE),
            countOrePlacement(
                UNCOMMON,
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-32), 12))
            )
        )
        register(
            context,
            SILVER_ORE_UPPER,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.SILVER_ORE),
            countOrePlacement(
                UNCOMMON,
                HeightRangePlacement.triangle(VerticalAnchor.absolute(-40), VerticalAnchor.absolute(96))
            )
        )
        register(
            context,
            SILVER_ORE_LOWER,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.SILVER_ORE),
            countOrePlacement(
                UNCOMMON,
                HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-40))
            )
        )
        register(
            context,
            INFUSCOLIUM_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.INFUSCOLIUM_ORE),
            countOrePlacement(
                UNCOMMON,
                HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(80))
            )
        )
        register(
            context,
            MANGANESE_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.MANGANESE_ORE),
            countOrePlacement(
                // COMMON was too rare with uniform placement
                COMMON * 2,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-16), VerticalAnchor.TOP)
            )
        )

        register(
            context,
            ASTRAL_SILVER_ORE_UPPER,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.ASTRAL_SILVER_ORE),
            countOrePlacement(
                COMMON,
                HeightRangePlacement.triangle(VerticalAnchor.absolute(100), VerticalAnchor.belowTop(-20))
            )
        )
        register(
            context,
            ASTRAL_SILVER_ORE_LOWER,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.ASTRAL_SILVER_ORE),
            countOrePlacement(
                COMMON / 2,
                HeightRangePlacement.triangle(VerticalAnchor.absolute(45), VerticalAnchor.absolute(155))
            )
        )
        register(
            context,
            IGNATIUS_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.IGNATIUS_ORE),
            countOrePlacement(
                COMMON * 2,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(96))
            )
        )
        register(
            context,
            OURECLASE_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.OURECLASE_ORE),
            countOrePlacement(
                UNCOMMON,
                HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(160))
            )
        )
        register(
            context,
            RUBRACIUM_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.RUBRACIUM_ORE),
            countOrePlacement(
                UNCOMMON,
                HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(8))
            )
        )
        register(
            context,
            SHADOW_IRON_ORE_UPPER,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.SHADOW_IRON_ORE),
            countOrePlacement(
                COMMON,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(97), VerticalAnchor.TOP)
            )
        )
        register(
            context,
            SHADOW_IRON_ORE_LOWER,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.SHADOW_IRON_ORE),
            countOrePlacement(
                COMMON,
                HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(31))
            )
        )

        register(
            context,
            CERUCLASE_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.CERUCLASE_ORE),
            countOrePlacement(
                COMMON,
                HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.TOP)
            )
        )
        register(
            context,
            EXIMITE_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.EXIMITE_ORE),
            countOrePlacement(
                COMMON,
                HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.TOP)
            )
        )
        register(
            context,
            KALENDRITE_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.KALENDRITE_ORE),
            countOrePlacement(
                RARE,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(27), VerticalAnchor.absolute(120))
            )
        )
        register(
            context,
            MIDASIUM_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.MIDASIUM_ORE),
            countOrePlacement(
                UNCOMMON,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.TOP)
            )
        )
        register(
            context,
            ORICHALCUM_ORE_UPPER,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.ORICHALCUM_ORE),
            countOrePlacement(
                VERY_RARE,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(48), VerticalAnchor.TOP)
            )
        )
        register(
            context,
            ORICHALCUM_ORE_MIDDLE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.ORICHALCUM_ORE),
            countOrePlacement(
                RARE,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(48))
            )
        )
        register(
            context,
            ORICHALCUM_ORE_LOWER,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.ORICHALCUM_ORE),
            countOrePlacement(
                RARE,
                HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.absolute(0))
            )
        )
        register(
            context,
            PLATINUM_ORE_UPPER,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.PLATINUM_ORE),
            countOrePlacement(
                ULTRA_RARE,
                HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(80))
            )
        )
        register(
            context,
            PLATINUM_ORE_LOWER,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.PLATINUM_ORE),
            countOrePlacement(
                VERY_RARE,
                HeightRangePlacement.triangle(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(0))
            )
        )
        register(
            context,
            VULCANITE_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.VULCANITE_ORE),
            countOrePlacement(
                UNCOMMON,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(27), VerticalAnchor.absolute(39))
            )
        )

        register(
            context,
            CARMOT_ORE_UPPER,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.CARMOT_ORE),
            countOrePlacement(
                RARE / 2,
                HeightRangePlacement.triangle(VerticalAnchor.absolute(40), VerticalAnchor.absolute(64))
            )
        )
        register(
            context,
            CARMOT_ORE_LOWER,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.CARMOT_ORE),
            countOrePlacement(
                RARE,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(6), VerticalAnchor.absolute(40))
            )
        )
        register(
            context,
            LEMURITE_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.LEMURITE_ORE),
            countOrePlacement(
                COMMON,
                HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(50))
            )
        )
        register(
            context,
            MEUTOITE_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.MEUTOITE_ORE),
            countOrePlacement(
                RARE,
                HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.TOP)
            )
        )
        register(
            context,
            MITHRIL_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.MITHRIL_ORE),
            countOrePlacement(
                ULTRA_RARE,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.TOP)
            )
        )
        register(
            context,
            MITHRIL_ORE_UPPER,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.MITHRIL_ORE),
            countOrePlacement(
                ULTRA_RARE,
                HeightRangePlacement.triangle(VerticalAnchor.absolute(176), VerticalAnchor.TOP)
            )
        )
        register(
            context,
            MITHRIL_ORE_LOWER,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.MITHRIL_ORE),
            countOrePlacement(
                ULTRA_RARE,
                HeightRangePlacement.triangle(VerticalAnchor.absolute(32), VerticalAnchor.absolute(175))
            )
        )
        register(
            context,
            SANGUINITE_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.SANGUINITE_ORE),
            countOrePlacement(
                VERY_RARE,
                HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.TOP)
            )
        )
        register(
            context,
            VYROXERES_ORE,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.VYROXERES_ORE),
            countOrePlacement(
                UNCOMMON,
                HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(120))
            )
        )

        register(
            context,
            ATLARUS_ORE_UPPER,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.ATLARUS_ORE),
            countOrePlacement(
                VERY_RARE,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-17), VerticalAnchor.absolute(42))
            )
        )
        register(
            context,
            ATLARUS_ORE_LOWER,
            configuredFeatures.getOrThrow(AlloyanceConfiguredFeatures.ATLARUS_ORE),
            countOrePlacement(
                VERY_RARE / 2,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(-16))
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

    private fun countOrePlacement(count: Int, vararg placementModifiers: PlacementModifier): List<PlacementModifier> {
        return orePlacement(CountPlacement.of(count), *placementModifiers)
    }

    private fun chanceOrePlacement(chance: Int, vararg placementModifiers: PlacementModifier): List<PlacementModifier> {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), *placementModifiers)
    }
}
