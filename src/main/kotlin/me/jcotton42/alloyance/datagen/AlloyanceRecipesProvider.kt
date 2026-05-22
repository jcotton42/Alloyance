package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.datagen.VanillaMetal.*
import me.jcotton42.alloyance.registration.*
import me.jcotton42.alloyance.registration.Metal.*
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
import net.neoforged.neoforge.common.crafting.SizedIngredient
import java.util.concurrent.CompletableFuture

class AlloyanceRecipesProvider(output: PackOutput, lookupProvider: CompletableFuture<HolderLookup.Provider>): RecipeProvider(output, lookupProvider) {
    override fun buildRecipes(output: RecipeOutput) {
        addMachineRecipes(output)
        addMiscIngredientRecipes(output)
        addToolRecipes(output)
        addArmorRecipes(output)

        crushOre(output, AlloyanceItems.PHOSPHORUS, 6, AlloyanceItemTags.ORES_PHOSPHORITE)

        nineBlockStorageRecipe(output, AlloyanceItems.POTASH, AlloyanceItems.POTASH_BLOCK)
        crushOre(output, AlloyanceItems.POTASH, 6, AlloyanceItemTags.ORES_POTASH)

        nineBlockStorageRecipe(output, AlloyanceItems.SULFUR, AlloyanceItems.SULFUR_BLOCK)
        crushOre(output, AlloyanceItems.SULFUR, 6, AlloyanceItemTags.ORES_SULFUR)

        addVanillaCompatRecipes(output)

        Metal.entries.forEach { metal ->
            addMetalStorageRecipes(output, metal)
            addMetalSmeltingRecipes(output, metal)
            addMetalCrusherRecipes(output, metal)
        }

        addAlloy(output, BRONZE, 4, 1.75f, COPPER, 3, TIN, 1)
        addAlloy(output, BRASS, 4, 1.75f, COPPER, 3, ZINC, 1)
        addAlloy(output, DAMASCUS_STEEL, 3, 1.5f, IRON, 1, BRONZE, 2)
        addAlloy(output, ANGMALLEN, 2, 1.25f, GOLD, 1, IRON, 1)
        addAlloy(output, STEEL, 2, 1.5f, IRON, 1, MANGANESE, 2)
        addAlloy(output, HEPATIZON, 2, 1.25f, INFUSCOLIUM, 1, STEEL, 1)
        addAlloy(output, BLACK_STEEL, 4, 1.75f, DEEP_IRON, 3, INFUSCOLIUM, 1)
        addAlloy(output, ELECTRUM, 2, 1.25f, SILVER, 1, GOLD, 1)
        addAlloy(output, QUICKSILVER, 2, 1.25f, SILVER, 1, ASTRAL_SILVER, 1)
        addAlloy(output, CELENEGIL, 2, 1.25f, ORICHALCUM, 1, PLATINUM, 1)
        addAlloy(output, AMORDRINE, 2, 1.25f, KALENDRITE, 1, PLATINUM, 1)
        addAlloy(output, SHADOW_STEEL, 3, 1.5f, SHADOW_IRON, 2, LEMURITE, 1)
        addAlloy(output, HADEROTH, 3, 1.5f, MITHRIL, 1, RUBRACIUM, 2)
        addAlloy(output, DESICHALKOS, 2, 1.25f, EXIMITE, 1, MEUTOITE, 1)
        addAlloy(output, INOLASHITE, 2, 1.25f, ALDUORITE, 1, CERUCLASE, 1)
        addAlloy(output, KRIK, 2, 1.25f, LUTETIUM, 1, OSMIUM, 1)
        addAlloy(output, TARTARITE, 1, 1.5f, ADAMANTINE, 1, ATLARUS, 1)
        addAlloy(output, ETHERIUM, 2, 1.25f, SANGUINITE, 1, ALDUORITE, 1)
    }

    private fun addMachineRecipes(output: RecipeOutput) {
        val bimetalName = getItemName(AlloyanceItems.BIMETAL_STRUCTURE)
        val crusherName = getItemName(AlloyanceItems.CRUSHER)
        val alloyerName = getItemName(AlloyanceItems.ALLOYER)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AlloyanceItems.BIMETAL_STRUCTURE)
            .pattern("tzt")
            .pattern("z z")
            .pattern("tzt")
            .define('t', AlloyanceItemTags.INGOTS_TIN)
            .define('z', AlloyanceItemTags.INGOTS_ZINC)
            .group(bimetalName)
            .unlockedBy(getHasName(AlloyanceItems.TIN_INGOT), has(AlloyanceItemTags.INGOTS_TIN))
            .unlockedBy(getHasName(AlloyanceItems.ZINC_INGOT), has(AlloyanceItemTags.INGOTS_ZINC))
            .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, bimetalName))

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AlloyanceItems.CRUSHER)
            .pattern("opo")
            .pattern("omo")
            .pattern("sfs")
            .define('o', AlloyanceItemTags.INGOTS_OURECLASE)
            .define('p', Items.PISTON)
            .define('m', AlloyanceItems.BIMETAL_STRUCTURE)
            .define('s', AlloyanceItemTags.INGOTS_STEEL)
            .define('f', Items.BLAST_FURNACE)
            .group(crusherName)
            .unlockedBy(getHasName(AlloyanceItems.OURECLASE_INGOT), has(AlloyanceItemTags.INGOTS_OURECLASE))
            .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, crusherName))

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AlloyanceItems.ALLOYER)
            .pattern("obo")
            .pattern("omo")
            .pattern("tft")
            .define('o', AlloyanceItemTags.INGOTS_OSMIUM)
            .define('b', Items.BUCKET)
            .define('m', AlloyanceItems.BIMETAL_STRUCTURE)
            .define('t', AlloyanceItemTags.INGOTS_TIN)
            .define('f', Items.BLAST_FURNACE)
            .group(alloyerName)
            .unlockedBy(getHasName(AlloyanceItems.OSMIUM_INGOT), has(AlloyanceItemTags.INGOTS_OSMIUM))
            .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, alloyerName))
    }

    private fun addMiscIngredientRecipes(output: RecipeOutput) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AlloyanceItems.THERMITE_DUST)
            .requires(AlloyanceItemTags.DUSTS_POTASH)
            .requires(AlloyanceItemTags.DUSTS_SULFUR)
            .requires(AlloyanceItemTags.DUSTS_IRON)
            .group(getItemName(AlloyanceItems.THERMITE_DUST))
            .unlockedBy(getHasName(AlloyanceItems.POTASH), has(AlloyanceItemTags.DUSTS_POTASH))
            .unlockedBy(getHasName(AlloyanceItems.SULFUR), has(AlloyanceItemTags.DUSTS_SULFUR))
            .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, getItemName(AlloyanceItems.THERMITE_DUST)))

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AlloyanceItems.INFUSED_IGNATIUS)
            .requires(AlloyanceItems.THERMITE_DUST)
            .requires(AlloyanceItemTags.DUSTS_IGNATIUS)
            .requires(AlloyanceItemTags.DUSTS_PHOSPHOROUS)
            .group(getItemName(AlloyanceItems.INFUSED_IGNATIUS))
            .unlockedBy(getHasName(AlloyanceItems.THERMITE_DUST), has(AlloyanceItems.THERMITE_DUST))
            .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, getItemName(AlloyanceItems.INFUSED_IGNATIUS)))
    }

    private fun addToolRecipes(output: RecipeOutput) {
        AlloyanceItems.AXES.forEach { (metal, axe) ->
            val ingot = AlloyanceItems.INGOTS.getValue(metal)
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe)
                .pattern("ii ")
                .pattern("is ")
                .pattern(" s ")
                .define('i', ingot)
                .define('s', Items.STICK)
                .group(getItemName(axe))
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, getItemName(axe)))
        }
        AlloyanceItems.HOES.forEach { (metal, hoe) ->
            val ingot = AlloyanceItems.INGOTS.getValue(metal)
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe)
                .pattern("ii ")
                .pattern(" s ")
                .pattern(" s ")
                .define('i', ingot)
                .define('s', Items.STICK)
                .group(getItemName(hoe))
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, getItemName(hoe)))
        }
        AlloyanceItems.PICKAXES.forEach { (metal, pickaxe) ->
            val ingot = AlloyanceItems.INGOTS.getValue(metal)
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxe)
                .pattern("iii")
                .pattern(" s ")
                .pattern(" s ")
                .define('i', ingot)
                .define('s', Items.STICK)
                .group(getItemName(pickaxe))
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, getItemName(pickaxe)))
        }
        AlloyanceItems.SHOVELS.forEach { (metal, shovel) ->
            val ingot = AlloyanceItems.INGOTS.getValue(metal)
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel)
                .pattern(" i ")
                .pattern(" s ")
                .pattern(" s ")
                .define('i', ingot)
                .define('s', Items.STICK)
                .group(getItemName(shovel))
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, getItemName(shovel)))
        }
        AlloyanceItems.SWORDS.forEach { (metal, sword) ->
            val ingot = AlloyanceItems.INGOTS.getValue(metal)
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, sword)
                .pattern(" i ")
                .pattern(" i ")
                .pattern(" s ")
                .define('i', ingot)
                .define('s', Items.STICK)
                .group(getItemName(sword))
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, getItemName(sword)))
        }
    }

    private fun addArmorRecipes(output: RecipeOutput) {
        fun helmet(result: ItemLike, ingot: ItemLike) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("iii")
                .pattern("i i")
                .define('i', ingot)
                .group(getItemName(result))
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, getItemName(result)))
        }
        fun chestplate(result: ItemLike, ingot: ItemLike) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("i i")
                .pattern("iii")
                .pattern("iii")
                .define('i', ingot)
                .group(getItemName(result))
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, getItemName(result)))
        }
        fun leggings(result: ItemLike, ingot: ItemLike) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("iii")
                .pattern("i i")
                .pattern("i i")
                .define('i', ingot)
                .group(getItemName(result))
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, getItemName(result)))
        }
        fun boots(result: ItemLike, ingot: ItemLike) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("i i")
                .pattern("i i")
                .define('i', ingot)
                .group(getItemName(result))
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, getItemName(result)))
        }

        AlloyanceItems.HELMETS.forEach { (metal, helmet) -> helmet(helmet, AlloyanceItems.INGOTS.getValue(metal)) }
        AlloyanceItems.CHESTPLATES.forEach { (metal, chestplate) -> chestplate(chestplate, AlloyanceItems.INGOTS.getValue(metal)) }
        AlloyanceItems.LEGGINGS.forEach { (metal, leggings) -> leggings(leggings, AlloyanceItems.INGOTS.getValue(metal)) }
        AlloyanceItems.BOOTS.forEach { (metal, boots) -> boots(boots, AlloyanceItems.INGOTS.getValue(metal)) }
    }

    private fun nineBlockStorageRecipe(output: RecipeOutput, unpacked: ItemLike, packed: ItemLike) {
        val unpackedName = getItemName(unpacked)
        val packedName = getItemName(packed)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, unpacked, 9)
            .requires(packed)
            .group(unpackedName)
            .unlockedBy(getHasName(packed), has(packed))
            .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "${unpackedName}_from_${packedName}"))
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, packed)
            .pattern("###")
            .pattern("###")
            .pattern("###")
            .define('#', unpacked)
            .group(packedName)
            .unlockedBy(getHasName(unpacked), has(unpacked))
            .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, packedName))
    }

    private fun addVanillaCompatRecipes(output: RecipeOutput) {
        smeltToIngot(output, AlloyanceItems.IRON_DUST, Items.IRON_INGOT)
        crushOre(output, AlloyanceItems.IRON_DUST, 2, Tags.Items.ORES_IRON)
        crushRawMaterial(output, AlloyanceItems.IRON_DUST, Tags.Items.RAW_MATERIALS_IRON)
        crushIngot(output, AlloyanceItems.IRON_DUST, Tags.Items.INGOTS_IRON)

        smeltToIngot(output, AlloyanceItems.GOLD_DUST, Items.GOLD_INGOT)
        crushOre(output, AlloyanceItems.GOLD_DUST, 2, Tags.Items.ORES_GOLD)
        crushRawMaterial(output, AlloyanceItems.GOLD_DUST, Tags.Items.RAW_MATERIALS_GOLD)
        crushIngot(output, AlloyanceItems.GOLD_DUST, Tags.Items.INGOTS_GOLD)

        smeltToIngot(output, AlloyanceItems.COPPER_DUST, Items.COPPER_INGOT)
        crushOre(output, AlloyanceItems.COPPER_DUST, 2, Tags.Items.ORES_COPPER)
        crushRawMaterial(output, AlloyanceItems.COPPER_DUST, Tags.Items.RAW_MATERIALS_COPPER)
        crushIngot(output, AlloyanceItems.COPPER_DUST, Tags.Items.INGOTS_COPPER)

        crushOre(output, Items.REDSTONE, 2, Tags.Items.ORES_REDSTONE)
        crushOre(output, Items.DIAMOND, 2, Tags.Items.ORES_DIAMOND)
        crushOre(output, Items.EMERALD, 2, Tags.Items.ORES_EMERALD)
        crushOre(output, Items.QUARTZ, 2, Tags.Items.ORES_QUARTZ)
    }

    private fun addMetalStorageRecipes(output: RecipeOutput, metal: Metal) {
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

    private fun addMetalSmeltingRecipes(output: RecipeOutput, metal: Metal) {
        val ingot = AlloyanceItems.INGOTS.getValue(metal)
        val smeltables = listOf(
            AlloyanceBlocks.ORES[metal],
            AlloyanceBlocks.DEEPSLATE_ORES[metal],
            AlloyanceBlocks.END_ORES[metal],
            AlloyanceBlocks.NETHER_ORES[metal],
            AlloyanceItems.DUSTS[metal],
            AlloyanceItems.RAW_MATERIALS[metal],
        )
        for (input in smeltables) {
            if (input == null) continue
            smeltToIngot(output, input, ingot)
        }
    }

    private fun addMetalCrusherRecipes(output: RecipeOutput, metal: Metal) {
        val dust = AlloyanceItems.DUSTS.getValue(metal)
        val ingotTag = AlloyanceItemTags.INGOTS.getValue(metal)
        val oreTag = AlloyanceItemTags.ORES[metal]
        val rawMaterialTag = AlloyanceItemTags.RAW_MATERIALS[metal]

        crushIngot(output, dust, ingotTag)
        if (oreTag != null) {
            crushOre(output, dust, 2, oreTag)
        }
        if (rawMaterialTag != null) {
            crushRawMaterial(output, dust, rawMaterialTag)
        }
    }

    private fun addAlloy(output: RecipeOutput, result: Metal, resultCount: Int, experience: Float, input1: Any, input1Count: Int, input2: Any, input2Count: Int) {
        val resultIngot = AlloyanceItems.INGOTS.getValue(result)
        val resultDust = AlloyanceItems.DUSTS.getValue(result)
        val dustTag1 = getAlloyDust(input1)
        val alloyableTag1 = getAlloyAlloyable(input1)
        val id1 = getAlloyId(input1)
        val dustTag2 = getAlloyDust(input2)
        val alloyableTag2 = getAlloyAlloyable(input2)
        val id2 = getAlloyId(input2)

        AlloyerRecipeBuilder(
            ItemStack(resultIngot.get(), resultCount),
            SizedIngredient.of(alloyableTag1, input1Count),
            SizedIngredient.of(alloyableTag2, input2Count),
            experience,
            140
        )
            .group(getItemName(resultIngot))
            .save(
                output,
                ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "${getItemName(resultIngot)}_from_alloying")
            )

        if (input1Count + input2Count > 9) return

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, resultDust, resultCount)
            .requires(Ingredient.of(dustTag1), input1Count)
            .requires(Ingredient.of(dustTag2), input2Count)
            .group(getItemName(resultDust))
            .unlockedBy("has_${id1}_dust", has(dustTag1))
            .unlockedBy("has_${id2}_dust", has(dustTag2))
            .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "${getItemName(resultDust)}_from_mixing"))
    }

    private fun smeltToIngot(output: RecipeOutput, input: ItemLike, ingot: ItemLike) {
        val inputName = getItemName(input)
        val ingredient = Ingredient.of(input)
        val ingotName = getItemName(ingot)

        SimpleCookingRecipeBuilder.smelting(ingredient, RecipeCategory.MISC, ingot, 0.7F, 200)
            .group(ingotName)
            .unlockedBy(getHasName(input), has(input))
            .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "${ingotName}_from_smelting_${inputName}"))
        SimpleCookingRecipeBuilder.blasting(ingredient, RecipeCategory.MISC, ingot, 0.7F, 100)
            .group(ingotName)
            .unlockedBy(getHasName(input), has(input))
            .save(output, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "${ingotName}_from_blasting_${inputName}"))
    }

    private fun crushOre(output: RecipeOutput, dust: ItemLike, dustCount: Int, oreTag: TagKey<Item>) {
        CrusherRecipeBuilder(ItemStack(dust, dustCount), Ingredient.of(oreTag), 0.75F, 140)
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

    private fun getAlloyDust(input: Any): TagKey<Item> = when (input) {
        is Metal -> AlloyanceItemTags.DUSTS.getValue(input)
        is VanillaMetal -> input.dustTag
        else -> throw IllegalArgumentException("Not a vanilla or Alloyance metal: $input")
    }

    private fun getAlloyAlloyable(input: Any): TagKey<Item> = when (input) {
        is Metal -> AlloyanceItemTags.ALLOYABLES.getValue(input)
        is VanillaMetal -> input.alloyableTag
        else -> throw IllegalArgumentException("Not a vanilla or Alloyance metal: $input")
    }

    private fun getAlloyId(input: Any): String = when (input) {
        is Metal -> input.id
        is VanillaMetal -> input.id
        else -> throw IllegalArgumentException("Not a vanilla or Alloyance metal: $input")
    }
}

private enum class VanillaMetal(val id: String, val dustTag: TagKey<Item>, val alloyableTag: TagKey<Item>) {
    COPPER("copper", AlloyanceItemTags.DUSTS_COPPER, AlloyanceItemTags.ALLOYABLES_COPPER),
    IRON("iron", AlloyanceItemTags.DUSTS_IRON, AlloyanceItemTags.ALLOYABLES_IRON),
    GOLD("gold", AlloyanceItemTags.DUSTS_GOLD, AlloyanceItemTags.ALLOYABLES_GOLD),
}
