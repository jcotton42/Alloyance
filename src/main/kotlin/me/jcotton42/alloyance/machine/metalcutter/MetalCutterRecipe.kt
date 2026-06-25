package me.jcotton42.alloyance.machine.metalcutter

import net.minecraft.core.HolderLookup
import net.minecraft.core.NonNullList
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeInput
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.level.Level

class MetalCutterRecipe(
    private val group: String,
    val ingredient: Ingredient,
    val result: ItemStack,
): Recipe<MetalCutterRecipe.Input> {
    override fun matches(
        input: Input,
        level: Level
    ): Boolean = ingredient.test(input.block) && input.blade.isCorrectToolForDrops(input.block)

    override fun assemble(input: Input, registries: HolderLookup.Provider): ItemStack = result.copy()

    override fun canCraftInDimensions(width: Int, height: Int): Boolean = width * height > 0

    override fun getGroup(): String = group

    override fun getIngredients(): NonNullList<Ingredient?> = NonNullList.create<Ingredient>().apply {
        add(ingredient)
    }

    override fun getResultItem(registries: HolderLookup.Provider): ItemStack = result

    override fun getSerializer(): RecipeSerializer<*> {
        TODO("Not yet implemented")
    }

    override fun getType(): RecipeType<*> {
        TODO("Not yet implemented")
    }

    // TODO override getToastItem

    class Input(val blade: ItemStack, val block: ItemStack) : RecipeInput {
        override fun getItem(index: Int): ItemStack = when (index) {
            0 -> blade
            1 -> block
            else -> throw IndexOutOfBoundsException("No item for index $index.")
        }

        override fun size(): Int = 2
    }
}