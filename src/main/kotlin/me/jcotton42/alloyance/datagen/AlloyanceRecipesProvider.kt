package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import me.jcotton42.alloyance.registration.AlloyanceItemTags
import me.jcotton42.alloyance.registration.AlloyanceItems
import me.jcotton42.alloyance.registration.Metal
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.neoforged.neoforge.common.Tags
import java.util.concurrent.CompletableFuture

class AlloyanceRecipesProvider(output: PackOutput, lookupProvider: CompletableFuture<HolderLookup.Provider>): RecipeProvider(output, lookupProvider) {
    override fun buildRecipes(output: RecipeOutput) {
        addVanillaCompatRecipes(output)

        // TODO, all the helper methods from vanilla are putting my recipes in the minecraft namespace, need to fix that
        Metal.entries.forEach { metal ->
            addStorageRecipes(output, metal)
            addSmeltingRecipes(output, metal)
            addCrusherRecipes(output, metal)
        }
    }

    private fun addVanillaCompatRecipes(output: RecipeOutput) {
        crushOre(output, AlloyanceItems.IRON_DUST, Tags.Items.ORES_IRON)
        crushRawMaterial(output, AlloyanceItems.IRON_DUST, Tags.Items.RAW_MATERIALS_IRON)
        crushIngot(output, AlloyanceItems.IRON_DUST, Tags.Items.INGOTS_IRON)

        crushOre(output, AlloyanceItems.GOLD_DUST, Tags.Items.ORES_GOLD)
        crushRawMaterial(output, AlloyanceItems.GOLD_DUST, Tags.Items.RAW_MATERIALS_GOLD)
        crushIngot(output, AlloyanceItems.GOLD_DUST, Tags.Items.INGOTS_GOLD)

        crushOre(output, AlloyanceItems.COPPER_DUST, Tags.Items.ORES_COPPER)
        crushRawMaterial(output, AlloyanceItems.COPPER_DUST, Tags.Items.RAW_MATERIALS_COPPER)
        crushIngot(output, AlloyanceItems.COPPER_DUST, Tags.Items.INGOTS_COPPER)

        crushOre(output, Items.REDSTONE, Tags.Items.ORES_REDSTONE)
        crushOre(output, Items.DIAMOND, Tags.Items.ORES_DIAMOND)
        crushOre(output, Items.EMERALD, Tags.Items.ORES_EMERALD)
        crushOre(output, Items.QUARTZ, Tags.Items.ORES_QUARTZ)
    }

    private fun addStorageRecipes(output: RecipeOutput, metal: Metal) {
        // deliberately not using tags here
        val block = AlloyanceBlocks.STORAGE_BLOCKS.getValue(metal).get()
        val blockName = getItemName(block)
        val ingot = AlloyanceItems.INGOTS.getValue(metal).get()
        val ingotName = getItemName(ingot)
        val nugget = AlloyanceItems.NUGGETS.getValue(metal).get()
        val nuggetName = getItemName(nugget)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
            .pattern("iii")
            .pattern("iii")
            .pattern("iii")
            .define('i', ingot)
            .group(blockName)
            .unlockedBy(getHasName(ingot), has(ingot))
            .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, blockName))
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingot, 9)
            .requires(block)
            .group(ingotName)
            .unlockedBy(getHasName(block), has(block))
            .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "${ingotName}_from_${blockName}"))

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ingot)
            .pattern("nnn")
            .pattern("nnn")
            .pattern("nnn")
            .define('n', nugget)
            .group(ingotName)
            .unlockedBy(getHasName(nugget), has(nugget))
            .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "${ingotName}_from_nuggets"))
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, nugget, 9)
            .requires(ingot)
            .group(nuggetName)
            .unlockedBy(getHasName(ingot), has(ingot))
            .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, nuggetName))
    }

    private fun addSmeltingRecipes(output: RecipeOutput, metal: Metal) {
        val ingot = AlloyanceItems.INGOTS.getValue(metal)
        val ingotName = getItemName(ingot)
        val smeltables = listOf(
            AlloyanceBlocks.ORES[metal],
            AlloyanceBlocks.DEEPSLATE_ORES[metal],
            AlloyanceItems.RAW_MATERIALS[metal],
        )
        for (input in smeltables) {
            if (input == null) continue
            val inputName = getItemName(input)
            val ingredient = Ingredient.of(input)
            // TODO check experience, smelt time, blast time values
            SimpleCookingRecipeBuilder.smelting(ingredient, RecipeCategory.MISC, ingot, 0.7F, 200)
                .group(ingotName)
                .unlockedBy(getHasName(input), has(input))
                .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "${ingotName}_from_smelting_${inputName}"))
            SimpleCookingRecipeBuilder.blasting(ingredient, RecipeCategory.MISC, ingot, 0.7F, 100)
                .group(ingotName)
                .unlockedBy(getHasName(input), has(input))
                .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "${ingotName}_from_blasting_${inputName}"))
        }
    }

    private fun addCrusherRecipes(output: RecipeOutput, metal: Metal) {
        val dust = AlloyanceItems.DUSTS.getValue(metal)
        val ingotTag = AlloyanceItemTags.INGOTS.getValue(metal)
        val oreTag = AlloyanceItemTags.ORES[metal]
        val rawMaterialTag = AlloyanceItemTags.RAW_MATERIALS[metal]

        crushIngot(output, dust, ingotTag)
        if (oreTag != null) {
            crushOre(output, dust, oreTag)
        }
        if (rawMaterialTag != null) {
            crushRawMaterial(output, dust, rawMaterialTag)
        }
    }

    private fun crushOre(output: RecipeOutput, dust: ItemLike, oreTag: TagKey<Item>) {
        CrusherRecipeBuilder(ItemStack(dust, 2), Ingredient.of(oreTag), 0.75F, 140)
            .group(getItemName(dust))
            .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "${getItemName(dust)}_from_crushing_ore"))
    }

    private fun crushRawMaterial(output: RecipeOutput, dust: ItemLike, rawMaterialTag: TagKey<Item>) {
        CrusherRecipeBuilder(ItemStack(dust, 1), Ingredient.of(rawMaterialTag), 0.75F, 140)
            .group(getItemName(dust))
            .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "${getItemName(dust)}_from_crushing_raw_material"))
    }

    private fun crushIngot(output: RecipeOutput, dust: ItemLike, ingotTag: TagKey<Item>) {
        CrusherRecipeBuilder(ItemStack(dust, 1), Ingredient.of(ingotTag), 0F, 140)
            .group(getItemName(dust))
            .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "${getItemName(dust)}_from_crushing_ingot"))
    }
}
