package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.machine.FuelSpeed
import me.jcotton42.alloyance.registration.AlloyanceDataMaps
import me.jcotton42.alloyance.registration.AlloyanceItems
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.DataMapProvider
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps
import java.util.concurrent.CompletableFuture

class AlloyanceDataMapProvider(output: PackOutput, lookupProvider: CompletableFuture<HolderLookup.Provider>): DataMapProvider(output, lookupProvider) {
    override fun gather() {
        builder(AlloyanceDataMaps.FUEL_SPEED)
            .add(AlloyanceItems.THERMITE_DUST, FuelSpeed(2), false)
            .add(AlloyanceItems.INFUSED_IGNATIUS, FuelSpeed(3), false)

        builder(NeoForgeDataMaps.FURNACE_FUELS)
            .add(AlloyanceItems.THERMITE_DUST, FurnaceFuel(8 * 200), false)
            .add(AlloyanceItems.INFUSED_IGNATIUS, FurnaceFuel(24 * 200), false)
    }
}