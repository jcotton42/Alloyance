package me.jcotton42.deluxemetals.datagen

import me.jcotton42.deluxemetals.DeluxeMetals
import me.jcotton42.deluxemetals.Registration
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.tags.BlockTags
import net.minecraftforge.common.data.BlockTagsProvider
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class TutBlockTags(
    output: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>,
    existingFileHelper: ExistingFileHelper
) : BlockTagsProvider(
        output,
        lookupProvider,
        DeluxeMetals.ID,
        existingFileHelper
) {
    override fun addTags(pProvider: HolderLookup.Provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(Registration.COMPLEX_BLOCK.get(), Registration.SIMPLE_BLOCK.get())
        tag(BlockTags.NEEDS_IRON_TOOL)
            .add(Registration.COMPLEX_BLOCK.get(), Registration.SIMPLE_BLOCK.get())
    }
}