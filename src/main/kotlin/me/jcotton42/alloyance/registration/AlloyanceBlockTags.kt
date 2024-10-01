package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.Metal.*
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block

object AlloyanceBlockTags {
    val STORAGE_BLOCKS: MutableMap<Metal, TagKey<Block>> = mutableMapOf()
    val ORES: MutableMap<Metal, TagKey<Block>> = mutableMapOf()

    val ORES_IN_GROUND_END_STONE: TagKey<Block> = c("ores_in_ground/end_stone")

    val STORAGE_BLOCKS_DEEP_IRON: TagKey<Block> = storageBlocks(DEEP_IRON)
    val ORES_DEEP_IRON: TagKey<Block> = ores(DEEP_IRON)

    val STORAGE_BLOCKS_PROMETHEUM: TagKey<Block> = storageBlocks(PROMETHEUM)
    val ORES_PROMETHEUM: TagKey<Block> = ores(PROMETHEUM)

    val STORAGE_BLOCKS_ZINC: TagKey<Block> = storageBlocks(ZINC)
    val ORES_ZINC: TagKey<Block> = ores(ZINC)

    val STORAGE_BLOCKS_TIN: TagKey<Block> = storageBlocks(TIN)
    val ORES_TIN: TagKey<Block> = ores(TIN)

    val STORAGE_BLOCKS_BRONZE: TagKey<Block> = storageBlocks(BRONZE)

    val STORAGE_BLOCKS_BRASS: TagKey<Block> = storageBlocks(BRASS)

    val STORAGE_BLOCKS_DAMASCUS_STEEL: TagKey<Block> = storageBlocks(DAMASCUS_STEEL)

    val ORES_OSMIUM = ores(OSMIUM)
    val STORAGE_BLOCKS_OSMIUM = storageBlocks(OSMIUM)

    val ORES_SILVER = ores(SILVER)
    val STORAGE_BLOCKS_SILVER = storageBlocks(SILVER)

    val ORES_INFUSCOLIUM = ores(INFUSCOLIUM)
    val STORAGE_BLOCKS_INFUSCOLIUM = storageBlocks(INFUSCOLIUM)

    val ORES_MANGANESE = ores(MANGANESE)
    val STORAGE_BLOCKS_MANGANESE = storageBlocks(MANGANESE)

    val STORAGE_BLOCKS_ANGMALLEN = storageBlocks(ANGMALLEN)

    val STORAGE_BLOCKS_STEEL = storageBlocks(STEEL)

    val STORAGE_BLOCKS_HEPATIZON = storageBlocks(HEPATIZON)

    val STORAGE_BLOCKS_BLACK_STEEL = storageBlocks(BLACK_STEEL)

    val STORAGE_BLOCKS_ELECTRUM = storageBlocks(ELECTRUM)

    val STORAGE_BLOCKS_ASTRAL_SILVER: TagKey<Block> = storageBlocks(ASTRAL_SILVER)
    val ORES_ASTRAL_SILVER: TagKey<Block> = ores(ASTRAL_SILVER)

    val STORAGE_BLOCKS_IGNATIUS: TagKey<Block> = storageBlocks(IGNATIUS)
    val ORES_IGNATIUS: TagKey<Block> = ores(IGNATIUS)

    val ORES_OURECLASE = ores(OURECLASE)
    val STORAGE_BLOCKS_OURECLASE = storageBlocks(OURECLASE)

    val ORES_RUBRACIUM = ores(RUBRACIUM)
    val STORAGE_BLOCKS_RUBRACIUM = storageBlocks(RUBRACIUM)

    val STORAGE_BLOCKS_SHADOW_IRON: TagKey<Block> = storageBlocks(SHADOW_IRON)
    val ORES_SHADOW_IRON: TagKey<Block> = ores(SHADOW_IRON)

    val STORAGE_BLOCKS_QUICKSILVER: TagKey<Block> = storageBlocks(QUICKSILVER)

    val STORAGE_BLOCKS_CERUCLASE: TagKey<Block> = storageBlocks(CERUCLASE)
    val ORES_CERUCLASE: TagKey<Block> = ores(CERUCLASE)

    val STORAGE_BLOCKS_EXIMITE: TagKey<Block> = storageBlocks(EXIMITE)
    val ORES_EXIMITE: TagKey<Block> = ores(EXIMITE)

    val STORAGE_BLOCKS_KALENDRITE: TagKey<Block> = storageBlocks(KALENDRITE)
    val ORES_KALENDRITE: TagKey<Block> = ores(KALENDRITE)

    val STORAGE_BLOCKS_MIDASIUM: TagKey<Block> = storageBlocks(MIDASIUM)
    val ORES_MIDASIUM: TagKey<Block> = ores(MIDASIUM)

    val ORES_ORICHALCUM = ores(ORICHALCUM)
    val STORAGE_BLOCKS_ORICHALCUM = storageBlocks(ORICHALCUM)

    val ORES_PLATINUM = ores(PLATINUM)
    val STORAGE_BLOCKS_PLATINUM = storageBlocks(PLATINUM)

    val STORAGE_BLOCKS_VULCANITE: TagKey<Block> = storageBlocks(VULCANITE)
    val ORES_VULCANITE: TagKey<Block> = ores(VULCANITE)

    val STORAGE_BLOCKS_CELENEGIL: TagKey<Block> = storageBlocks(CELENEGIL)

    val STORAGE_BLOCKS_AMORDRINE: TagKey<Block> = storageBlocks(AMORDRINE)

    val ORES_CARMOT = ores(CARMOT)
    val STORAGE_BLOCKS_CARMOT = storageBlocks(CARMOT)

    private fun storageBlocks(metal: Metal): TagKey<Block> {
        val tag = c("storage_blocks/${metal.id}")
        STORAGE_BLOCKS[metal] = tag
        return tag
    }

    private fun ores(metal: Metal): TagKey<Block> {
        val tag = c("ores/${metal.id}")
        ORES[metal] = tag
        return tag
    }

    private fun alloyance(path: String): TagKey<Block> {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Alloyance.ID, path))
    }

    private fun c(path: String): TagKey<Block> {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", path))
    }
}
