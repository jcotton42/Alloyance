package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.registration.AlloyanceBlocks
import me.jcotton42.alloyance.registration.AlloyanceItems
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.item.Item
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator

class AlloyanceBlockLootProvider(lookupProvider: HolderLookup.Provider): BlockLootSubProvider(
    // explosion-resistant items
    emptySet(),
    FeatureFlags.REGISTRY.allFlags(),
    lookupProvider
) {
    override fun generate() {
        dropSelf(AlloyanceBlocks.ALLOYER.get())
        dropSelf(AlloyanceBlocks.CRUSHER.get())

        oreDropsItem(AlloyanceBlocks.PHOSPHORITE_ORE.get(), AlloyanceItems.PHOSPHORUS.get(), 1f, 3f)
        oreDropsItem(AlloyanceBlocks.DEEPSLATE_PHOSPHORITE_ORE.get(), AlloyanceItems.PHOSPHORUS.get(), 1f, 3f)

        oreDropsItem(AlloyanceBlocks.POTASH_ORE.get(), AlloyanceItems.POTASH.get(), 1f, 3f)
        oreDropsItem(AlloyanceBlocks.DEEPSLATE_POTASH_ORE.get(), AlloyanceItems.POTASH.get(), 1f, 3f)
        dropSelf(AlloyanceBlocks.POTASH_BLOCK.get())

        oreDropsItem(AlloyanceBlocks.DEEPSLATE_SULFUR_ORE.get(), AlloyanceItems.SULFUR.get(), 1f, 4f)
        dropSelf(AlloyanceBlocks.SULFUR_BLOCK.get())

        AlloyanceBlocks.STORAGE_BLOCKS.values.forEach { dropSelf(it.get()) }
        AlloyanceBlocks.ORES.forEach { (metal, ore) ->
            oreDropsItem(ore.get(), AlloyanceItems.RAW_MATERIALS.getValue(metal).get())
        }
        AlloyanceBlocks.DEEPSLATE_ORES.forEach { (metal, ore) ->
            oreDropsItem(ore.get(), AlloyanceItems.RAW_MATERIALS.getValue(metal).get())
        }
        AlloyanceBlocks.END_ORES.forEach { (metal, ore) ->
            oreDropsItem(ore.get(), AlloyanceItems.RAW_MATERIALS.getValue(metal).get())
        }
        AlloyanceBlocks.NETHER_ORES.forEach{ (metal, ore) ->
            oreDropsItem(ore.get(), AlloyanceItems.RAW_MATERIALS.getValue(metal).get())
        }
    }

    override fun getKnownBlocks(): Iterable<Block> = AlloyanceBlocks.BLOCKS.entries
        .stream()
        .map { it.value() }
        .toList()

    private fun oreDropsItem(block: Block, item: Item) {
        add(block, createOreDrop(block, item))
    }

    private fun oreDropsItem(block: Block, item: Item, countLower: Float, countUpper: Float) {
        val enchantments = registries.lookupOrThrow(Registries.ENCHANTMENT)
        val builder = createSilkTouchDispatchTable(
            block,
            applyExplosionDecay(
                block,
                LootItem.lootTableItem(item)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(countLower, countUpper)))
                    .apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
            )
        )
        add(block, builder)
    }
}