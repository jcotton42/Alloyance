package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.Registration
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.tags.BlockTags
import net.minecraftforge.common.data.BlockTagsProvider
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class AlloyanceBlockTags(
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
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(Registration.DEEP_IRON_ORE.get(), Registration.DEEP_IRON_BLOCK.get())
        // TODO check this is the right tool
        tag(BlockTags.NEEDS_IRON_TOOL)
            .add(Registration.DEEP_IRON_ORE.get(), Registration.DEEP_IRON_BLOCK.get())
    }
}