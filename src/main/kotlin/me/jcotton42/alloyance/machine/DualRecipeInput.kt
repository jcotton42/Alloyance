package me.jcotton42.alloyance.machine

import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.RecipeInput

@JvmRecord
data class DualRecipeInput(val item1: ItemStack, val item2: ItemStack): RecipeInput {
    override fun getItem(index: Int): ItemStack = when (index) {
        0 -> item1
        1 -> item2
        else -> throw IllegalArgumentException("No item for index $index")
    }

    override fun size(): Int = 2
}
