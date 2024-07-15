package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.tags.ItemTags
import net.minecraftforge.common.data.BlockTagsProvider
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class AlloyanceItemTags(
    output: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>,
    blockTags: BlockTagsProvider,
    existingFileHelper: ExistingFileHelper
): ItemTagsProvider(
    output,
    lookupProvider,
    blockTags.contentsGetter(),
    Alloyance.ID,
    existingFileHelper
) {
    override fun addTags(pProvider: HolderLookup.Provider) {
    }
}