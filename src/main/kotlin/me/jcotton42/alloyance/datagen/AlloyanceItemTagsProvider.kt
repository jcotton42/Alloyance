package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.AlloyanceBlockTags
import me.jcotton42.alloyance.registration.AlloyanceItemTags
import me.jcotton42.alloyance.registration.AlloyanceItems
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.resources.ResourceKey
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
        tag(AlloyanceItemTags.DUSTS_POTASH).add(AlloyanceItems.POTASH.get())
        tag(AlloyanceItemTags.ORES_POTASH).add(AlloyanceItems.POTASH_ORE.get(), AlloyanceItems.DEEPSLATE_POTASH_ORE.get())
        tag(AlloyanceItemTags.STORAGE_BLOCKS_POTASH).add(AlloyanceItems.POTASH_BLOCK.get())
        copy(AlloyanceBlockTags.ORES_POTASH, AlloyanceItemTags.ORES_POTASH)
        copy(AlloyanceBlockTags.STORAGE_BLOCKS_POTASH, AlloyanceItemTags.STORAGE_BLOCKS_POTASH)

        tag(AlloyanceItemTags.DUSTS_SULFUR).add(AlloyanceItems.SULFUR.get())
        tag(AlloyanceItemTags.ORES_SULFUR).add(AlloyanceItems.DEEPSLATE_SULFUR_ORE.get())
        tag(AlloyanceItemTags.STORAGE_BLOCKS_SULFUR).add(AlloyanceItems.SULFUR_BLOCK.get())
        copy(AlloyanceBlockTags.ORES_SULFUR, AlloyanceItemTags.ORES_SULFUR)
        copy(AlloyanceBlockTags.STORAGE_BLOCKS_SULFUR, AlloyanceItemTags.STORAGE_BLOCKS_SULFUR)

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
        copy(AlloyanceBlockTags.ORES_IN_GROUND_END_STONE, AlloyanceItemTags.ORES_IN_GROUND_END_STONE)
        copy(Tags.Blocks.ORES_IN_GROUND_NETHERRACK, Tags.Items.ORES_IN_GROUND_NETHERRACK)
        copy(Tags.Blocks.ORE_RATES_SINGULAR, Tags.Items.ORE_RATES_SINGULAR)
    }
}
