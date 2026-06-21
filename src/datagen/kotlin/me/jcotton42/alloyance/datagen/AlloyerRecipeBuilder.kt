package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.machine.alloyer.AlloyerRecipe
import me.jcotton42.alloyance.machine.crusher.CrusherRecipe
import net.minecraft.advancements.AdvancementRequirements
import net.minecraft.advancements.AdvancementRewards
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike
import net.neoforged.neoforge.common.crafting.SizedIngredient

class AlloyerRecipeBuilder(
    result: ItemStack,
    private val ingredient1: SizedIngredient,
    private val ingredient2: SizedIngredient,
    private val experience: Float,
    private val alloyingTime: Int,
): SimpleRecipeBuilder<AlloyerRecipeBuilder>(result) {
    constructor(
        result: ItemLike,
        ingredient1: SizedIngredient,
        ingredient2: SizedIngredient,
        experience: Float,
        alloyingTime: Int,
    ) : this(ItemStack(result.asItem()), ingredient1, ingredient2, experience, alloyingTime)

    override fun getThis(): AlloyerRecipeBuilder = this

    override fun save(
        output: RecipeOutput,
        id: ResourceLocation
    ) {
        val advancement = output.advancement()
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
            .rewards(AdvancementRewards.Builder.recipe(id))
            .requirements(AdvancementRequirements.Strategy.OR)
        criteria.forEach(advancement::addCriterion)
        val recipe = AlloyerRecipe(group ?: "", ingredient1, ingredient2, result, experience, alloyingTime)
        output.accept(id, recipe, advancement.build(id.withPrefix("recipes/")))
    }
}
