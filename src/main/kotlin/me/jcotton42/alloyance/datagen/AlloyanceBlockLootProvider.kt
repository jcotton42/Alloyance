package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.registration.AlloyanceBlocks
import me.jcotton42.alloyance.registration.AlloyanceItems
import net.minecraft.core.HolderLookup
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

class AlloyanceBlockLootProvider(lookupProvider: HolderLookup.Provider): BlockLootSubProvider(
    // explosion-resistant items
    emptySet(),
    FeatureFlags.REGISTRY.allFlags(),
    lookupProvider
) {
    override fun generate() {
        dropSelf(AlloyanceBlocks.ALLOYER.get())
        dropSelf(AlloyanceBlocks.CRUSHER.get())

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
}