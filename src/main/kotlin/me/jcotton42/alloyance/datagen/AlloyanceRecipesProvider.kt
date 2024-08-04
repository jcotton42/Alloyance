package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.registration.AlloyanceBlocks
import me.jcotton42.alloyance.registration.AlloyanceItems
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.world.level.ItemLike
import java.util.concurrent.CompletableFuture

class AlloyanceRecipesProvider(output: PackOutput, lookupProvider: CompletableFuture<HolderLookup.Provider>): RecipeProvider(output, lookupProvider) {
    override fun buildRecipes(output: RecipeOutput) {
        // Shaped and ShapelessRecipeBuilder, oreSmelting, oreBlasting, etc.
        AlloyanceBlocks.STORAGE_BLOCKS.forEach { (metal, block) ->
            nineBlockStorageRecipesRecipesWithCustomUnpacking(
                output, RecipeCategory.MISC,
                AlloyanceItems.INGOTS.getValue(metal), RecipeCategory.BUILDING_BLOCKS,
                block.get(), "${metal.id    }_ingot_from_${metal.id}_block", "${metal.id}_ingot"
            )
        }
        AlloyanceItems.NUGGETS.forEach { (metal, nugget) ->
            nineBlockStorageRecipesWithCustomPacking(
                output, RecipeCategory.MISC,
                nugget.get(), RecipeCategory.MISC,
                AlloyanceItems.INGOTS.getValue(metal), "${metal.id}_ingot_from_nuggets", "${metal.id}_ingot"
            )
        }
        for ((metal, ingot) in AlloyanceItems.INGOTS) {
            val smeltables = mutableListOf<ItemLike>()
            val ore = AlloyanceBlocks.ORES[metal]
            val deepslateOre = AlloyanceBlocks.DEEPSLATE_ORES[metal]
            val rawMaterial = AlloyanceItems.RAW_MATERIALS[metal]
            if (ore != null) smeltables.add(ore)
            if (deepslateOre != null) smeltables.add(deepslateOre)
            if (rawMaterial != null) smeltables.add(rawMaterial)

            if (smeltables.isEmpty()) continue
            oreSmelting(
                output, smeltables, RecipeCategory.MISC,
                ingot.get(),
                // TODO check experience and smelt time values
                0.7f, 200, "${metal.id}_ingot"
            )
            // TODO check experience and blast time values
            oreBlasting(output, smeltables, RecipeCategory.MISC, ingot.get(), 0.7f, 100, "${metal.id}_ingot")
        }
    }
}
