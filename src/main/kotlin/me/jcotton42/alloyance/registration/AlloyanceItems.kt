package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.Metal.*
import net.minecraft.core.Holder
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.registries.DeferredItem
import net.neoforged.neoforge.registries.DeferredRegister

object AlloyanceItems {
    val ITEMS = DeferredRegister.createItems(Alloyance.ID)

    val RAW_MATERIALS = mutableMapOf<Metal, DeferredItem<Item>>()
    val INGOTS = mutableMapOf<Metal, DeferredItem<Item>>()
    val NUGGETS = mutableMapOf<Metal, DeferredItem<Item>>()

    val DEEP_IRON_ORE = block(AlloyanceBlocks.DEEP_IRON_ORE)
    val DEEPSLATE_DEEP_IRON_ORE = block(AlloyanceBlocks.DEEPSLATE_DEEP_IRON_ORE)
    val DEEP_IRON_BLOCK = block(AlloyanceBlocks.DEEP_IRON_BLOCK)
    val RAW_DEEP_IRON = rawMaterial(DEEP_IRON)
    val DEEP_IRON_INGOT = ingot(DEEP_IRON)
    val DEEP_IRON_NUGGET = nugget(DEEP_IRON)

    val PROMETHEUM_ORE = block(AlloyanceBlocks.PROMETHEUM_ORE)
    val DEEPSLATE_PROMETHEUM_ORE = block(AlloyanceBlocks.DEEPSLATE_PROMETHEUM_ORE)
    val PROMETHEUM_BLOCK = block(AlloyanceBlocks.PROMETHEUM_BLOCK)
    val RAW_PROMETHEUM = rawMaterial(PROMETHEUM)
    val PROMETHEUM_INGOT = ingot(PROMETHEUM)
    val PROMETHEUM_NUGGET = nugget(PROMETHEUM)

    val ZINC_ORE = block(AlloyanceBlocks.ZINC_ORE)
    val DEEPSLATE_ZINC_ORE = block(AlloyanceBlocks.DEEPSLATE_ZINC_ORE)
    val ZINC_BLOCK = block(AlloyanceBlocks.ZINC_BLOCK)
    val RAW_ZINC = rawMaterial(ZINC)
    val ZINC_INGOT = ingot(ZINC)
    val ZINC_NUGGET = nugget(ZINC)

    val TIN_ORE = block(AlloyanceBlocks.TIN_ORE)
    val TIN_BLOCK = block(AlloyanceBlocks.TIN_BLOCK)
    val RAW_TIN = rawMaterial(TIN)
    val TIN_INGOT = ingot(TIN)
    val TIN_NUGGET = nugget(TIN)

    // TODO might want rarity on items for higher tiers, could do it by a level on the Metal?
    // TODO Also would be cool if some of the metals (maybe the Nether ones) were fire-resistant. This goes for all their items.

    private fun block(block: Holder<Block>): DeferredItem<BlockItem> {
        return ITEMS.registerSimpleBlockItem(block)
    }

    private fun rawMaterial(metal: Metal): DeferredItem<Item> {
        val item = ITEMS.registerSimpleItem("raw_${metal.id}")
        RAW_MATERIALS[metal] = item
        return item
    }

    private fun ingot(metal: Metal): DeferredItem<Item> {
        val item = ITEMS.registerSimpleItem("${metal.id}_ingot")
        INGOTS[metal] = item
        return item
    }

    private fun nugget(metal: Metal): DeferredItem<Item> {
        val item = ITEMS.registerSimpleItem("${metal.id}_nugget")
        NUGGETS[metal] = item
        return item
    }
}
