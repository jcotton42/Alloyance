package me.jcotton42.alloyance.worldgen

import me.jcotton42.alloyance.Alloyance
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey

object AlloyanceBiomeTags {
    val HAS_DEEP_IRON_ORE = create("has_deep_iron_ore")
    val HAS_UNDERWATER_DEEP_IRON_ORE = create("has_underwater_deep_iron_ore")
    val HAS_PROMETHEUM_ORE = create("has_prometheum_ore")
    val HAS_TIN_ORE = create("has_tin_ore")
    val HAS_ZINC_ORE = create("has_zinc_ore")

    private fun create(path: String) =
        TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, path))
}
