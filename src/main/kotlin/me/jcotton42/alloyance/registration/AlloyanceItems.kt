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

    val DAMASCUS_STEEL_BLOCK = block(AlloyanceBlocks.DAMASCUS_STEEL_BLOCK)
    val DAMASCUS_STEEL_INGOT = ingot(DAMASCUS_STEEL)
    val DAMASCUS_STEEL_NUGGET = nugget(DAMASCUS_STEEL)
    val DAMASCUS_STEEL_DUST = dust(DAMASCUS_STEEL)

    val DEEPSLATE_OSMIUM_ORE = block(AlloyanceBlocks.DEEPSLATE_OSMIUM_ORE)
    val OSMIUM_BLOCK = block(AlloyanceBlocks.OSMIUM_BLOCK)
    val RAW_OSMIUM = rawMaterial(OSMIUM)
    val OSMIUM_INGOT = ingot(OSMIUM)
    val OSMIUM_NUGGET = nugget(OSMIUM)
    val OSMIUM_DUST = dust(OSMIUM)

    val SILVER_ORE = block(AlloyanceBlocks.SILVER_ORE)
    val DEEPSLATE_SILVER_ORE = block(AlloyanceBlocks.DEEPSLATE_SILVER_ORE)
    val SILVER_BLOCK = block(AlloyanceBlocks.SILVER_BLOCK)
    val RAW_SILVER = rawMaterial(SILVER)
    val SILVER_INGOT = ingot(SILVER)
    val SILVER_NUGGET = nugget(SILVER)
    val SILVER_DUST = dust(SILVER)

    val INFUSCOLIUM_ORE = block(AlloyanceBlocks.INFUSCOLIUM_ORE)
    val DEEPSLATE_INFUSCOLIUM_ORE = block(AlloyanceBlocks.DEEPSLATE_INFUSCOLIUM_ORE)
    val INFUSCOLIUM_BLOCK = block(AlloyanceBlocks.INFUSCOLIUM_BLOCK)
    val RAW_INFUSCOLIUM = rawMaterial(INFUSCOLIUM)
    val INFUSCOLIUM_INGOT = ingot(INFUSCOLIUM)
    val INFUSCOLIUM_NUGGET = nugget(INFUSCOLIUM)
    val INFUSCOLIUM_DUST = dust(INFUSCOLIUM)

    val MANGANESE_ORE = block(AlloyanceBlocks.MANGANESE_ORE)
    val DEEPSLATE_MANGANESE_ORE = block(AlloyanceBlocks.DEEPSLATE_MANGANESE_ORE)
    val MANGANESE_BLOCK = block(AlloyanceBlocks.MANGANESE_BLOCK)
    val RAW_MANGANESE = rawMaterial(MANGANESE)
    val MANGANESE_INGOT = ingot(MANGANESE)
    val MANGANESE_NUGGET = nugget(MANGANESE)
    val MANGANESE_DUST = dust(MANGANESE)

    val ANGMALLEN_BLOCK = block(AlloyanceBlocks.ANGMALLEN_BLOCK)
    val ANGMALLEN_INGOT = ingot(ANGMALLEN)
    val ANGMALLEN_NUGGET = nugget(ANGMALLEN)
    val ANGMALLEN_DUST = dust(ANGMALLEN)

    val STEEL_BLOCK = block(AlloyanceBlocks.STEEL_BLOCK)
    val STEEL_INGOT = ingot(STEEL)
    val STEEL_NUGGET = nugget(STEEL)
    val STEEL_DUST = dust(STEEL)

    val HEPATIZON_BLOCK = block(AlloyanceBlocks.HEPATIZON_BLOCK)
    val HEPATIZON_INGOT = ingot(HEPATIZON)
    val HEPATIZON_NUGGET = nugget(HEPATIZON)
    val HEPATIZON_DUST = dust(HEPATIZON)

    val BLACK_STEEL_BLOCK = block(AlloyanceBlocks.BLACK_STEEL_BLOCK)
    val BLACK_STEEL_INGOT = ingot(BLACK_STEEL)
    val BLACK_STEEL_NUGGET = nugget(BLACK_STEEL)
    val BLACK_STEEL_DUST = dust(BLACK_STEEL)

    val ELECTRUM_BLOCK = block(AlloyanceBlocks.ELECTRUM_BLOCK)
    val ELECTRUM_INGOT = ingot(ELECTRUM)
    val ELECTRUM_NUGGET = nugget(ELECTRUM)
    val ELECTRUM_DUST = dust(ELECTRUM)

    val ASTRAL_SILVER_ORE = block(AlloyanceBlocks.ASTRAL_SILVER_ORE)
    val ASTRAL_SILVER_BLOCK = block(AlloyanceBlocks.ASTRAL_SILVER_BLOCK)
    val RAW_ASTRAL_SILVER = rawMaterial(ASTRAL_SILVER)
    val ASTRAL_SILVER_INGOT = ingot(ASTRAL_SILVER)
    val ASTRAL_SILVER_NUGGET = nugget(ASTRAL_SILVER)
    val ASTRAL_SILVER_DUST = dust(ASTRAL_SILVER)

    val NETHER_IGNATIUS_ORE = block(AlloyanceBlocks.NETHER_IGNATIUS_ORE)
    val IGNATIUS_BLOCK = block(AlloyanceBlocks.IGNATIUS_BLOCK)
    val RAW_IGNATIUS = rawMaterial(IGNATIUS)
    val IGNATIUS_INGOT = ingot(IGNATIUS)
    val IGNATIUS_NUGGET = nugget(IGNATIUS)
    val IGNATIUS_DUST = dust(IGNATIUS)

    val OURECLASE_ORE = block(AlloyanceBlocks.OURECLASE_ORE)
    val DEEPSLATE_OURECLASE_ORE = block(AlloyanceBlocks.DEEPSLATE_OURECLASE_ORE)
    val OURECLASE_BLOCK = block(AlloyanceBlocks.OURECLASE_BLOCK)
    val RAW_OURECLASE = rawMaterial(OURECLASE)
    val OURECLASE_INGOT = ingot(OURECLASE)
    val OURECLASE_NUGGET = nugget(OURECLASE)
    val OURECLASE_DUST = dust(OURECLASE)

    val RUBRACIUM_ORE = block(AlloyanceBlocks.RUBRACIUM_ORE)
    val DEEPSLATE_RUBRACIUM_ORE = block(AlloyanceBlocks.DEEPSLATE_RUBRACIUM_ORE)
    val RUBRACIUM_BLOCK = block(AlloyanceBlocks.RUBRACIUM_BLOCK)
    val RAW_RUBRACIUM = rawMaterial(RUBRACIUM)
    val RUBRACIUM_INGOT = ingot(RUBRACIUM)
    val RUBRACIUM_NUGGET = nugget(RUBRACIUM)
    val RUBRACIUM_DUST = dust(RUBRACIUM)

    val NETHER_SHADOW_IRON_ORE = block(AlloyanceBlocks.NETHER_SHADOW_IRON_ORE)
    val SHADOW_IRON_BLOCK = block(AlloyanceBlocks.SHADOW_IRON_BLOCK)
    val RAW_SHADOW_IRON = rawMaterial(SHADOW_IRON)
    val SHADOW_IRON_INGOT = ingot(SHADOW_IRON)
    val SHADOW_IRON_NUGGET = nugget(SHADOW_IRON)
    val SHADOW_IRON_DUST = dust(SHADOW_IRON)

    val QUICKSILVER_BLOCK = block(AlloyanceBlocks.QUICKSILVER_BLOCK)
    val QUICKSILVER_INGOT = ingot(QUICKSILVER)
    val QUICKSILVER_NUGGET = nugget(QUICKSILVER)
    val QUICKSILVER_DUST = dust(QUICKSILVER)

    val NETHER_CERUCLASE_ORE = block(AlloyanceBlocks.NETHER_CERUCLASE_ORE)
    val CERUCLASE_BLOCK = block(AlloyanceBlocks.CERUCLASE_BLOCK)
    val RAW_CERUCLASE = rawMaterial(CERUCLASE)
    val CERUCLASE_INGOT = ingot(CERUCLASE)
    val CERUCLASE_NUGGET = nugget(CERUCLASE)
    val CERUCLASE_DUST = dust(CERUCLASE)

    val END_EXIMITE_ORE = block(AlloyanceBlocks.END_EXIMITE_ORE)
    val EXIMITE_BLOCK = block(AlloyanceBlocks.EXIMITE_BLOCK)
    val RAW_EXIMITE = rawMaterial(EXIMITE)
    val EXIMITE_INGOT = ingot(EXIMITE)
    val EXIMITE_NUGGET = nugget(EXIMITE)
    val EXIMITE_DUST = dust(EXIMITE)

    val NETHER_KALENDRITE_ORE = block(AlloyanceBlocks.NETHER_KALENDRITE_ORE)
    val KALENDRITE_BLOCK = block(AlloyanceBlocks.KALENDRITE_BLOCK)
    val RAW_KALENDRITE = rawMaterial(KALENDRITE)
    val KALENDRITE_INGOT = ingot(KALENDRITE)
    val KALENDRITE_NUGGET = nugget(KALENDRITE)
    val KALENDRITE_DUST = dust(KALENDRITE)

    val NETHER_MIDASIUM_ORE = block(AlloyanceBlocks.NETHER_MIDASIUM_ORE)
    val MIDASIUM_BLOCK = block(AlloyanceBlocks.MIDASIUM_BLOCK)
    val RAW_MIDASIUM = rawMaterial(MIDASIUM)
    val MIDASIUM_INGOT = ingot(MIDASIUM)
    val MIDASIUM_NUGGET = nugget(MIDASIUM)
    val MIDASIUM_DUST = dust(MIDASIUM)

    val ORICHALCUM_ORE = block(AlloyanceBlocks.ORICHALCUM_ORE)
    val DEEPSLATE_ORICHALCUM_ORE = block(AlloyanceBlocks.DEEPSLATE_ORICHALCUM_ORE)
    val ORICHALCUM_BLOCK = block(AlloyanceBlocks.ORICHALCUM_BLOCK)
    val RAW_ORICHALCUM = rawMaterial(ORICHALCUM)
    val ORICHALCUM_INGOT = ingot(ORICHALCUM)
    val ORICHALCUM_NUGGET = nugget(ORICHALCUM)
    val ORICHALCUM_DUST = dust(ORICHALCUM)

    fun register(bus: IEventBus) {
        ITEMS.register(bus)
    }

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
