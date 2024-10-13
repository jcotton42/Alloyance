package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import me.jcotton42.alloyance.registration.AlloyanceItems
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.BucketItem
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.material.Fluid
import net.neoforged.neoforge.client.model.generators.ItemModelProvider
import net.neoforged.neoforge.client.model.generators.loaders.DynamicFluidContainerModelBuilder
import net.neoforged.neoforge.common.data.ExistingFileHelper
import net.neoforged.neoforge.registries.DeferredBlock
import net.neoforged.neoforge.registries.DeferredItem
import java.util.function.Supplier

class AlloyanceItemModelsProvider(output: PackOutput, existingFileHelper: ExistingFileHelper): ItemModelProvider(
    output,
    Alloyance.ID,
    existingFileHelper
) {
    override fun registerModels() {
        basicItem(AlloyanceItems.COPPER_DUST.get())
        basicItem(AlloyanceItems.IRON_DUST.get())
        basicItem(AlloyanceItems.GOLD_DUST.get())
        basicItem(AlloyanceItems.INFUSED_IGNATIUS.get())
        basicItem(AlloyanceItems.THERMITE_DUST.get())

        basicItem(AlloyanceItems.PHOSPHORUS.get())
        basicBlockItem(AlloyanceBlocks.PHOSPHORITE_ORE)
        basicBlockItem(AlloyanceBlocks.DEEPSLATE_PHOSPHORITE_ORE)

        basicItem(AlloyanceItems.POTASH.get())
        basicBlockItem(AlloyanceBlocks.POTASH_BLOCK)
        basicBlockItem(AlloyanceBlocks.POTASH_ORE)
        basicBlockItem(AlloyanceBlocks.DEEPSLATE_POTASH_ORE)

        basicItem(AlloyanceItems.SULFUR.get())
        basicBlockItem(AlloyanceBlocks.SULFUR_BLOCK)
        basicBlockItem(AlloyanceBlocks.DEEPSLATE_SULFUR_ORE)

        basicItem(AlloyanceItems.BITUMEN.get())
        basicItem(AlloyanceItems.TAR.get())
        basicBlockItem(AlloyanceBlocks.TAR_ORE)

        AlloyanceBlocks.ORES.values.forEach(::basicBlockItem)
        AlloyanceBlocks.DEEPSLATE_ORES.values.forEach(::basicBlockItem)
        AlloyanceBlocks.END_ORES.values.forEach(::basicBlockItem)
        AlloyanceBlocks.NETHER_ORES.values.forEach(::basicBlockItem)
        AlloyanceBlocks.STORAGE_BLOCKS.values.forEach(::basicBlockItem)

        AlloyanceItems.INGOTS.values.forEach { basicItem(it.get()) }
        AlloyanceItems.RAW_MATERIALS.values.forEach { basicItem(it.get()) }
        AlloyanceItems.NUGGETS.values.forEach { basicItem(it.get()) }
        AlloyanceItems.DUSTS.values.forEach { basicItem(it.get()) }
        AlloyanceItems.BUCKETS.forEach { (fluid, bucket) ->
            bucket(fluid, bucket)
        }
    }

    private fun basicBlockItem(block: DeferredBlock<Block>) {
        withExistingParent(block.id.path, modLoc("block/${block.id.path}"))
    }

    private fun <F: Fluid> bucket(fluid: Supplier<F>, item: DeferredItem<BucketItem>) {
        // this may need to be replaced with RegisterColorHandlersEvent.Item
        getBuilder(item.id.path)
            .parent(getExistingFile(ResourceLocation.parse("neoforge:bucket")))
            .customLoader { b, e ->
                DynamicFluidContainerModelBuilder.begin(b, e)
            }.fluid(fluid.get())
    }
}
