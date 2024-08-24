package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.machine.crusher.CrusherRecipe
import net.minecraft.advancements.AdvancementRequirements
import net.minecraft.advancements.AdvancementRewards
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike

class CrusherRecipeBuilder(
    result: ItemStack,
    private val ingredient: Ingredient,
    private val experience: Float,
    private val crushingTime: Int,
): SimpleRecipeBuilder<CrusherRecipeBuilder>(result) {
    constructor(
        result: ItemLike,
        ingredient: Ingredient,
        experience: Float,
        crushingTime: Int,
    ) : this(ItemStack(result.asItem()), ingredient, experience, crushingTime)

    override fun getThis(): CrusherRecipeBuilder = this

    override fun save(output: RecipeOutput, id: ResourceLocation) {
        val advancement = output.advancement()
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
            .rewards(AdvancementRewards.Builder.recipe(id))
            .requirements(AdvancementRequirements.Strategy.OR)
        criteria.forEach(advancement::addCriterion)
        val recipe = CrusherRecipe(group ?: "", ingredient, result, experience, crushingTime)
        output.accept(id, recipe, advancement.build(id.withPrefix("recipes/")))
    }
}
