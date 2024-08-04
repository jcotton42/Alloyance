package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.tags.BlockTags
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

        tag(BlockTags.NEEDS_IRON_TOOL).add(
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
        )

        // TODO BlockTags.BEACON_BASE_BLOCKS, see isBeaconBase in BlockMetal.java in Reforged for criteria
    }
}
