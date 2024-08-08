package me.jcotton42.alloyance.machine

import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.items.IItemHandlerModifiable

class ExtractOnlyItemHandler(inner: IItemHandlerModifiable): IItemHandlerModifiable by inner {
    override fun insertItem(slot: Int, stack: ItemStack, simulate: Boolean): ItemStack {
        return stack
    }
}