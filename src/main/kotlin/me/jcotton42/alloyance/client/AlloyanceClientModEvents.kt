package me.jcotton42.alloyance.client

import me.jcotton42.alloyance.registration.AlloyanceFluids
import me.jcotton42.alloyance.registration.AlloyanceItems
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent
import net.neoforged.neoforge.client.model.DynamicFluidContainerModel

object AlloyanceClientModEvents {
    @SubscribeEvent
    fun onRegisterClientExtensions(event: RegisterClientExtensionsEvent) {
        event.registerFluidType(MoltenFluidClientExtension(0xFF111419.toInt()), AlloyanceFluids.TAR_TYPE)
    }

    @SubscribeEvent
    fun onRegisterItemColorHandlers(event: RegisterColorHandlersEvent.Item) {
        event.register(DynamicFluidContainerModel.Colors(), AlloyanceItems.MOLTEN_TAR_BUCKET.get())
    }
}