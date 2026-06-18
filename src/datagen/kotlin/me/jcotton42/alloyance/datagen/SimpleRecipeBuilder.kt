package me.jcotton42.alloyance.datagen

import net.minecraft.advancements.Criterion
import net.minecraft.data.recipes.RecipeBuilder
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

abstract class SimpleRecipeBuilder<B: SimpleRecipeBuilder<B>>(protected val result: ItemStack): RecipeBuilder {
    protected val criteria: MutableMap<String, Criterion<*>> = mutableMapOf()
    protected var group: String? = null

    protected abstract fun getThis(): B

    override fun unlockedBy(name: String, criterion: Criterion<*>): B {
        criteria[name] = criterion
        return getThis()
    }

    override fun group(groupName: String?): B {
        group = groupName
        return getThis()
    }

    override fun getResult(): Item = result.item
}