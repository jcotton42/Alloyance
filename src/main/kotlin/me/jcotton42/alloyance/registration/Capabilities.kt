package me.jcotton42.alloyance.registration

import net.neoforged.neoforge.capabilities.Capabilities
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent

fun registerCapabilities(event: RegisterCapabilitiesEvent) {
    event.registerBlockEntity(
        Capabilities.ItemHandler.BLOCK,
        AlloyanceBlocks.CRUSHER_BLOCK_ENTITY.get()
    ) { entity, side -> entity.getItemHandler(side) }
}
