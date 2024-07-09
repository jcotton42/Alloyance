package me.jcotton42.deluxemetals.datagen

import me.jcotton42.deluxemetals.Registration
import net.minecraft.advancements.critereon.InventoryChangeTrigger
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import net.minecraftforge.common.Tags
import java.util.function.Consumer

class TutRecipes(output: PackOutput): RecipeProvider(output) {
    override fun buildRecipes(consumer: Consumer<FinishedRecipe>) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Registration.SIMPLE_BLOCK.get())
            .requires(ItemTags.DIRT)
            .requires(Tags.Items.GEMS_DIAMOND)
            .unlockedBy("has_diamond", InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(Tags.Items.GEMS_DIAMOND).build()
            ))
            .save(consumer)

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.COMPLEX_BLOCK.get())
            .pattern("dsd")
            .pattern("dxd")
            .pattern("ddd")
            .define('d', ItemTags.DIRT)
            .define('x', Tags.Items.GEMS_DIAMOND)
            .define('s', Items.STICK)
            .group("tutorial")
            .unlockedBy("has_diamond", InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(Tags.Items.GEMS_DIAMOND).build()
            ))
            .save(consumer)
    }
}