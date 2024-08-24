package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import me.jcotton42.alloyance.registration.AlloyanceItemTags
import me.jcotton42.alloyance.registration.AlloyanceItems
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.loot.packs.VanillaLootTableProvider
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider
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
        // TODO, all the helper methods from vanilla are putting my recipes in the minecraft namespace, need to fix that
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

        buildCrusherRecipes(output)
    }

    private fun buildCrusherRecipes(output: RecipeOutput) {
        AlloyanceItems.DUSTS.forEach { (metal, dust) ->
            val oreTag = AlloyanceItemTags.ORES[metal]
            val rawMaterialTag = AlloyanceItemTags.RAW_MATERIALS[metal]
            val ingotTag = AlloyanceItemTags.INGOTS[metal]

            if (oreTag != null) {
                crushOre(output, dust.get(), oreTag)
            }
            if (rawMaterialTag != null) {
                crushRawMaterial(output, dust.get(), rawMaterialTag)
            }
            if (ingotTag != null) {
                crushIngot(output, dust.get(), ingotTag)
            }
        }

        crushOre(output, AlloyanceItems.IRON_DUST.get(), Tags.Items.ORES_IRON)
        crushRawMaterial(output, AlloyanceItems.IRON_DUST.get(), Tags.Items.RAW_MATERIALS_IRON)
        crushIngot(output, AlloyanceItems.IRON_DUST.get(), Tags.Items.INGOTS_IRON)

        crushOre(output, AlloyanceItems.GOLD_DUST.get(), Tags.Items.ORES_GOLD)
        crushRawMaterial(output, AlloyanceItems.GOLD_DUST.get(), Tags.Items.RAW_MATERIALS_GOLD)
        crushIngot(output, AlloyanceItems.GOLD_DUST.get(), Tags.Items.INGOTS_GOLD)

        crushOre(output, AlloyanceItems.COPPER_DUST.get(), Tags.Items.ORES_COPPER)
        crushRawMaterial(output, AlloyanceItems.COPPER_DUST.get(), Tags.Items.RAW_MATERIALS_COPPER)
        crushIngot(output, AlloyanceItems.COPPER_DUST.get(), Tags.Items.INGOTS_COPPER)

        crushOre(output, Items.REDSTONE, Tags.Items.ORES_REDSTONE)
        crushOre(output, Items.DIAMOND, Tags.Items.ORES_DIAMOND)
        crushOre(output, Items.EMERALD, Tags.Items.ORES_EMERALD)
        crushOre(output, Items.QUARTZ, Tags.Items.ORES_QUARTZ)
    }

    private fun crushOre(output: RecipeOutput, dust: Item, oreTag: TagKey<Item>) {
        CrusherRecipeBuilder(ItemStack(dust, 2), Ingredient.of(oreTag), 0.75F, 140)
            .group(getItemName(dust))
            .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "${getItemName(dust)}_from_crushing_ore"))
    }

    private fun crushRawMaterial(output: RecipeOutput, dust: Item, rawMaterialTag: TagKey<Item>) {
        CrusherRecipeBuilder(ItemStack(dust, 1), Ingredient.of(rawMaterialTag), 0.75F, 140)
            .group(getItemName(dust))
            .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "${getItemName(dust)}_from_crushing_raw_material"))
    }

    private fun crushIngot(output: RecipeOutput, dust: Item, ingotTag: TagKey<Item>) {
        CrusherRecipeBuilder(ItemStack(dust, 1), Ingredient.of(ingotTag), 0F, 140)
            .group(getItemName(dust))
            .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "${getItemName(dust)}_from_crushing_ingot"))
    }
}
