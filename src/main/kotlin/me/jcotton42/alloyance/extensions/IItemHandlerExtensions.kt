package me.jcotton42.alloyance.extensions

import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.ItemContainerContents
import net.neoforged.neoforge.items.IItemHandler
import net.neoforged.neoforge.items.IItemHandlerModifiable
import kotlin.math.min

fun IItemHandler.getAllStacks(): List<ItemStack> {
    val items = mutableListOf<ItemStack>()
    for (slot in 0..<slots) {
        items.add(getStackInSlot(slot))
    }
    return items
}

fun ItemContainerContents.copyInto(handler: IItemHandlerModifiable) {
    val limit = min(slots, handler.slots)
    for (slot in 0..<limit) {
        handler.setStackInSlot(slot, getStackInSlot(slot))
    }
}