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

    val STORAGE_BLOCKS: MutableMap<Metal, TagKey<Item>> = mutableMapOf()
    val ORES: MutableMap<Metal, TagKey<Item>> = mutableMapOf()

    val DUSTS_IRON: TagKey<Item> = c("dusts/iron")
    val DUSTS_GOLD: TagKey<Item> = c("dusts/gold")
    val DUSTS_COPPER: TagKey<Item> = c("dusts/copper")

    val RAW_MATERIALS_DEEP_IRON: TagKey<Item> = rawMaterials(DEEP_IRON)
    val INGOTS_DEEP_IRON: TagKey<Item> = ingots(DEEP_IRON)
    val NUGGETS_DEEP_IRON: TagKey<Item> = nuggets(DEEP_IRON)
    val DUSTS_DEEP_IRON: TagKey<Item> = dusts(DEEP_IRON)
    val STORAGE_BLOCKS_DEEP_IRON: TagKey<Item> = storageBlocks(DEEP_IRON)
    val ORES_DEEP_IRON: TagKey<Item> = ores(DEEP_IRON)

    val RAW_MATERIALS_PROMETHEUM: TagKey<Item> = rawMaterials(PROMETHEUM)
    val INGOTS_PROMETHEUM: TagKey<Item> = ingots(PROMETHEUM)
    val NUGGETS_PROMETHEUM: TagKey<Item> = nuggets(PROMETHEUM)
    val DUSTS_PROMETHEUM: TagKey<Item> = dusts(PROMETHEUM)
    val STORAGE_BLOCKS_PROMETHEUM: TagKey<Item> = storageBlocks(PROMETHEUM)
    val ORES_PROMETHEUM: TagKey<Item> = ores(PROMETHEUM)

    val RAW_MATERIALS_ZINC: TagKey<Item> = rawMaterials(ZINC)
    val INGOTS_ZINC: TagKey<Item> = ingots(ZINC)
    val NUGGETS_ZINC: TagKey<Item> = nuggets(ZINC)
    val DUSTS_ZINC: TagKey<Item> = dusts(ZINC)
    val STORAGE_BLOCKS_ZINC: TagKey<Item> = storageBlocks(ZINC)
    val ORES_ZINC: TagKey<Item> = ores(ZINC)

    val RAW_MATERIALS_TIN: TagKey<Item> = rawMaterials(TIN)
    val INGOTS_TIN: TagKey<Item> = ingots(TIN)
    val NUGGETS_TIN: TagKey<Item> = nuggets(TIN)
    val DUSTS_TIN: TagKey<Item> = dusts(TIN)
    val STORAGE_BLOCKS_TIN: TagKey<Item> = storageBlocks(TIN)
    val ORES_TIN: TagKey<Item> = ores(TIN)

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

    private fun alloyance(path: String): TagKey<Item> {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, path))
    }

    private fun c(path: String): TagKey<Item> {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", path))
    }
}