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

    val STORAGE_BLOCKS_DEEP_IRON: TagKey<Block> = storageBlocks(DEEP_IRON)
    val ORES_DEEP_IRON: TagKey<Block> = ores(DEEP_IRON)

    val STORAGE_BLOCKS_PROMETHEUM: TagKey<Block> = storageBlocks(PROMETHEUM)
    val ORES_PROMETHEUM: TagKey<Block> = ores(PROMETHEUM)

    val STORAGE_BLOCKS_ZINC: TagKey<Block> = storageBlocks(ZINC)
    val ORES_ZINC: TagKey<Block> = ores(ZINC)

    val STORAGE_BLOCKS_TIN: TagKey<Block> = storageBlocks(TIN)
    val ORES_TIN: TagKey<Block> = ores(TIN)

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