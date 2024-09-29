package me.jcotton42.alloyance.worldgen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_ASTRAL_SILVER_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_DEEP_IRON_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_INFUSCOLIUM_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_MANGANESE_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_OSMIUM_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_PROMETHEUM_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_SILVER_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_TIN_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_UNDERWATER_DEEP_IRON_ORE
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeTags.HAS_ZINC_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.ASTRAL_SILVER_ORE_LOWER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.ASTRAL_SILVER_ORE_UPPER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.DEEP_IRON_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.INFUSCOLIUM_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.MANGANESE_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.OSMIUM_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.PROMETHEUM_ORE_LOWER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.PROMETHEUM_ORE_UPPER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.SILVER_ORE_LOWER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.SILVER_ORE_UPPER
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.TIN_ORE
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures.UNDERWATER_DEEP_IRON_ORE
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
