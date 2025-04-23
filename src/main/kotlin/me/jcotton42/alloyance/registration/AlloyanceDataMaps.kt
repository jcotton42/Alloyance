package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.machine.FuelProperties
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.datamaps.DataMapType
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent

object AlloyanceDataMaps {
    val FUEL_PROPERTIES: DataMapType<Item, FuelProperties> = DataMapType.builder(
        ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "fuel_properties"),
        Registries.ITEM,
        FuelProperties.CODEC
    ).build()

    fun register(bus: IEventBus) {
        bus.addListener(::registerDataMapTypes)
    }

    private fun registerDataMapTypes(event: RegisterDataMapTypesEvent) {
        event.register(FUEL_PROPERTIES)
    }
}