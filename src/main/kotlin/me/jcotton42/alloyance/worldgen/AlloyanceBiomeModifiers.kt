package me.jcotton42.alloyance.worldgen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_ASTRAL_SILVER_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_CARMOT_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_CERUCLASE_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_DEEP_IRON_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_EXIMITE_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_IGNATIUS_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_INFUSCOLIUM_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_KALENDRITE_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_LEMURITE_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_MANGANESE_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_MEUTOITE_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_MIDASIUM_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_MITHRIL_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_ORICHALCUM_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_OSMIUM_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_OURECLASE_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_PLATINUM_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_PROMETHEUM_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_RUBRACIUM_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_SANGUINITE_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_SHADOW_IRON_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_SILVER_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_TIN_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_UNDERWATER_DEEP_IRON_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_VULCANITE_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_ZINC_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.ASTRAL_SILVER_ORE_LOWER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.ASTRAL_SILVER_ORE_UPPER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.CARMOT_ORE_LOWER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.CARMOT_ORE_UPPER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.CERUCLASE_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.DEEP_IRON_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.EXIMITE_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.IGNATIUS_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.INFUSCOLIUM_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.KALENDRITE_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.LEMURITE_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.MANGANESE_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.MEUTOITE_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.MIDASIUM_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.MITHRIL_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.MITHRIL_ORE_LOWER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.MITHRIL_ORE_UPPER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.ORICHALCUM_ORE_LOWER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.ORICHALCUM_ORE_MIDDLE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.ORICHALCUM_ORE_UPPER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.OSMIUM_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.OURECLASE_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.PLATINUM_ORE_LOWER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.PLATINUM_ORE_UPPER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.PROMETHEUM_ORE_LOWER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.PROMETHEUM_ORE_UPPER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.RUBRACIUM_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.SANGUINITE_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.SHADOW_IRON_ORE_LOWER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.SHADOW_IRON_ORE_UPPER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.SILVER_ORE_LOWER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.SILVER_ORE_UPPER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.TIN_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.UNDERWATER_DEEP_IRON_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.VULCANITE_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.ZINC_ORE
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.levelgen.GenerationStep
import net.neoforged.neoforge.common.world.BiomeModifier
import net.neoforged.neoforge.common.world.BiomeModifiers
import net.neoforged.neoforge.registries.NeoForgeRegistries

object AlloyanceBiomeModifiers {
    val ADD_DEEP_IRON_ORE = registerKey("add_deep_iron_ore")
    val ADD_UNDERWATER_DEEP_IRON_ORE = registerKey("add_underwater_deep_iron_ore")
    val ADD_PROMETHEUM_ORE_UPPER = registerKey("add_prometheum_ore_upper")
    val ADD_PROMETHEUM_ORE_LOWER = registerKey("add_prometheum_ore_lower")
    val ADD_TIN_ORE = registerKey("add_tin_ore")
    val ADD_ZINC_ORE = registerKey("add_zinc_ore")
    val ADD_OSMIUM_ORE = registerKey("add_osmium_ore")
    val ADD_SILVER_ORE_UPPER = registerKey("add_silver_ore_upper")
    val ADD_SILVER_ORE_LOWER = registerKey("add_silver_ore_lower")
    val ADD_INFUSCOLIUM_ORE = registerKey("add_infuscolium_ore")
    val ADD_MANGANESE_ORE = registerKey("add_manganese_ore")
    val ADD_ASTRAL_SILVER_ORE_UPPER = registerKey("add_astral_silver_ore_upper")
    val ADD_ASTRAL_SILVER_ORE_LOWER = registerKey("add_astral_silver_ore_lower")
    val ADD_IGNATIUS_ORE = registerKey("add_ignatius_ore")
    val ADD_OURECLASE_ORE = registerKey("add_oureclase_ore")
    val ADD_RUBRACIUM_ORE = registerKey("add_rubracium_ore")
    val ADD_SHADOW_IRON_ORE_UPPER = registerKey("add_shadow_iron_ore_upper")
    val ADD_SHADOW_IRON_ORE_LOWER = registerKey("add_shadow_iron_ore_lower")
    val ADD_CERUCLASE_ORE = registerKey("add_ceruclase_ore")
    val ADD_EXIMITE_ORE = registerKey("add_eximite_ore")
    val ADD_KALENDRITE_ORE = registerKey("add_kalendrite_ore")
    val ADD_MIDASIUM_ORE = registerKey("add_midasium_ore")
    val ADD_ORICHALCUM_ORE_UPPER = registerKey("add_orichalcum_ore_upper")
    val ADD_ORICHALCUM_ORE_MIDDLE = registerKey("add_orichalcum_ore_middle")
    val ADD_ORICHALCUM_ORE_LOWER = registerKey("add_orichalcum_ore_lower")
    val ADD_PLATINUM_ORE_UPPER = registerKey("add_platinum_ore_upper")
    val ADD_PLATINUM_ORE_LOWER = registerKey("add_platinum_ore_lower")
    val ADD_VULCANITE_ORE = registerKey("add_vulcanite_ore")
    val ADD_CARMOT_ORE_UPPER = registerKey("add_carmot_ore_upper")
    val ADD_CARMOT_ORE_LOWER = registerKey("add_carmot_ore_lower")
    val ADD_LEMURITE_ORE = registerKey("add_lemurite_ore")
    val ADD_MEUTOITE_ORE = registerKey("add_meutoite_ore")
    val ADD_MITHRIL_ORE = registerKey("add_mithril_ore")
    val ADD_MITHRIL_ORE_UPPER = registerKey("add_mithril_ore_upper")
    val ADD_MITHRIL_ORE_LOWER = registerKey("add_mithril_ore_lower")
    val ADD_SANGUINITE_ORE = registerKey("add_sanguinite_ore")

    private val ores = listOf(
        Triple(ADD_DEEP_IRON_ORE, HAS_DEEP_IRON_ORE, DEEP_IRON_ORE),
        Triple(ADD_UNDERWATER_DEEP_IRON_ORE, HAS_UNDERWATER_DEEP_IRON_ORE, UNDERWATER_DEEP_IRON_ORE),
        Triple(ADD_PROMETHEUM_ORE_UPPER, HAS_PROMETHEUM_ORE, PROMETHEUM_ORE_UPPER),
        Triple(ADD_PROMETHEUM_ORE_LOWER, HAS_PROMETHEUM_ORE, PROMETHEUM_ORE_LOWER),
        Triple(ADD_TIN_ORE, HAS_TIN_ORE, TIN_ORE),
        Triple(ADD_ZINC_ORE, HAS_ZINC_ORE, ZINC_ORE),
        Triple(ADD_OSMIUM_ORE, HAS_OSMIUM_ORE, OSMIUM_ORE),
        Triple(ADD_SILVER_ORE_UPPER, HAS_SILVER_ORE, SILVER_ORE_UPPER),
        Triple(ADD_SILVER_ORE_LOWER, HAS_SILVER_ORE, SILVER_ORE_LOWER),
        Triple(ADD_INFUSCOLIUM_ORE, HAS_INFUSCOLIUM_ORE, INFUSCOLIUM_ORE),
        Triple(ADD_MANGANESE_ORE, HAS_MANGANESE_ORE, MANGANESE_ORE),
        Triple(ADD_ASTRAL_SILVER_ORE_UPPER, HAS_ASTRAL_SILVER_ORE, ASTRAL_SILVER_ORE_UPPER),
        Triple(ADD_ASTRAL_SILVER_ORE_LOWER, HAS_ASTRAL_SILVER_ORE, ASTRAL_SILVER_ORE_LOWER),
        Triple(ADD_IGNATIUS_ORE, HAS_IGNATIUS_ORE, IGNATIUS_ORE),
        Triple(ADD_OURECLASE_ORE, HAS_OURECLASE_ORE, OURECLASE_ORE),
        Triple(ADD_RUBRACIUM_ORE, HAS_RUBRACIUM_ORE, RUBRACIUM_ORE),
        Triple(ADD_SHADOW_IRON_ORE_UPPER, HAS_SHADOW_IRON_ORE, SHADOW_IRON_ORE_UPPER),
        Triple(ADD_SHADOW_IRON_ORE_LOWER, HAS_SHADOW_IRON_ORE, SHADOW_IRON_ORE_LOWER),
        Triple(ADD_CERUCLASE_ORE, HAS_CERUCLASE_ORE, CERUCLASE_ORE),
        Triple(ADD_EXIMITE_ORE, HAS_EXIMITE_ORE, EXIMITE_ORE),
        Triple(ADD_KALENDRITE_ORE, HAS_KALENDRITE_ORE, KALENDRITE_ORE),
        Triple(ADD_MIDASIUM_ORE, HAS_MIDASIUM_ORE, MIDASIUM_ORE),
        Triple(ADD_ORICHALCUM_ORE_UPPER, HAS_ORICHALCUM_ORE, ORICHALCUM_ORE_UPPER),
        Triple(ADD_ORICHALCUM_ORE_MIDDLE, HAS_ORICHALCUM_ORE, ORICHALCUM_ORE_MIDDLE),
        Triple(ADD_ORICHALCUM_ORE_LOWER, HAS_ORICHALCUM_ORE, ORICHALCUM_ORE_LOWER),
        Triple(ADD_PLATINUM_ORE_UPPER, HAS_PLATINUM_ORE, PLATINUM_ORE_UPPER),
        Triple(ADD_PLATINUM_ORE_LOWER, HAS_PLATINUM_ORE, PLATINUM_ORE_LOWER),
        Triple(ADD_VULCANITE_ORE, HAS_VULCANITE_ORE, VULCANITE_ORE),
        Triple(ADD_CARMOT_ORE_UPPER, HAS_CARMOT_ORE, CARMOT_ORE_UPPER),
        Triple(ADD_CARMOT_ORE_LOWER, HAS_CARMOT_ORE, CARMOT_ORE_LOWER),
        Triple(ADD_LEMURITE_ORE, HAS_LEMURITE_ORE, LEMURITE_ORE),
        Triple(ADD_MEUTOITE_ORE, HAS_MEUTOITE_ORE, MEUTOITE_ORE),
        Triple(ADD_MITHRIL_ORE, HAS_MITHRIL_ORE, MITHRIL_ORE),
        Triple(ADD_MITHRIL_ORE_UPPER, HAS_MITHRIL_ORE, MITHRIL_ORE_UPPER),
        Triple(ADD_MITHRIL_ORE_LOWER, HAS_MITHRIL_ORE, MITHRIL_ORE_LOWER),
        Triple(ADD_SANGUINITE_ORE, HAS_SANGUINITE_ORE, SANGUINITE_ORE),
    )

    fun bootstrap(context: BootstrapContext<BiomeModifier>) {
        val placedFeatures = context.lookup(Registries.PLACED_FEATURE)
        val biomes = context.lookup(Registries.BIOME)

        for ((modifierKey, biomeTagKey, placedKey) in ores) {
            context.register(
                modifierKey, BiomeModifiers.AddFeaturesBiomeModifier(
                    biomes.getOrThrow(biomeTagKey),
                    HolderSet.direct(placedFeatures.getOrThrow(placedKey)),
                    GenerationStep.Decoration.UNDERGROUND_ORES
                )
            )
        }
    }

    private fun registerKey(path: String): ResourceKey<BiomeModifier> = ResourceKey.create(
        NeoForgeRegistries.Keys.BIOME_MODIFIERS,
        ResourceLocation.fromNamespaceAndPath(Alloyance.ID, path)
    )
}
