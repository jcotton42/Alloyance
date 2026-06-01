package me.jcotton42.alloyance.machine.metalcutter

import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.common.crafting.ICustomIngredient
import net.neoforged.neoforge.common.crafting.IngredientType
import java.util.stream.Stream

class DataComponentOnlyIngredient: ICustomIngredient {
    override fun test(stack: ItemStack): Boolean {
        TODO("Not yet implemented")
    }

    override fun getItems(): Stream<ItemStack?> {
        TODO("Not yet implemented")
    }

    override fun isSimple(): Boolean = false

    override fun getType(): IngredientType<*> {
        TODO("Not yet implemented")
    }
}