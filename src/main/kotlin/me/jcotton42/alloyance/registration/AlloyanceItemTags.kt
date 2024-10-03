package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.Metal.*
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

object AlloyanceItemTags {
    val RAW_MATERIALS: MutableMap<Metal, TagKey<Item>> = mutableMapOf()
    val INGOTS: MutableMap<Metal, TagKey<Item>> = mutableMapOf()
    val NUGGETS: MutableMap<Metal, TagKey<Item>> = mutableMapOf()
    val DUSTS: MutableMap<Metal, TagKey<Item>> = mutableMapOf()
    val ALLOYABLES: MutableMap<Metal, TagKey<Item>> = mutableMapOf()

    val STORAGE_BLOCKS: MutableMap<Metal, TagKey<Item>> = mutableMapOf()
    val ORES: MutableMap<Metal, TagKey<Item>> = mutableMapOf()

    val ALLOYABLES_ROOT: TagKey<Item> = alloyance("alloyables")
    val ORES_IN_GROUND_END_STONE: TagKey<Item> = c("ores_in_ground/end_stone")

    val DUSTS_IRON: TagKey<Item> = c("dusts/iron")
    val ALLOYABLES_IRON: TagKey<Item> = alloyance("alloyables/iron")

    val DUSTS_GOLD: TagKey<Item> = c("dusts/gold")
    val ALLOYABLES_GOLD: TagKey<Item> = alloyance("alloyables/gold")

    val DUSTS_COPPER: TagKey<Item> = c("dusts/copper")
    val ALLOYABLES_COPPER: TagKey<Item> = alloyance("alloyables/copper")

    val RAW_MATERIALS_DEEP_IRON: TagKey<Item> = rawMaterials(DEEP_IRON)
    val INGOTS_DEEP_IRON: TagKey<Item> = ingots(DEEP_IRON)
    val NUGGETS_DEEP_IRON: TagKey<Item> = nuggets(DEEP_IRON)
    val DUSTS_DEEP_IRON: TagKey<Item> = dusts(DEEP_IRON)
    val STORAGE_BLOCKS_DEEP_IRON: TagKey<Item> = storageBlocks(DEEP_IRON)
    val ORES_DEEP_IRON: TagKey<Item> = ores(DEEP_IRON)
    val ALLOYABLES_DEEP_IRON: TagKey<Item> = alloyables(DEEP_IRON)

    val RAW_MATERIALS_PROMETHEUM: TagKey<Item> = rawMaterials(PROMETHEUM)
    val INGOTS_PROMETHEUM: TagKey<Item> = ingots(PROMETHEUM)
    val NUGGETS_PROMETHEUM: TagKey<Item> = nuggets(PROMETHEUM)
    val DUSTS_PROMETHEUM: TagKey<Item> = dusts(PROMETHEUM)
    val STORAGE_BLOCKS_PROMETHEUM: TagKey<Item> = storageBlocks(PROMETHEUM)
    val ORES_PROMETHEUM: TagKey<Item> = ores(PROMETHEUM)
    val ALLOYABLES_PROMETHEUM: TagKey<Item> = alloyables(PROMETHEUM)

    val RAW_MATERIALS_ZINC: TagKey<Item> = rawMaterials(ZINC)
    val INGOTS_ZINC: TagKey<Item> = ingots(ZINC)
    val NUGGETS_ZINC: TagKey<Item> = nuggets(ZINC)
    val DUSTS_ZINC: TagKey<Item> = dusts(ZINC)
    val STORAGE_BLOCKS_ZINC: TagKey<Item> = storageBlocks(ZINC)
    val ORES_ZINC: TagKey<Item> = ores(ZINC)
    val ALLOYABLES_ZINC: TagKey<Item> = alloyables(ZINC)

    val RAW_MATERIALS_TIN: TagKey<Item> = rawMaterials(TIN)
    val INGOTS_TIN: TagKey<Item> = ingots(TIN)
    val NUGGETS_TIN: TagKey<Item> = nuggets(TIN)
    val DUSTS_TIN: TagKey<Item> = dusts(TIN)
    val STORAGE_BLOCKS_TIN: TagKey<Item> = storageBlocks(TIN)
    val ORES_TIN: TagKey<Item> = ores(TIN)
    val ALLOYABLES_TIN: TagKey<Item> = alloyables(TIN)

    val INGOTS_BRONZE: TagKey<Item> = ingots(BRONZE)
    val NUGGETS_BRONZE: TagKey<Item> = nuggets(BRONZE)
    val DUSTS_BRONZE: TagKey<Item> = dusts(BRONZE)
    val STORAGE_BLOCKS_BRONZE: TagKey<Item> = storageBlocks(BRONZE)
    val ALLOYABLES_BRONZE: TagKey<Item> = alloyables(BRONZE)

    val INGOTS_BRASS: TagKey<Item> = ingots(BRASS)
    val NUGGETS_BRASS: TagKey<Item> = nuggets(BRASS)
    val DUSTS_BRASS: TagKey<Item> = dusts(BRASS)
    val STORAGE_BLOCKS_BRASS: TagKey<Item> = storageBlocks(BRASS)
    val ALLOYABLES_BRASS: TagKey<Item> = alloyables(BRASS)

    val INGOTS_DAMASCUS_STEEL: TagKey<Item> = ingots(DAMASCUS_STEEL)
    val NUGGETS_DAMASCUS_STEEL: TagKey<Item> = nuggets(DAMASCUS_STEEL)
    val DUSTS_DAMASCUS_STEEL: TagKey<Item> = dusts(DAMASCUS_STEEL)
    val STORAGE_BLOCKS_DAMASCUS_STEEL: TagKey<Item> = storageBlocks(DAMASCUS_STEEL)
    val ALLOYABLES_DAMASCUS_STEEL: TagKey<Item> = alloyables(DAMASCUS_STEEL)

    val RAW_MATERIALS_OSMIUM: TagKey<Item> = rawMaterials(OSMIUM)
    val INGOTS_OSMIUM: TagKey<Item> = ingots(OSMIUM)
    val NUGGETS_OSMIUM: TagKey<Item> = nuggets(OSMIUM)
    val DUSTS_OSMIUM: TagKey<Item> = dusts(OSMIUM)
    val STORAGE_BLOCKS_OSMIUM: TagKey<Item> = storageBlocks(OSMIUM)
    val ORES_OSMIUM: TagKey<Item> = ores(OSMIUM)
    val ALLOYABLES_OSMIUM: TagKey<Item> = alloyables(OSMIUM)

    val RAW_MATERIALS_SILVER: TagKey<Item> = rawMaterials(SILVER)
    val INGOTS_SILVER: TagKey<Item> = ingots(SILVER)
    val NUGGETS_SILVER: TagKey<Item> = nuggets(SILVER)
    val DUSTS_SILVER: TagKey<Item> = dusts(SILVER)
    val STORAGE_BLOCKS_SILVER: TagKey<Item> = storageBlocks(SILVER)
    val ORES_SILVER: TagKey<Item> = ores(SILVER)
    val ALLOYABLES_SILVER: TagKey<Item> = alloyables(SILVER)

    val RAW_MATERIALS_INFUSCOLIUM: TagKey<Item> = rawMaterials(INFUSCOLIUM)
    val INGOTS_INFUSCOLIUM: TagKey<Item> = ingots(INFUSCOLIUM)
    val NUGGETS_INFUSCOLIUM: TagKey<Item> = nuggets(INFUSCOLIUM)
    val DUSTS_INFUSCOLIUM: TagKey<Item> = dusts(INFUSCOLIUM)
    val STORAGE_BLOCKS_INFUSCOLIUM: TagKey<Item> = storageBlocks(INFUSCOLIUM)
    val ORES_INFUSCOLIUM: TagKey<Item> = ores(INFUSCOLIUM)
    val ALLOYABLES_INFUSCOLIUM: TagKey<Item> = alloyables(INFUSCOLIUM)

    val RAW_MATERIALS_MANGANESE: TagKey<Item> = rawMaterials(MANGANESE)
    val INGOTS_MANGANESE: TagKey<Item> = ingots(MANGANESE)
    val NUGGETS_MANGANESE: TagKey<Item> = nuggets(MANGANESE)
    val DUSTS_MANGANESE: TagKey<Item> = dusts(MANGANESE)
    val STORAGE_BLOCKS_MANGANESE: TagKey<Item> = storageBlocks(MANGANESE)
    val ORES_MANGANESE: TagKey<Item> = ores(MANGANESE)
    val ALLOYABLES_MANGANESE: TagKey<Item> = alloyables(MANGANESE)

    val INGOTS_ANGMALLEN: TagKey<Item> = ingots(ANGMALLEN)
    val NUGGETS_ANGMALLEN: TagKey<Item> = nuggets(ANGMALLEN)
    val DUSTS_ANGMALLEN: TagKey<Item> = dusts(ANGMALLEN)
    val STORAGE_BLOCKS_ANGMALLEN: TagKey<Item> = storageBlocks(ANGMALLEN)
    val ALLOYABLES_ANGMALLEN: TagKey<Item> = alloyables(ANGMALLEN)

    val INGOTS_STEEL: TagKey<Item> = ingots(STEEL)
    val NUGGETS_STEEL: TagKey<Item> = nuggets(STEEL)
    val DUSTS_STEEL: TagKey<Item> = dusts(STEEL)
    val STORAGE_BLOCKS_STEEL: TagKey<Item> = storageBlocks(STEEL)
    val ALLOYABLES_STEEL: TagKey<Item> = alloyables(STEEL)

    val INGOTS_HEPATIZON: TagKey<Item> = ingots(HEPATIZON)
    val NUGGETS_HEPATIZON: TagKey<Item> = nuggets(HEPATIZON)
    val DUSTS_HEPATIZON: TagKey<Item> = dusts(HEPATIZON)
    val STORAGE_BLOCKS_HEPATIZON: TagKey<Item> = storageBlocks(HEPATIZON)
    val ALLOYABLES_HEPATIZON: TagKey<Item> = alloyables(HEPATIZON)

    val INGOTS_BLACK_STEEL: TagKey<Item> = ingots(BLACK_STEEL)
    val NUGGETS_BLACK_STEEL: TagKey<Item> = nuggets(BLACK_STEEL)
    val DUSTS_BLACK_STEEL: TagKey<Item> = dusts(BLACK_STEEL)
    val STORAGE_BLOCKS_BLACK_STEEL: TagKey<Item> = storageBlocks(BLACK_STEEL)
    val ALLOYABLES_BLACK_STEEL: TagKey<Item> = alloyables(BLACK_STEEL)

    val INGOTS_ELECTRUM: TagKey<Item> = ingots(ELECTRUM)
    val NUGGETS_ELECTRUM: TagKey<Item> = nuggets(ELECTRUM)
    val DUSTS_ELECTRUM: TagKey<Item> = dusts(ELECTRUM)
    val STORAGE_BLOCKS_ELECTRUM: TagKey<Item> = storageBlocks(ELECTRUM)
    val ALLOYABLES_ELECTRUM: TagKey<Item> = alloyables(ELECTRUM)

    val RAW_MATERIALS_ASTRAL_SILVER: TagKey<Item> = rawMaterials(ASTRAL_SILVER)
    val INGOTS_ASTRAL_SILVER: TagKey<Item> = ingots(ASTRAL_SILVER)
    val NUGGETS_ASTRAL_SILVER: TagKey<Item> = nuggets(ASTRAL_SILVER)
    val DUSTS_ASTRAL_SILVER: TagKey<Item> = dusts(ASTRAL_SILVER)
    val STORAGE_BLOCKS_ASTRAL_SILVER: TagKey<Item> = storageBlocks(ASTRAL_SILVER)
    val ORES_ASTRAL_SILVER: TagKey<Item> = ores(ASTRAL_SILVER)
    val ALLOYABLES_ASTRAL_SILVER: TagKey<Item> = alloyables(ASTRAL_SILVER)

    val RAW_MATERIALS_IGNATIUS: TagKey<Item> = rawMaterials(IGNATIUS)
    val INGOTS_IGNATIUS: TagKey<Item> = ingots(IGNATIUS)
    val NUGGETS_IGNATIUS: TagKey<Item> = nuggets(IGNATIUS)
    val DUSTS_IGNATIUS: TagKey<Item> = dusts(IGNATIUS)
    val STORAGE_BLOCKS_IGNATIUS: TagKey<Item> = storageBlocks(IGNATIUS)
    val ORES_IGNATIUS: TagKey<Item> = ores(IGNATIUS)
    val ALLOYABLES_IGNATIUS: TagKey<Item> = alloyables(IGNATIUS)

    val RAW_MATERIALS_OURECLASE: TagKey<Item> = rawMaterials(OURECLASE)
    val INGOTS_OURECLASE: TagKey<Item> = ingots(OURECLASE)
    val NUGGETS_OURECLASE: TagKey<Item> = nuggets(OURECLASE)
    val DUSTS_OURECLASE: TagKey<Item> = dusts(OURECLASE)
    val STORAGE_BLOCKS_OURECLASE: TagKey<Item> = storageBlocks(OURECLASE)
    val ORES_OURECLASE: TagKey<Item> = ores(OURECLASE)
    val ALLOYABLES_OURECLASE: TagKey<Item> = alloyables(OURECLASE)

    val RAW_MATERIALS_RUBRACIUM: TagKey<Item> = rawMaterials(RUBRACIUM)
    val INGOTS_RUBRACIUM: TagKey<Item> = ingots(RUBRACIUM)
    val NUGGETS_RUBRACIUM: TagKey<Item> = nuggets(RUBRACIUM)
    val DUSTS_RUBRACIUM: TagKey<Item> = dusts(RUBRACIUM)
    val STORAGE_BLOCKS_RUBRACIUM: TagKey<Item> = storageBlocks(RUBRACIUM)
    val ORES_RUBRACIUM: TagKey<Item> = ores(RUBRACIUM)
    val ALLOYABLES_RUBRACIUM: TagKey<Item> = alloyables(RUBRACIUM)

    val RAW_MATERIALS_SHADOW_IRON: TagKey<Item> = rawMaterials(SHADOW_IRON)
    val INGOTS_SHADOW_IRON: TagKey<Item> = ingots(SHADOW_IRON)
    val NUGGETS_SHADOW_IRON: TagKey<Item> = nuggets(SHADOW_IRON)
    val DUSTS_SHADOW_IRON: TagKey<Item> = dusts(SHADOW_IRON)
    val STORAGE_BLOCKS_SHADOW_IRON: TagKey<Item> = storageBlocks(SHADOW_IRON)
    val ORES_SHADOW_IRON: TagKey<Item> = ores(SHADOW_IRON)
    val ALLOYABLES_SHADOW_IRON: TagKey<Item> = alloyables(SHADOW_IRON)

    val INGOTS_QUICKSILVER: TagKey<Item> = ingots(QUICKSILVER)
    val NUGGETS_QUICKSILVER: TagKey<Item> = nuggets(QUICKSILVER)
    val DUSTS_QUICKSILVER: TagKey<Item> = dusts(QUICKSILVER)
    val STORAGE_BLOCKS_QUICKSILVER: TagKey<Item> = storageBlocks(QUICKSILVER)
    val ALLOYABLES_QUICKSILVER: TagKey<Item> = alloyables(QUICKSILVER)

    val RAW_MATERIALS_CERUCLASE: TagKey<Item> = rawMaterials(CERUCLASE)
    val INGOTS_CERUCLASE: TagKey<Item> = ingots(CERUCLASE)
    val NUGGETS_CERUCLASE: TagKey<Item> = nuggets(CERUCLASE)
    val DUSTS_CERUCLASE: TagKey<Item> = dusts(CERUCLASE)
    val STORAGE_BLOCKS_CERUCLASE: TagKey<Item> = storageBlocks(CERUCLASE)
    val ORES_CERUCLASE: TagKey<Item> = ores(CERUCLASE)
    val ALLOYABLES_CERUCLASE: TagKey<Item> = alloyables(CERUCLASE)

    val RAW_MATERIALS_EXIMITE: TagKey<Item> = rawMaterials(EXIMITE)
    val INGOTS_EXIMITE: TagKey<Item> = ingots(EXIMITE)
    val NUGGETS_EXIMITE: TagKey<Item> = nuggets(EXIMITE)
    val DUSTS_EXIMITE: TagKey<Item> = dusts(EXIMITE)
    val STORAGE_BLOCKS_EXIMITE: TagKey<Item> = storageBlocks(EXIMITE)
    val ORES_EXIMITE: TagKey<Item> = ores(EXIMITE)
    val ALLOYABLES_EXIMITE: TagKey<Item> = alloyables(EXIMITE)

    val RAW_MATERIALS_KALENDRITE: TagKey<Item> = rawMaterials(KALENDRITE)
    val INGOTS_KALENDRITE: TagKey<Item> = ingots(KALENDRITE)
    val NUGGETS_KALENDRITE: TagKey<Item> = nuggets(KALENDRITE)
    val DUSTS_KALENDRITE: TagKey<Item> = dusts(KALENDRITE)
    val STORAGE_BLOCKS_KALENDRITE: TagKey<Item> = storageBlocks(KALENDRITE)
    val ORES_KALENDRITE: TagKey<Item> = ores(KALENDRITE)
    val ALLOYABLES_KALENDRITE: TagKey<Item> = alloyables(KALENDRITE)

    val RAW_MATERIALS_MIDASIUM: TagKey<Item> = rawMaterials(MIDASIUM)
    val INGOTS_MIDASIUM: TagKey<Item> = ingots(MIDASIUM)
    val NUGGETS_MIDASIUM: TagKey<Item> = nuggets(MIDASIUM)
    val DUSTS_MIDASIUM: TagKey<Item> = dusts(MIDASIUM)
    val STORAGE_BLOCKS_MIDASIUM: TagKey<Item> = storageBlocks(MIDASIUM)
    val ORES_MIDASIUM: TagKey<Item> = ores(MIDASIUM)
    val ALLOYABLES_MIDASIUM: TagKey<Item> = alloyables(MIDASIUM)

    val RAW_MATERIALS_ORICHALCUM: TagKey<Item> = rawMaterials(ORICHALCUM)
    val INGOTS_ORICHALCUM: TagKey<Item> = ingots(ORICHALCUM)
    val NUGGETS_ORICHALCUM: TagKey<Item> = nuggets(ORICHALCUM)
    val DUSTS_ORICHALCUM: TagKey<Item> = dusts(ORICHALCUM)
    val STORAGE_BLOCKS_ORICHALCUM: TagKey<Item> = storageBlocks(ORICHALCUM)
    val ORES_ORICHALCUM: TagKey<Item> = ores(ORICHALCUM)
    val ALLOYABLES_ORICHALCUM: TagKey<Item> = alloyables(ORICHALCUM)

    val RAW_MATERIALS_PLATINUM: TagKey<Item> = rawMaterials(PLATINUM)
    val INGOTS_PLATINUM: TagKey<Item> = ingots(PLATINUM)
    val NUGGETS_PLATINUM: TagKey<Item> = nuggets(PLATINUM)
    val DUSTS_PLATINUM: TagKey<Item> = dusts(PLATINUM)
    val STORAGE_BLOCKS_PLATINUM: TagKey<Item> = storageBlocks(PLATINUM)
    val ORES_PLATINUM: TagKey<Item> = ores(PLATINUM)
    val ALLOYABLES_PLATINUM: TagKey<Item> = alloyables(PLATINUM)

    val RAW_MATERIALS_VULCANITE: TagKey<Item> = rawMaterials(VULCANITE)
    val INGOTS_VULCANITE: TagKey<Item> = ingots(VULCANITE)
    val NUGGETS_VULCANITE: TagKey<Item> = nuggets(VULCANITE)
    val DUSTS_VULCANITE: TagKey<Item> = dusts(VULCANITE)
    val STORAGE_BLOCKS_VULCANITE: TagKey<Item> = storageBlocks(VULCANITE)
    val ORES_VULCANITE: TagKey<Item> = ores(VULCANITE)
    val ALLOYABLES_VULCANITE: TagKey<Item> = alloyables(VULCANITE)

    val INGOTS_CELENEGIL: TagKey<Item> = ingots(CELENEGIL)
    val NUGGETS_CELENEGIL: TagKey<Item> = nuggets(CELENEGIL)
    val DUSTS_CELENEGIL: TagKey<Item> = dusts(CELENEGIL)
    val STORAGE_BLOCKS_CELENEGIL: TagKey<Item> = storageBlocks(CELENEGIL)
    val ALLOYABLES_CELENEGIL: TagKey<Item> = alloyables(CELENEGIL)

    val INGOTS_AMORDRINE: TagKey<Item> = ingots(AMORDRINE)
    val NUGGETS_AMORDRINE: TagKey<Item> = nuggets(AMORDRINE)
    val DUSTS_AMORDRINE: TagKey<Item> = dusts(AMORDRINE)
    val STORAGE_BLOCKS_AMORDRINE: TagKey<Item> = storageBlocks(AMORDRINE)
    val ALLOYABLES_AMORDRINE: TagKey<Item> = alloyables(AMORDRINE)

    val RAW_MATERIALS_CARMOT: TagKey<Item> = rawMaterials(CARMOT)
    val INGOTS_CARMOT: TagKey<Item> = ingots(CARMOT)
    val NUGGETS_CARMOT: TagKey<Item> = nuggets(CARMOT)
    val DUSTS_CARMOT: TagKey<Item> = dusts(CARMOT)
    val STORAGE_BLOCKS_CARMOT: TagKey<Item> = storageBlocks(CARMOT)
    val ORES_CARMOT: TagKey<Item> = ores(CARMOT)
    val ALLOYABLES_CARMOT: TagKey<Item> = alloyables(CARMOT)

    val RAW_MATERIALS_LEMURITE: TagKey<Item> = rawMaterials(LEMURITE)
    val INGOTS_LEMURITE: TagKey<Item> = ingots(LEMURITE)
    val NUGGETS_LEMURITE: TagKey<Item> = nuggets(LEMURITE)
    val DUSTS_LEMURITE: TagKey<Item> = dusts(LEMURITE)
    val STORAGE_BLOCKS_LEMURITE: TagKey<Item> = storageBlocks(LEMURITE)
    val ORES_LEMURITE: TagKey<Item> = ores(LEMURITE)
    val ALLOYABLES_LEMURITE: TagKey<Item> = alloyables(LEMURITE)

    val RAW_MATERIALS_MEUTOITE: TagKey<Item> = rawMaterials(MEUTOITE)
    val INGOTS_MEUTOITE: TagKey<Item> = ingots(MEUTOITE)
    val NUGGETS_MEUTOITE: TagKey<Item> = nuggets(MEUTOITE)
    val DUSTS_MEUTOITE: TagKey<Item> = dusts(MEUTOITE)
    val STORAGE_BLOCKS_MEUTOITE: TagKey<Item> = storageBlocks(MEUTOITE)
    val ORES_MEUTOITE: TagKey<Item> = ores(MEUTOITE)
    val ALLOYABLES_MEUTOITE: TagKey<Item> = alloyables(MEUTOITE)

    val RAW_MATERIALS_MITHRIL: TagKey<Item> = rawMaterials(MITHRIL)
    val INGOTS_MITHRIL: TagKey<Item> = ingots(MITHRIL)
    val NUGGETS_MITHRIL: TagKey<Item> = nuggets(MITHRIL)
    val DUSTS_MITHRIL: TagKey<Item> = dusts(MITHRIL)
    val STORAGE_BLOCKS_MITHRIL: TagKey<Item> = storageBlocks(MITHRIL)
    val ORES_MITHRIL: TagKey<Item> = ores(MITHRIL)
    val ALLOYABLES_MITHRIL: TagKey<Item> = alloyables(MITHRIL)

    val RAW_MATERIALS_SANGUINITE: TagKey<Item> = rawMaterials(SANGUINITE)
    val INGOTS_SANGUINITE: TagKey<Item> = ingots(SANGUINITE)
    val NUGGETS_SANGUINITE: TagKey<Item> = nuggets(SANGUINITE)
    val DUSTS_SANGUINITE: TagKey<Item> = dusts(SANGUINITE)
    val STORAGE_BLOCKS_SANGUINITE: TagKey<Item> = storageBlocks(SANGUINITE)
    val ORES_SANGUINITE: TagKey<Item> = ores(SANGUINITE)
    val ALLOYABLES_SANGUINITE: TagKey<Item> = alloyables(SANGUINITE)

    val RAW_MATERIALS_VYROXERES: TagKey<Item> = rawMaterials(VYROXERES)
    val INGOTS_VYROXERES: TagKey<Item> = ingots(VYROXERES)
    val NUGGETS_VYROXERES: TagKey<Item> = nuggets(VYROXERES)
    val DUSTS_VYROXERES: TagKey<Item> = dusts(VYROXERES)
    val STORAGE_BLOCKS_VYROXERES: TagKey<Item> = storageBlocks(VYROXERES)
    val ORES_VYROXERES: TagKey<Item> = ores(VYROXERES)
    val ALLOYABLES_VYROXERES: TagKey<Item> = alloyables(VYROXERES)

    val INGOTS_SHADOW_STEEL: TagKey<Item> = ingots(SHADOW_STEEL)
    val NUGGETS_SHADOW_STEEL: TagKey<Item> = nuggets(SHADOW_STEEL)
    val DUSTS_SHADOW_STEEL: TagKey<Item> = dusts(SHADOW_STEEL)
    val STORAGE_BLOCKS_SHADOW_STEEL: TagKey<Item> = storageBlocks(SHADOW_STEEL)
    val ALLOYABLES_SHADOW_STEEL: TagKey<Item> = alloyables(SHADOW_STEEL)

    val INGOTS_HADEROTH: TagKey<Item> = ingots(HADEROTH)
    val NUGGETS_HADEROTH: TagKey<Item> = nuggets(HADEROTH)
    val DUSTS_HADEROTH: TagKey<Item> = dusts(HADEROTH)
    val STORAGE_BLOCKS_HADEROTH: TagKey<Item> = storageBlocks(HADEROTH)
    val ALLOYABLES_HADEROTH: TagKey<Item> = alloyables(HADEROTH)

    val INGOTS_DESICHALKOS: TagKey<Item> = ingots(DESICHALKOS)
    val NUGGETS_DESICHALKOS: TagKey<Item> = nuggets(DESICHALKOS)
    val DUSTS_DESICHALKOS: TagKey<Item> = dusts(DESICHALKOS)
    val STORAGE_BLOCKS_DESICHALKOS: TagKey<Item> = storageBlocks(DESICHALKOS)
    val ALLOYABLES_DESICHALKOS: TagKey<Item> = alloyables(DESICHALKOS)

    private fun rawMaterials(metal: Metal): TagKey<Item> {
        val tag = c("raw_materials/${metal.id}")
        RAW_MATERIALS[metal] = tag
        return tag
    }

    private fun ingots(metal: Metal): TagKey<Item> {
        val tag = c("ingots/${metal.id}")
        INGOTS[metal] = tag
        return tag
    }

    private fun nuggets(metal: Metal): TagKey<Item> {
        val tag = c("nuggets/${metal.id}")
        NUGGETS[metal] = tag
        return tag
    }

    private fun dusts(metal: Metal): TagKey<Item> {
        val tag = c("dusts/${metal.id}")
        DUSTS[metal] = tag
        return tag
    }

    private fun storageBlocks(metal: Metal): TagKey<Item> {
        val tag = c("storage_blocks/${metal.id}")
        STORAGE_BLOCKS[metal] = tag
        return tag
    }

    private fun ores(metal: Metal): TagKey<Item> {
        val tag = c("ores/${metal.id}")
        ORES[metal] = tag
        return tag
    }

    private fun alloyables(metal: Metal): TagKey<Item> {
        val tag = alloyance("alloyables/${metal.id}")
        ALLOYABLES[metal] = tag
        return tag
    }

    private fun alloyance(path: String): TagKey<Item> {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, path))
    }

    private fun c(path: String): TagKey<Item> {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", path))
    }
}
