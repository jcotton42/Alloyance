package me.jcotton42.deluxemetals.datagen

import me.jcotton42.deluxemetals.DeluxeMetals
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraftforge.common.data.BlockTagsProvider
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class TutItemTags(
    output: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>,
    blockTags: BlockTagsProvider,
    existingFileHelper: ExistingFileHelper
): ItemTagsProvider(
    output,
    lookupProvider,
    blockTags.contentsGetter(),
    DeluxeMetals.ID,
    existingFileHelper
) {
    override fun addTags(pProvider: HolderLookup.Provider) {
    }
}