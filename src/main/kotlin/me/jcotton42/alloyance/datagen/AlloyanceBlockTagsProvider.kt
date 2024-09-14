package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.AlloyanceBlockTags
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.tags.BlockTags
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
        AlloyanceBlocks.STORAGE_BLOCKS.values.forEach { tag(BlockTags.MINEABLE_WITH_PICKAXE).add(it.get()) }
        AlloyanceBlocks.ORES.values.forEach { tag(BlockTags.MINEABLE_WITH_PICKAXE).add(it.get()) }
        AlloyanceBlocks.DEEPSLATE_ORES.values.forEach { tag(BlockTags.MINEABLE_WITH_PICKAXE).add(it.get()) }

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
            AlloyanceBlocks.CRUSHER.get(),
        )
        tag(BlockTags.NEEDS_IRON_TOOL).add(
            AlloyanceBlocks.CRUSHER.get(),
        )

        // TODO make a tier dictionary or something
        tag(BlockTags.NEEDS_STONE_TOOL).add(
            AlloyanceBlocks.DEEP_IRON_BLOCK.get(),
            AlloyanceBlocks.DEEP_IRON_ORE.get(),
            AlloyanceBlocks.DEEPSLATE_DEEP_IRON_ORE.get(),

            AlloyanceBlocks.PROMETHEUM_BLOCK.get(),
            AlloyanceBlocks.PROMETHEUM_ORE.get(),
            AlloyanceBlocks.DEEPSLATE_PROMETHEUM_ORE.get(),

            AlloyanceBlocks.ZINC_BLOCK.get(),
            AlloyanceBlocks.ZINC_ORE.get(),
            AlloyanceBlocks.DEEPSLATE_ZINC_ORE.get(),

            AlloyanceBlocks.TIN_BLOCK.get(),
            AlloyanceBlocks.TIN_ORE.get(),

            AlloyanceBlocks.BRONZE_BLOCK.get(),
            AlloyanceBlocks.BRASS_BLOCK.get(),
        )

        // TODO BlockTags.BEACON_BASE_BLOCKS, see isBeaconBase in BlockMetal.java in Reforged for criteria

        AlloyanceBlockTags.STORAGE_BLOCKS.forEach { (metal, blockTag) ->
            tag(Tags.Blocks.STORAGE_BLOCKS).addTag(blockTag)
            tag(blockTag).add(AlloyanceBlocks.STORAGE_BLOCKS.getValue(metal).get())
        }

        AlloyanceBlockTags.ORES.forEach { (metal, blockTag) ->
            tag(Tags.Blocks.ORES).addTag(blockTag)
            val ore = AlloyanceBlocks.ORES[metal]?.get()
            val deepslateOre = AlloyanceBlocks.DEEPSLATE_ORES[metal]?.get()
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
        }
    }
}
