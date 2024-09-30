package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.AlloyanceBlockTags
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import me.jcotton42.alloyance.registration.Metal
import me.jcotton42.alloyance.registration.Metal.*
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.tags.BlockTags
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.common.Tags
import net.neoforged.neoforge.common.data.BlockTagsProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class AlloyanceBlockTagsProvider(
    output: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>,
    existingFileHelper: ExistingFileHelper
): BlockTagsProvider(
    output,
    lookupProvider,
    Alloyance.ID,
    existingFileHelper
) {
    override fun addTags(provider: HolderLookup.Provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
            AlloyanceBlocks.ALLOYER.get(),
            AlloyanceBlocks.CRUSHER.get(),
        )
        tag(BlockTags.NEEDS_IRON_TOOL).add(
            AlloyanceBlocks.ALLOYER.get(),
            AlloyanceBlocks.CRUSHER.get(),
        )

        AlloyanceBlocks.STORAGE_BLOCKS.forEach { (metal, block) ->
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block.get())
            tag(BlockTags.BEACON_BASE_BLOCKS).add(block.get())
            tag(getToolTag(metal)).add(block.get())
        }
        AlloyanceBlocks.ORES.forEach { (metal, block) ->
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block.get())
            tag(getToolTag(metal)).add(block.get())
        }
        AlloyanceBlocks.DEEPSLATE_ORES.forEach { (metal, block) ->
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block.get())
            tag(getToolTag(metal)).add(block.get())
        }
        AlloyanceBlocks.END_ORES.forEach { (metal, block) ->
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block.get())
            tag(getToolTag(metal)).add(block.get())
        }
        AlloyanceBlocks.NETHER_ORES.forEach { (metal, block) ->
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block.get())
            tag(getToolTag(metal)).add(block.get())
        }

        AlloyanceBlockTags.STORAGE_BLOCKS.forEach { (metal, blockTag) ->
            tag(Tags.Blocks.STORAGE_BLOCKS).addTag(blockTag)
            tag(blockTag).add(AlloyanceBlocks.STORAGE_BLOCKS.getValue(metal).get())
        }

        AlloyanceBlockTags.ORES.forEach { (metal, blockTag) ->
            tag(Tags.Blocks.ORES).addTag(blockTag)
            val ore = AlloyanceBlocks.ORES[metal]?.get()
            val deepslateOre = AlloyanceBlocks.DEEPSLATE_ORES[metal]?.get()
            val endOre = AlloyanceBlocks.END_ORES[metal]?.get()
            val netherOre = AlloyanceBlocks.NETHER_ORES[metal]?.get()
            if (ore != null) {
                tag(blockTag).add(ore)
                tag(Tags.Blocks.ORES_IN_GROUND_STONE).add(ore)
                tag(Tags.Blocks.ORE_RATES_SINGULAR).add(ore)
            }
            if (deepslateOre != null) {
                tag(blockTag).add(deepslateOre)
                tag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE).add(deepslateOre)
                tag(Tags.Blocks.ORE_RATES_SINGULAR).add(deepslateOre)
            }
            if (endOre != null) {
                tag(blockTag).add(endOre)
                tag(AlloyanceBlockTags.ORES_IN_GROUND_END_STONE).add(endOre)
                tag(Tags.Blocks.ORE_RATES_SINGULAR).add(endOre)
            }
            if (netherOre != null) {
                tag(blockTag).add(netherOre)
                tag(Tags.Blocks.ORES_IN_GROUND_NETHERRACK).add(netherOre)
                tag(Tags.Blocks.ORE_RATES_SINGULAR).add(netherOre)
            }
        }
    }
}

// TODO reconsider these based on hardness
private fun getToolTag(metal: Metal): TagKey<Block> = when (metal) {
    // tier 1
    DEEP_IRON, PROMETHEUM, ZINC, TIN, BRONZE, BRASS -> BlockTags.NEEDS_STONE_TOOL
    DAMASCUS_STEEL -> BlockTags.NEEDS_IRON_TOOL

    // tier 2
    OSMIUM, SILVER, INFUSCOLIUM, MANGANESE, ANGMALLEN, ELECTRUM -> BlockTags.NEEDS_IRON_TOOL
    STEEL, HEPATIZON, BLACK_STEEL -> BlockTags.NEEDS_DIAMOND_TOOL

    // tier 3
    ASTRAL_SILVER, IGNATIUS, OURECLASE, RUBRACIUM, SHADOW_IRON -> BlockTags.NEEDS_DIAMOND_TOOL
}
