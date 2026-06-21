package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.worldgen.AlloyanceBiomeModifiers
import me.jcotton42.alloyance.worldgen.AlloyanceConfiguredFeatures
import me.jcotton42.alloyance.worldgen.AlloyancePlacedFeatures
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider
import net.neoforged.neoforge.registries.NeoForgeRegistries
import java.util.concurrent.CompletableFuture

class AlloyanceWorldGenProvider(
    output: PackOutput,
    registries: CompletableFuture<HolderLookup.Provider>
): DatapackBuiltinEntriesProvider(output, registries, BUILDER, setOf(Alloyance.ID)) {
    companion object {
        val BUILDER = RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, AlloyanceConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, AlloyancePlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, AlloyanceBiomeModifiers::bootstrap)
    }
}