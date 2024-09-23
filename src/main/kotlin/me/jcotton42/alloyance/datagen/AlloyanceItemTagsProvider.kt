package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.AlloyanceBlockTags
import me.jcotton42.alloyance.registration.AlloyanceItemTags
import me.jcotton42.alloyance.registration.AlloyanceItems
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.world.item.Items
import net.neoforged.neoforge.common.Tags
import net.neoforged.neoforge.common.data.BlockTagsProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class AlloyanceItemTagsProvider(
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
    override fun addTags(provider: HolderLookup.Provider) {
        tag(AlloyanceItemTags.DUSTS_IRON).add(AlloyanceItems.IRON_DUST.get())
        tag(AlloyanceItemTags.DUSTS_GOLD).add(AlloyanceItems.GOLD_DUST.get())
        tag(AlloyanceItemTags.DUSTS_COPPER).add(AlloyanceItems.COPPER_DUST.get())

        tag(AlloyanceItemTags.ALLOYABLES_IRON)
            .addTags(Tags.Items.INGOTS_IRON)
            .addTag(AlloyanceItemTags.DUSTS_IRON)

        tag(AlloyanceItemTags.ALLOYABLES_GOLD)
            .addTag(Tags.Items.INGOTS_GOLD)
            .addTag(AlloyanceItemTags.DUSTS_GOLD)

        tag(AlloyanceItemTags.ALLOYABLES_COPPER)
            .addTag(Tags.Items.INGOTS_COPPER)
            .addTag(AlloyanceItemTags.DUSTS_COPPER)

        tag(AlloyanceItemTags.ALLOYABLES_ROOT)
            .addTag(AlloyanceItemTags.ALLOYABLES_IRON)
            .addTag(AlloyanceItemTags.ALLOYABLES_GOLD)
            .addTag(AlloyanceItemTags.ALLOYABLES_COPPER)

        tag(Tags.Items.DUSTS)
            .addTag(AlloyanceItemTags.DUSTS_IRON)
            .addTag(AlloyanceItemTags.DUSTS_GOLD)
            .addTag(AlloyanceItemTags.DUSTS_COPPER)

        AlloyanceItemTags.RAW_MATERIALS.forEach { (metal, itemTag) ->
            tag(Tags.Items.RAW_MATERIALS).addTag(itemTag)
            tag(itemTag).add(AlloyanceItems.RAW_MATERIALS.getValue(metal).get())
        }
        AlloyanceItemTags.INGOTS.forEach { (metal, itemTag) ->
            tag(Tags.Items.INGOTS).addTag(itemTag)
            tag(itemTag).add(AlloyanceItems.INGOTS.getValue(metal).get())
        }
        AlloyanceItemTags.NUGGETS.forEach { (metal, itemTag) ->
            tag(Tags.Items.NUGGETS).addTag(itemTag)
            tag(itemTag).add(AlloyanceItems.NUGGETS.getValue(metal).get())
        }
        AlloyanceItemTags.DUSTS.forEach { (metal, itemTag) ->
            tag(Tags.Items.DUSTS).addTag(itemTag)
            tag(itemTag).add(AlloyanceItems.DUSTS.getValue(metal).get())
        }
        AlloyanceItemTags.ALLOYABLES.forEach { (metal, itemTag) ->
            tag(AlloyanceItemTags.ALLOYABLES_ROOT).addTag(itemTag)
            tag(itemTag)
                .addTag(AlloyanceItemTags.DUSTS.getValue(metal))
                .addTag(AlloyanceItemTags.INGOTS.getValue(metal))
        }

        AlloyanceBlockTags.STORAGE_BLOCKS.forEach { (metal, blockTag) ->
            copy(blockTag, AlloyanceItemTags.STORAGE_BLOCKS.getValue(metal))
        }
        AlloyanceBlockTags.ORES.forEach { (metal, blockTag) ->
            copy(blockTag, AlloyanceItemTags.ORES.getValue(metal))
        }

        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS)
        copy(Tags.Blocks.ORES, Tags.Items.ORES)
        copy(Tags.Blocks.ORES_IN_GROUND_STONE, Tags.Items.ORES_IN_GROUND_STONE)
        copy(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE, Tags.Items.ORES_IN_GROUND_DEEPSLATE)
        copy(Tags.Blocks.ORE_RATES_SINGULAR, Tags.Items.ORE_RATES_SINGULAR)
    }
}
