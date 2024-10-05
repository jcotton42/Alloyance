package me.jcotton42.alloyance.registration

import com.mojang.serialization.Codec
import me.jcotton42.alloyance.Alloyance
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.datamaps.DataMapType
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent

object AlloyanceDataMaps {
    val FUEL_SPEED: DataMapType<Item, Int> = DataMapType.builder(
        ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "fuel_speed"),
        Registries.ITEM,
        Codec.INT
    ).build()

    fun register(bus: IEventBus) {
        bus.addListener(::registerDataMapTypes)
    }

    private fun registerDataMapTypes(event: RegisterDataMapTypesEvent) {
        event.register(FUEL_SPEED)
    }
}