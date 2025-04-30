package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.client.TooltipStyle
import me.jcotton42.alloyance.machine.FuelProperties
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.Registries
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister

object AlloyanceDataComponents {
    val COMPONENTS: DeferredRegister.DataComponents = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Alloyance.ID)

    val FUEL_PROPERTIES: DeferredHolder<DataComponentType<*>, DataComponentType<FuelProperties>> = COMPONENTS.registerComponentType("fuel_properties") { builder ->
        builder.persistent(FuelProperties.CODEC).networkSynchronized(FuelProperties.STREAM_CODEC)
    }

    val TOOLTIP_STYLE: DeferredHolder<DataComponentType<*>, DataComponentType<TooltipStyle>> = COMPONENTS.registerComponentType("tooltip_style") { builder ->
        builder.persistent(TooltipStyle.CODEC).networkSynchronized(TooltipStyle.STREAM_CODEC)
    }

    fun register(bus: IEventBus) {
        COMPONENTS.register(bus)
    }
}
