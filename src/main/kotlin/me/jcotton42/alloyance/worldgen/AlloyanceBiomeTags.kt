package me.jcotton42.alloyance.worldgen

import me.jcotton42.alloyance.Alloyance
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome

object AlloyanceBiomeTags {
    val HAS_DEEP_IRON_ORE: TagKey<Biome> = create("has_deep_iron_ore")
    val HAS_UNDERWATER_DEEP_IRON_ORE: TagKey<Biome> = create("has_underwater_deep_iron_ore")
    val HAS_PROMETHEUM_ORE: TagKey<Biome> = create("has_prometheum_ore")
    val HAS_TIN_ORE: TagKey<Biome> = create("has_tin_ore")
    val HAS_ZINC_ORE: TagKey<Biome> = create("has_zinc_ore")

    val HAS_OSMIUM_ORE: TagKey<Biome> = create("has_osmium_ore")
    val HAS_SILVER_ORE: TagKey<Biome> = create("has_silver_ore")
    val HAS_INFUSCOLIUM_ORE: TagKey<Biome> = create("has_infuscolium_ore")
    val HAS_MANGANESE_ORE: TagKey<Biome> = create("has_manganese_ore")

    val HAS_ASTRAL_SILVER_ORE: TagKey<Biome> = create("has_astral_silver_ore")
    val HAS_IGNATIUS_ORE: TagKey<Biome> = create("has_ignatius_ore")
    val HAS_OURECLASE_ORE: TagKey<Biome> = create("has_oureclase_ore")
    val HAS_RUBRACIUM_ORE: TagKey<Biome> = create("has_rubracium_ore")
    val HAS_SHADOW_IRON_ORE: TagKey<Biome> = create("has_shadow_iron_ore")

    val HAS_CERUCLASE_ORE: TagKey<Biome> = create("has_ceruclase_ore")
    val HAS_EXIMITE_ORE: TagKey<Biome> = create("has_eximite_ore")
    val HAS_KALENDRITE_ORE: TagKey<Biome> = create("has_kalendrite_ore")
    val HAS_MIDASIUM_ORE: TagKey<Biome> = create("has_midasium_ore")
    val HAS_ORICHALCUM_ORE: TagKey<Biome> = create("has_orichalcum_ore")
    val HAS_PLATINUM_ORE: TagKey<Biome> = create("has_platinum_ore")
    val HAS_VULCANITE_ORE: TagKey<Biome> = create("has_vulcanite_ore")

    val HAS_CARMOT_ORE: TagKey<Biome> = create("has_carmot_ore")
    val HAS_LEMURITE_ORE: TagKey<Biome> = create("has_lemurite_ore")
    val HAS_MEUTOITE_ORE: TagKey<Biome> = create("has_meutoite_ore")
    val HAS_MITHRIL_ORE: TagKey<Biome> = create("has_mithril_ore")
    val HAS_SANGUINITE_ORE: TagKey<Biome> = create("has_sanguinite_ore")
    val HAS_VYROXERES_ORE: TagKey<Biome> = create("has_vyroxeres_ore")

    val HAS_ATLARUS_ORE: TagKey<Biome> = create("has_atlarus_ore")
    val HAS_ADAMANTINE_ORE: TagKey<Biome> = create("has_adamantine_ore")

    private fun create(path: String): TagKey<Biome> =
        TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, path))
}
