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

    private fun create(path: String): TagKey<Biome> =
        TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, path))
}
