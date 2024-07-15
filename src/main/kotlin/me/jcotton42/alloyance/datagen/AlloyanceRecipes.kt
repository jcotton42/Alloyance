package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Registration
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeProvider
import java.util.function.Consumer

class AlloyanceRecipes(output: PackOutput): RecipeProvider(output) {
    override fun buildRecipes(writer: Consumer<FinishedRecipe>) {
        // Shaped and ShapelessRecipeBuilder, oreSmelting, oreBlasting, etc.
        val deepIronSmeltables = listOf(Registration.DEEP_IRON_ORE.get(), Registration.RAW_DEEP_IRON.get())
        oreSmelting(writer, deepIronSmeltables, RecipeCategory.MISC,
            Registration.DEEP_IRON_INGOT.get(),
            // TODO check experience and smelt time values
            0.7f, 200, "deep_iron_ingot"
        )
        // TODO check experience and blast time values
        oreBlasting(writer, deepIronSmeltables, RecipeCategory.MISC, Registration.DEEP_IRON_INGOT.get(), 0.7f, 100, "deep_iron_ingot")
        nineBlockStorageRecipesRecipesWithCustomUnpacking(writer, RecipeCategory.MISC,
            Registration.DEEP_IRON_INGOT.get(), RecipeCategory.BUILDING_BLOCKS,
            Registration.DEEP_IRON_BLOCK.get(), "deep_iron_ingot_from_deep_iron_block", "deep_iron_ingot");
        nineBlockStorageRecipesWithCustomPacking(writer, RecipeCategory.MISC,
            Registration.DEEP_IRON_NUGGET.get(), RecipeCategory.MISC,
            Registration.DEEP_IRON_INGOT.get(), "deep_iron_ingot_from_nuggets", "deep_iron_ingot");
    }
}