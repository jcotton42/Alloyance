package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.client.TooltipStyle
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

    val POTASH_ORE = block(AlloyanceBlocks.POTASH_ORE)
    val DEEPSLATE_POTASH_ORE = block(AlloyanceBlocks.DEEPSLATE_POTASH_ORE)
    val POTASH_BLOCK = block(AlloyanceBlocks.POTASH_BLOCK)
    val POTASH = ITEMS.registerSimpleItem("potash")

    val DEEPSLATE_SULFUR_ORE = block(AlloyanceBlocks.DEEPSLATE_SULFUR_ORE)
    val SULFUR_BLOCK = block(AlloyanceBlocks.SULFUR_BLOCK)
    val SULFUR = ITEMS.registerSimpleItem("sulfur")

    // TODO fireproof?
    // TODO tags?
    val INFUSED_IGNATIUS = ITEMS.registerSimpleItem("infused_ignatius", Item.Properties().lore("tooltip.alloyance.infused_ignatius"))
    val THERMITE_DUST = ITEMS.registerSimpleItem("thermite_dust", Item.Properties().lore("tooltip.alloyance.thermite_dust"))

    val DEEP_IRON_ORE = block(AlloyanceBlocks.DEEP_IRON_ORE, DEEP_IRON)
    val DEEPSLATE_DEEP_IRON_ORE = block(AlloyanceBlocks.DEEPSLATE_DEEP_IRON_ORE, DEEP_IRON)
    val DEEP_IRON_BLOCK = block(AlloyanceBlocks.DEEP_IRON_BLOCK, DEEP_IRON)
    val RAW_DEEP_IRON = rawMaterial(DEEP_IRON)
    val DEEP_IRON_INGOT = ingot(DEEP_IRON)
    val DEEP_IRON_NUGGET = nugget(DEEP_IRON)
    val DEEP_IRON_DUST = dust(DEEP_IRON)

    val PROMETHEUM_ORE = block(AlloyanceBlocks.PROMETHEUM_ORE, PROMETHEUM)
    val DEEPSLATE_PROMETHEUM_ORE = block(AlloyanceBlocks.DEEPSLATE_PROMETHEUM_ORE, PROMETHEUM)
    val PROMETHEUM_BLOCK = block(AlloyanceBlocks.PROMETHEUM_BLOCK, PROMETHEUM)
    val RAW_PROMETHEUM = rawMaterial(PROMETHEUM)
    val PROMETHEUM_INGOT = ingot(PROMETHEUM)
    val PROMETHEUM_NUGGET = nugget(PROMETHEUM)
    val PROMETHEUM_DUST = dust(PROMETHEUM)

    val ZINC_ORE = block(AlloyanceBlocks.ZINC_ORE, ZINC)
    val DEEPSLATE_ZINC_ORE = block(AlloyanceBlocks.DEEPSLATE_ZINC_ORE, ZINC)
    val ZINC_BLOCK = block(AlloyanceBlocks.ZINC_BLOCK, ZINC)
    val RAW_ZINC = rawMaterial(ZINC)
    val ZINC_INGOT = ingot(ZINC)
    val ZINC_NUGGET = nugget(ZINC)
    val ZINC_DUST = dust(ZINC)

    val TIN_ORE = block(AlloyanceBlocks.TIN_ORE, TIN)
    val TIN_BLOCK = block(AlloyanceBlocks.TIN_BLOCK, TIN)
    val RAW_TIN = rawMaterial(TIN)
    val TIN_INGOT = ingot(TIN)
    val TIN_NUGGET = nugget(TIN)
    val TIN_DUST = dust(TIN)

    val BRONZE_BLOCK = block(AlloyanceBlocks.BRONZE_BLOCK, BRONZE)
    val BRONZE_INGOT = ingot(BRONZE)
    val BRONZE_NUGGET = nugget(BRONZE)
    val BRONZE_DUST = dust(BRONZE)

    val BRASS_BLOCK = block(AlloyanceBlocks.BRASS_BLOCK, BRASS)
    val BRASS_INGOT = ingot(BRASS)
    val BRASS_NUGGET = nugget(BRASS)
    val BRASS_DUST = dust(BRASS)

    val DAMASCUS_STEEL_BLOCK = block(AlloyanceBlocks.DAMASCUS_STEEL_BLOCK, DAMASCUS_STEEL)
    val DAMASCUS_STEEL_INGOT = ingot(DAMASCUS_STEEL)
    val DAMASCUS_STEEL_NUGGET = nugget(DAMASCUS_STEEL)
    val DAMASCUS_STEEL_DUST = dust(DAMASCUS_STEEL)

    val DEEPSLATE_OSMIUM_ORE = block(AlloyanceBlocks.DEEPSLATE_OSMIUM_ORE, OSMIUM)
    val OSMIUM_BLOCK = block(AlloyanceBlocks.OSMIUM_BLOCK, OSMIUM)
    val RAW_OSMIUM = rawMaterial(OSMIUM)
    val OSMIUM_INGOT = ingot(OSMIUM)
    val OSMIUM_NUGGET = nugget(OSMIUM)
    val OSMIUM_DUST = dust(OSMIUM)

    val SILVER_ORE = block(AlloyanceBlocks.SILVER_ORE, SILVER)
    val DEEPSLATE_SILVER_ORE = block(AlloyanceBlocks.DEEPSLATE_SILVER_ORE, SILVER)
    val SILVER_BLOCK = block(AlloyanceBlocks.SILVER_BLOCK, SILVER)
    val RAW_SILVER = rawMaterial(SILVER)
    val SILVER_INGOT = ingot(SILVER)
    val SILVER_NUGGET = nugget(SILVER)
    val SILVER_DUST = dust(SILVER)

    val INFUSCOLIUM_ORE = block(AlloyanceBlocks.INFUSCOLIUM_ORE, INFUSCOLIUM)
    val DEEPSLATE_INFUSCOLIUM_ORE = block(AlloyanceBlocks.DEEPSLATE_INFUSCOLIUM_ORE, INFUSCOLIUM)
    val INFUSCOLIUM_BLOCK = block(AlloyanceBlocks.INFUSCOLIUM_BLOCK, INFUSCOLIUM)
    val RAW_INFUSCOLIUM = rawMaterial(INFUSCOLIUM)
    val INFUSCOLIUM_INGOT = ingot(INFUSCOLIUM)
    val INFUSCOLIUM_NUGGET = nugget(INFUSCOLIUM)
    val INFUSCOLIUM_DUST = dust(INFUSCOLIUM)

    val MANGANESE_ORE = block(AlloyanceBlocks.MANGANESE_ORE, MANGANESE)
    val DEEPSLATE_MANGANESE_ORE = block(AlloyanceBlocks.DEEPSLATE_MANGANESE_ORE, MANGANESE)
    val MANGANESE_BLOCK = block(AlloyanceBlocks.MANGANESE_BLOCK, MANGANESE)
    val RAW_MANGANESE = rawMaterial(MANGANESE)
    val MANGANESE_INGOT = ingot(MANGANESE)
    val MANGANESE_NUGGET = nugget(MANGANESE)
    val MANGANESE_DUST = dust(MANGANESE)

    val ANGMALLEN_BLOCK = block(AlloyanceBlocks.ANGMALLEN_BLOCK, ANGMALLEN)
    val ANGMALLEN_INGOT = ingot(ANGMALLEN)
    val ANGMALLEN_NUGGET = nugget(ANGMALLEN)
    val ANGMALLEN_DUST = dust(ANGMALLEN)

    val STEEL_BLOCK = block(AlloyanceBlocks.STEEL_BLOCK, STEEL)
    val STEEL_INGOT = ingot(STEEL)
    val STEEL_NUGGET = nugget(STEEL)
    val STEEL_DUST = dust(STEEL)

    val HEPATIZON_BLOCK = block(AlloyanceBlocks.HEPATIZON_BLOCK, HEPATIZON)
    val HEPATIZON_INGOT = ingot(HEPATIZON)
    val HEPATIZON_NUGGET = nugget(HEPATIZON)
    val HEPATIZON_DUST = dust(HEPATIZON)

    val BLACK_STEEL_BLOCK = block(AlloyanceBlocks.BLACK_STEEL_BLOCK, BLACK_STEEL)
    val BLACK_STEEL_INGOT = ingot(BLACK_STEEL)
    val BLACK_STEEL_NUGGET = nugget(BLACK_STEEL)
    val BLACK_STEEL_DUST = dust(BLACK_STEEL)

    val ELECTRUM_BLOCK = block(AlloyanceBlocks.ELECTRUM_BLOCK, ELECTRUM)
    val ELECTRUM_INGOT = ingot(ELECTRUM)
    val ELECTRUM_NUGGET = nugget(ELECTRUM)
    val ELECTRUM_DUST = dust(ELECTRUM)

    val ASTRAL_SILVER_ORE = block(AlloyanceBlocks.ASTRAL_SILVER_ORE, ASTRAL_SILVER)
    val ASTRAL_SILVER_BLOCK = block(AlloyanceBlocks.ASTRAL_SILVER_BLOCK, ASTRAL_SILVER)
    val RAW_ASTRAL_SILVER = rawMaterial(ASTRAL_SILVER)
    val ASTRAL_SILVER_INGOT = ingot(ASTRAL_SILVER)
    val ASTRAL_SILVER_NUGGET = nugget(ASTRAL_SILVER)
    val ASTRAL_SILVER_DUST = dust(ASTRAL_SILVER)

    val NETHER_IGNATIUS_ORE = block(AlloyanceBlocks.NETHER_IGNATIUS_ORE, IGNATIUS)
    val IGNATIUS_BLOCK = block(AlloyanceBlocks.IGNATIUS_BLOCK, IGNATIUS)
    val RAW_IGNATIUS = rawMaterial(IGNATIUS)
    val IGNATIUS_INGOT = ingot(IGNATIUS)
    val IGNATIUS_NUGGET = nugget(IGNATIUS)
    val IGNATIUS_DUST = dust(IGNATIUS)

    val OURECLASE_ORE = block(AlloyanceBlocks.OURECLASE_ORE, OURECLASE)
    val DEEPSLATE_OURECLASE_ORE = block(AlloyanceBlocks.DEEPSLATE_OURECLASE_ORE, OURECLASE)
    val OURECLASE_BLOCK = block(AlloyanceBlocks.OURECLASE_BLOCK, OURECLASE)
    val RAW_OURECLASE = rawMaterial(OURECLASE)
    val OURECLASE_INGOT = ingot(OURECLASE)
    val OURECLASE_NUGGET = nugget(OURECLASE)
    val OURECLASE_DUST = dust(OURECLASE)

    val RUBRACIUM_ORE = block(AlloyanceBlocks.RUBRACIUM_ORE, RUBRACIUM)
    val DEEPSLATE_RUBRACIUM_ORE = block(AlloyanceBlocks.DEEPSLATE_RUBRACIUM_ORE, RUBRACIUM)
    val RUBRACIUM_BLOCK = block(AlloyanceBlocks.RUBRACIUM_BLOCK, RUBRACIUM)
    val RAW_RUBRACIUM = rawMaterial(RUBRACIUM)
    val RUBRACIUM_INGOT = ingot(RUBRACIUM)
    val RUBRACIUM_NUGGET = nugget(RUBRACIUM)
    val RUBRACIUM_DUST = dust(RUBRACIUM)

    val NETHER_SHADOW_IRON_ORE = block(AlloyanceBlocks.NETHER_SHADOW_IRON_ORE, SHADOW_IRON)
    val SHADOW_IRON_BLOCK = block(AlloyanceBlocks.SHADOW_IRON_BLOCK, SHADOW_IRON)
    val RAW_SHADOW_IRON = rawMaterial(SHADOW_IRON)
    val SHADOW_IRON_INGOT = ingot(SHADOW_IRON)
    val SHADOW_IRON_NUGGET = nugget(SHADOW_IRON)
    val SHADOW_IRON_DUST = dust(SHADOW_IRON)

    val QUICKSILVER_BLOCK = block(AlloyanceBlocks.QUICKSILVER_BLOCK, QUICKSILVER)
    val QUICKSILVER_INGOT = ingot(QUICKSILVER)
    val QUICKSILVER_NUGGET = nugget(QUICKSILVER)
    val QUICKSILVER_DUST = dust(QUICKSILVER)

    val NETHER_CERUCLASE_ORE = block(AlloyanceBlocks.NETHER_CERUCLASE_ORE, CERUCLASE)
    val CERUCLASE_BLOCK = block(AlloyanceBlocks.CERUCLASE_BLOCK, CERUCLASE)
    val RAW_CERUCLASE = rawMaterial(CERUCLASE)
    val CERUCLASE_INGOT = ingot(CERUCLASE)
    val CERUCLASE_NUGGET = nugget(CERUCLASE)
    val CERUCLASE_DUST = dust(CERUCLASE)

    val END_EXIMITE_ORE = block(AlloyanceBlocks.END_EXIMITE_ORE, EXIMITE)
    val EXIMITE_BLOCK = block(AlloyanceBlocks.EXIMITE_BLOCK, EXIMITE)
    val RAW_EXIMITE = rawMaterial(EXIMITE)
    val EXIMITE_INGOT = ingot(EXIMITE)
    val EXIMITE_NUGGET = nugget(EXIMITE)
    val EXIMITE_DUST = dust(EXIMITE)

    val NETHER_KALENDRITE_ORE = block(AlloyanceBlocks.NETHER_KALENDRITE_ORE, KALENDRITE)
    val KALENDRITE_BLOCK = block(AlloyanceBlocks.KALENDRITE_BLOCK, KALENDRITE)
    val RAW_KALENDRITE = rawMaterial(KALENDRITE)
    val KALENDRITE_INGOT = ingot(KALENDRITE)
    val KALENDRITE_NUGGET = nugget(KALENDRITE)
    val KALENDRITE_DUST = dust(KALENDRITE)

    val NETHER_MIDASIUM_ORE = block(AlloyanceBlocks.NETHER_MIDASIUM_ORE, MIDASIUM)
    val MIDASIUM_BLOCK = block(AlloyanceBlocks.MIDASIUM_BLOCK, MIDASIUM)
    val RAW_MIDASIUM = rawMaterial(MIDASIUM)
    val MIDASIUM_INGOT = ingot(MIDASIUM)
    val MIDASIUM_NUGGET = nugget(MIDASIUM)
    val MIDASIUM_DUST = dust(MIDASIUM)

    val ORICHALCUM_ORE = block(AlloyanceBlocks.ORICHALCUM_ORE, ORICHALCUM)
    val DEEPSLATE_ORICHALCUM_ORE = block(AlloyanceBlocks.DEEPSLATE_ORICHALCUM_ORE, ORICHALCUM)
    val ORICHALCUM_BLOCK = block(AlloyanceBlocks.ORICHALCUM_BLOCK, ORICHALCUM)
    val RAW_ORICHALCUM = rawMaterial(ORICHALCUM)
    val ORICHALCUM_INGOT = ingot(ORICHALCUM)
    val ORICHALCUM_NUGGET = nugget(ORICHALCUM)
    val ORICHALCUM_DUST = dust(ORICHALCUM)

    val PLATINUM_ORE = block(AlloyanceBlocks.PLATINUM_ORE, PLATINUM)
    val DEEPSLATE_PLATINUM_ORE = block(AlloyanceBlocks.DEEPSLATE_PLATINUM_ORE, PLATINUM)
    val PLATINUM_BLOCK = block(AlloyanceBlocks.PLATINUM_BLOCK, PLATINUM)
    val RAW_PLATINUM = rawMaterial(PLATINUM)
    val PLATINUM_INGOT = ingot(PLATINUM)
    val PLATINUM_NUGGET = nugget(PLATINUM)
    val PLATINUM_DUST = dust(PLATINUM)

    val NETHER_VULCANITE_ORE = block(AlloyanceBlocks.NETHER_VULCANITE_ORE, VULCANITE)
    val VULCANITE_BLOCK = block(AlloyanceBlocks.VULCANITE_BLOCK, VULCANITE)
    val RAW_VULCANITE = rawMaterial(VULCANITE)
    val VULCANITE_INGOT = ingot(VULCANITE)
    val VULCANITE_NUGGET = nugget(VULCANITE)
    val VULCANITE_DUST = dust(VULCANITE)

    val CELENEGIL_BLOCK = block(AlloyanceBlocks.CELENEGIL_BLOCK, CELENEGIL)
    val CELENEGIL_INGOT = ingot(CELENEGIL)
    val CELENEGIL_NUGGET = nugget(CELENEGIL)
    val CELENEGIL_DUST = dust(CELENEGIL)

    val AMORDRINE_BLOCK = block(AlloyanceBlocks.AMORDRINE_BLOCK, AMORDRINE)
    val AMORDRINE_INGOT = ingot(AMORDRINE)
    val AMORDRINE_NUGGET = nugget(AMORDRINE)
    val AMORDRINE_DUST = dust(AMORDRINE)

    val CARMOT_ORE = block(AlloyanceBlocks.CARMOT_ORE, CARMOT)
    val DEEPSLATE_CARMOT_ORE = block(AlloyanceBlocks.DEEPSLATE_CARMOT_ORE, CARMOT)
    val CARMOT_BLOCK = block(AlloyanceBlocks.CARMOT_BLOCK, CARMOT)
    val RAW_CARMOT = rawMaterial(CARMOT)
    val CARMOT_INGOT = ingot(CARMOT)
    val CARMOT_NUGGET = nugget(CARMOT)
    val CARMOT_DUST = dust(CARMOT)

    val NETHER_LEMURITE_ORE = block(AlloyanceBlocks.NETHER_LEMURITE_ORE, LEMURITE)
    val LEMURITE_BLOCK = block(AlloyanceBlocks.LEMURITE_BLOCK, LEMURITE)
    val RAW_LEMURITE = rawMaterial(LEMURITE)
    val LEMURITE_INGOT = ingot(LEMURITE)
    val LEMURITE_NUGGET = nugget(LEMURITE)
    val LEMURITE_DUST = dust(LEMURITE)

    val END_MEUTOITE_ORE = block(AlloyanceBlocks.END_MEUTOITE_ORE, MEUTOITE)
    val MEUTOITE_BLOCK = block(AlloyanceBlocks.MEUTOITE_BLOCK, MEUTOITE)
    val RAW_MEUTOITE = rawMaterial(MEUTOITE)
    val MEUTOITE_INGOT = ingot(MEUTOITE)
    val MEUTOITE_NUGGET = nugget(MEUTOITE)
    val MEUTOITE_DUST = dust(MEUTOITE)

    val MITHRIL_ORE = block(AlloyanceBlocks.MITHRIL_ORE, MITHRIL)
    val MITHRIL_BLOCK = block(AlloyanceBlocks.MITHRIL_BLOCK, MITHRIL)
    val RAW_MITHRIL = rawMaterial(MITHRIL)
    val MITHRIL_INGOT = ingot(MITHRIL)
    val MITHRIL_NUGGET = nugget(MITHRIL)
    val MITHRIL_DUST = dust(MITHRIL)

    val NETHER_SANGUINITE_ORE = block(AlloyanceBlocks.NETHER_SANGUINITE_ORE, SANGUINITE)
    val SANGUINITE_BLOCK = block(AlloyanceBlocks.SANGUINITE_BLOCK, SANGUINITE)
    val RAW_SANGUINITE = rawMaterial(SANGUINITE)
    val SANGUINITE_INGOT = ingot(SANGUINITE)
    val SANGUINITE_NUGGET = nugget(SANGUINITE)
    val SANGUINITE_DUST = dust(SANGUINITE)

    val NETHER_VYROXERES_ORE = block(AlloyanceBlocks.NETHER_VYROXERES_ORE, VYROXERES)
    val VYROXERES_BLOCK = block(AlloyanceBlocks.VYROXERES_BLOCK, VYROXERES)
    val RAW_VYROXERES = rawMaterial(VYROXERES)
    val VYROXERES_INGOT = ingot(VYROXERES)
    val VYROXERES_NUGGET = nugget(VYROXERES)
    val VYROXERES_DUST = dust(VYROXERES)

    val SHADOW_STEEL_BLOCK = block(AlloyanceBlocks.SHADOW_STEEL_BLOCK, SHADOW_STEEL)
    val SHADOW_STEEL_INGOT = ingot(SHADOW_STEEL)
    val SHADOW_STEEL_NUGGET = nugget(SHADOW_STEEL)
    val SHADOW_STEEL_DUST = dust(SHADOW_STEEL)

    val HADEROTH_BLOCK = block(AlloyanceBlocks.HADEROTH_BLOCK, HADEROTH)
    val HADEROTH_INGOT = ingot(HADEROTH)
    val HADEROTH_NUGGET = nugget(HADEROTH)
    val HADEROTH_DUST = dust(HADEROTH)

    val DESICHALKOS_BLOCK = block(AlloyanceBlocks.DESICHALKOS_BLOCK, DESICHALKOS)
    val DESICHALKOS_INGOT = ingot(DESICHALKOS)
    val DESICHALKOS_NUGGET = nugget(DESICHALKOS)
    val DESICHALKOS_DUST = dust(DESICHALKOS)

    val ATLARUS_ORE = block(AlloyanceBlocks.ATLARUS_ORE, ATLARUS)
    val DEEPSLATE_ATLARUS_ORE = block(AlloyanceBlocks.DEEPSLATE_ATLARUS_ORE, ATLARUS)
    val ATLARUS_BLOCK = block(AlloyanceBlocks.ATLARUS_BLOCK, ATLARUS)
    val RAW_ATLARUS = rawMaterial(ATLARUS)
    val ATLARUS_INGOT = ingot(ATLARUS)
    val ATLARUS_NUGGET = nugget(ATLARUS)
    val ATLARUS_DUST = dust(ATLARUS)

    val ADAMANTINE_ORE = block(AlloyanceBlocks.ADAMANTINE_ORE, ADAMANTINE)
    val DEEPSLATE_ADAMANTINE_ORE = block(AlloyanceBlocks.DEEPSLATE_ADAMANTINE_ORE, ADAMANTINE)
    val ADAMANTINE_BLOCK = block(AlloyanceBlocks.ADAMANTINE_BLOCK, ADAMANTINE)
    val RAW_ADAMANTINE = rawMaterial(ADAMANTINE)
    val ADAMANTINE_INGOT = ingot(ADAMANTINE)
    val ADAMANTINE_NUGGET = nugget(ADAMANTINE)
    val ADAMANTINE_DUST = dust(ADAMANTINE)

    val NETHER_ALDUORITE_ORE = block(AlloyanceBlocks.NETHER_ALDUORITE_ORE, ALDUORITE)
    val ALDUORITE_BLOCK = block(AlloyanceBlocks.ALDUORITE_BLOCK, ALDUORITE)
    val RAW_ALDUORITE = rawMaterial(ALDUORITE)
    val ALDUORITE_INGOT = ingot(ALDUORITE)
    val ALDUORITE_NUGGET = nugget(ALDUORITE)
    val ALDUORITE_DUST = dust(ALDUORITE)

    val NETHER_LUTETIUM_ORE = block(AlloyanceBlocks.NETHER_LUTETIUM_ORE, LUTETIUM)
    val LUTETIUM_BLOCK = block(AlloyanceBlocks.LUTETIUM_BLOCK, LUTETIUM)
    val RAW_LUTETIUM = rawMaterial(LUTETIUM)
    val LUTETIUM_INGOT = ingot(LUTETIUM)
    val LUTETIUM_NUGGET = nugget(LUTETIUM)
    val LUTETIUM_DUST = dust(LUTETIUM)

    val INOLASHITE_BLOCK = block(AlloyanceBlocks.INOLASHITE_BLOCK, INOLASHITE)
    val INOLASHITE_INGOT = ingot(INOLASHITE)
    val INOLASHITE_NUGGET = nugget(INOLASHITE)
    val INOLASHITE_DUST = dust(INOLASHITE)

    val KRIK_BLOCK = block(AlloyanceBlocks.KRIK_BLOCK, KRIK)
    val KRIK_INGOT = ingot(KRIK)
    val KRIK_NUGGET = nugget(KRIK)
    val KRIK_DUST = dust(KRIK)

    val TARTARITE_BLOCK = block(AlloyanceBlocks.TARTARITE_BLOCK, TARTARITE)
    val TARTARITE_INGOT = ingot(TARTARITE)
    val TARTARITE_NUGGET = nugget(TARTARITE)
    val TARTARITE_DUST = dust(TARTARITE)

    val ETHERIUM_BLOCK = block(AlloyanceBlocks.ETHERIUM_BLOCK, ETHERIUM)
    val ETHERIUM_INGOT = ingot(ETHERIUM)
    val ETHERIUM_NUGGET = nugget(ETHERIUM)
    val ETHERIUM_DUST = dust(ETHERIUM)

    fun register(bus: IEventBus) {
        ITEMS.register(bus)
    }

    private fun block(block: Holder<Block>): DeferredItem<BlockItem> {
        return ITEMS.registerSimpleBlockItem(block)
    }

    private fun block(block: Holder<Block>, metal: Metal): DeferredItem<BlockItem> {
        val blockName = block.unwrapKey().orElseThrow().location().path
        return ITEMS.register(blockName) { -> BlockItem(block.value(), Item.Properties().tooltipColor(metal.color)) }
    }

    private fun rawMaterial(metal: Metal): DeferredItem<Item> {
        val item = ITEMS.register("raw_${metal.id}") { -> Item(Item.Properties().tooltipColor(metal.color)) }
        RAW_MATERIALS[metal] = item
        return item
    }

    private fun ingot(metal: Metal): DeferredItem<Item> {
        val item = ITEMS.register("${metal.id}_ingot") { -> Item(Item.Properties().tooltipColor(metal.color)) }
        INGOTS[metal] = item
        return item
    }

    private fun nugget(metal: Metal): DeferredItem<Item> {
        val item = ITEMS.register("${metal.id}_nugget") { -> Item(Item.Properties().tooltipColor(metal.color)) }
        NUGGETS[metal] = item
        return item
    }

    private fun dust(metal: Metal): DeferredItem<Item> {
        val item = ITEMS.register("${metal.id}_dust") { -> Item(Item.Properties().tooltipColor(metal.color)) }
        DUSTS[metal] = item
        return item
    }
}

private fun Item.Properties.lore(translationKey: String): Item.Properties {
    return component(
        DataComponents.LORE, ItemLore(listOf(Component.translatable(translationKey)))
    )
}

private fun Item.Properties.tooltipColor(color: Int): Item.Properties {
    return component(
        AlloyanceDataComponents.TOOLTIP_STYLE, TooltipStyle(color)
    )
}
