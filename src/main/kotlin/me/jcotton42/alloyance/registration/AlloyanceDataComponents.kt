package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.client.TooltipStyle
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.Registries
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister

object AlloyanceDataComponents {
    val COMPONENTS: DeferredRegister.DataComponents = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Alloyance.ID)

    val TOOLTIP_STYLE: DeferredHolder<DataComponentType<*>, DataComponentType<TooltipStyle>> = COMPONENTS.registerComponentType("tooltip_style") { builder ->
        builder.persistent(TooltipStyle.CODEC).networkSynchronized(TooltipStyle.STREAM_CODEC)
    }

    fun register(bus: IEventBus) {
        COMPONENTS.register(bus)
    }
}