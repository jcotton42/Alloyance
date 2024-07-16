package me.jcotton42.alloyance.worldgen

import me.jcotton42.alloyance.Alloyance
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BiomeTags
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraftforge.common.world.BiomeModifier
import net.minecraftforge.common.world.ForgeBiomeModifiers
import net.minecraftforge.registries.ForgeRegistries

object AlloyanceBiomeModifiers {
    val ADD_DEEP_IRON_ORE = registerKey("add_deep_iron_ore")

    fun bootstrap(context: BootstapContext<BiomeModifier>) {
        val placedFeatures = context.lookup(Registries.PLACED_FEATURE)
        val biomes = context.lookup(Registries.BIOME)

        context.register(ADD_DEEP_IRON_ORE, ForgeBiomeModifiers.AddFeaturesBiomeModifier(
            biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
            HolderSet.direct(placedFeatures.getOrThrow(AlloyancePlacedFeatures.DEEP_IRON_ORE_PLACED_KEY)),
            GenerationStep.Decoration.UNDERGROUND_ORES))
    }

    private fun registerKey(name: String): ResourceKey<BiomeModifier> {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation(Alloyance.ID, name))
    }
}