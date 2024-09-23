package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.AlloyanceItems.ITEMS
import me.jcotton42.alloyance.registration.Metal.*
import net.minecraft.core.Holder
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.component.ItemLore
import net.minecraft.world.level.block.Block
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredItem
import net.neoforged.neoforge.registries.DeferredRegister

object AlloyanceItems {
    val ITEMS = DeferredRegister.createItems(Alloyance.ID)

    val RAW_MATERIALS = mutableMapOf<Metal, DeferredItem<Item>>()
    val INGOTS = mutableMapOf<Metal, DeferredItem<Item>>()
    val NUGGETS = mutableMapOf<Metal, DeferredItem<Item>>()
    val DUSTS = mutableMapOf<Metal, DeferredItem<Item>>()

    val ALLOYER = block(AlloyanceBlocks.ALLOYER)
    val CRUSHER = block(AlloyanceBlocks.CRUSHER)

    val COPPER_DUST = ITEMS.registerSimpleItem("copper_dust")
    val IRON_DUST = ITEMS.registerSimpleItem("iron_dust")
    val GOLD_DUST = ITEMS.registerSimpleItem("gold_dust")

    // TODO fireproof?
    // TODO tags?
    val INFUSED_IGNATIUS = ITEMS.registerSimpleItem("infused_ignatius", Item.Properties().lore("tooltip.alloyance.infused_ignatius"))
    val THERMITE_DUST = ITEMS.registerSimpleItem("thermite_dust", Item.Properties().lore("tooltip.alloyance.thermite_dust"))

    val DEEP_IRON_ORE = block(AlloyanceBlocks.DEEP_IRON_ORE)
    val DEEPSLATE_DEEP_IRON_ORE = block(AlloyanceBlocks.DEEPSLATE_DEEP_IRON_ORE)
    val DEEP_IRON_BLOCK = block(AlloyanceBlocks.DEEP_IRON_BLOCK)
    val RAW_DEEP_IRON = rawMaterial(DEEP_IRON)
    val DEEP_IRON_INGOT = ingot(DEEP_IRON)
    val DEEP_IRON_NUGGET = nugget(DEEP_IRON)
    val DEEP_IRON_DUST = dust(DEEP_IRON)

    val PROMETHEUM_ORE = block(AlloyanceBlocks.PROMETHEUM_ORE)
    val DEEPSLATE_PROMETHEUM_ORE = block(AlloyanceBlocks.DEEPSLATE_PROMETHEUM_ORE)
    val PROMETHEUM_BLOCK = block(AlloyanceBlocks.PROMETHEUM_BLOCK)
    val RAW_PROMETHEUM = rawMaterial(PROMETHEUM)
    val PROMETHEUM_INGOT = ingot(PROMETHEUM)
    val PROMETHEUM_NUGGET = nugget(PROMETHEUM)
    val PROMETHEUM_DUST = dust(PROMETHEUM)

    val ZINC_ORE = block(AlloyanceBlocks.ZINC_ORE)
    val DEEPSLATE_ZINC_ORE = block(AlloyanceBlocks.DEEPSLATE_ZINC_ORE)
    val ZINC_BLOCK = block(AlloyanceBlocks.ZINC_BLOCK)
    val RAW_ZINC = rawMaterial(ZINC)
    val ZINC_INGOT = ingot(ZINC)
    val ZINC_NUGGET = nugget(ZINC)
    val ZINC_DUST = dust(ZINC)

    val TIN_ORE = block(AlloyanceBlocks.TIN_ORE)
    val TIN_BLOCK = block(AlloyanceBlocks.TIN_BLOCK)
    val RAW_TIN = rawMaterial(TIN)
    val TIN_INGOT = ingot(TIN)
    val TIN_NUGGET = nugget(TIN)
    val TIN_DUST = dust(TIN)

    val BRONZE_BLOCK = block(AlloyanceBlocks.BRONZE_BLOCK)
    val BRONZE_INGOT = ingot(BRONZE)
    val BRONZE_NUGGET = nugget(BRONZE)
    val BRONZE_DUST = dust(BRONZE)

    val BRASS_BLOCK = block(AlloyanceBlocks.BRASS_BLOCK)
    val BRASS_INGOT = ingot(BRASS)
    val BRASS_NUGGET = nugget(BRASS)
    val BRASS_DUST = dust(BRASS)

    fun register(bus: IEventBus) {
        ITEMS.register(bus)
    }

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

    private fun dust(metal: Metal): DeferredItem<Item> {
        val item = ITEMS.registerSimpleItem("${metal.id}_dust")
        DUSTS[metal] = item
        return item
    }
}

private fun Item.Properties.lore(translationKey: String): Item.Properties {
    return component(
        DataComponents.LORE, ItemLore(listOf(Component.translatable(translationKey)))
    )
}
