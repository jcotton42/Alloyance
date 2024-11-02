package me.jcotton42.alloyance.client

import me.jcotton42.alloyance.registration.AlloyanceFluids
import me.jcotton42.alloyance.registration.AlloyanceItems
import net.minecraft.world.level.ItemLike
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
        val items = arrayOfNulls<ItemLike>(AlloyanceItems.BUCKETS.size)
        var i = 0
        AlloyanceItems.BUCKETS.values.forEach { bucket ->
            items[i] = bucket.get()
            i++
        }
        event.register(DynamicFluidContainerModel.Colors(), *items)
    }
}