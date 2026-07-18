package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import me.jcotton42.alloyance.registration.AlloyanceItems
import net.minecraft.core.Holder
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
        extendedHandheldItem(AlloyanceItems.TARTARITE_AXE)
        extendedHandheldItem(AlloyanceItems.TARTARITE_HOE)
        extendedHandheldItem(AlloyanceItems.TARTARITE_PICKAXE)
        extendedHandheldItem(AlloyanceItems.TARTARITE_SHOVEL)
        extendedHandheldItem(AlloyanceItems.TARTARITE_SWORD)

        basicBlockItem(AlloyanceBlocks.ALLOYER)
        basicBlockItem(AlloyanceBlocks.CRUSHER)

        basicBlockItem(AlloyanceBlocks.BIMETAL_STRUCTURE)

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
        basicBlockItem(AlloyanceBlocks.SULFUR_ORE)
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

        handheldItem(AlloyanceItems.COPPER_AXE.get())
        AlloyanceItems.AXES.values.skipHandled().forEach { handheldItem(it.get()) }

        handheldItem(AlloyanceItems.COPPER_HOE.get())
        AlloyanceItems.HOES.values.skipHandled().forEach { handheldItem(it.get()) }

        handheldItem(AlloyanceItems.COPPER_PICKAXE.get())
        AlloyanceItems.PICKAXES.values.skipHandled().forEach { handheldItem(it.get()) }

        handheldItem(AlloyanceItems.COPPER_SHOVEL.get())
        AlloyanceItems.SHOVELS.values.skipHandled().forEach { handheldItem(it.get()) }

        handheldItem(AlloyanceItems.COPPER_SWORD.get())
        AlloyanceItems.SWORDS.values.skipHandled().forEach { handheldItem(it.get()) }

        basicItem(AlloyanceItems.COPPER_HELMET.get())
        AlloyanceItems.HELMETS.values.skipHandled().forEach { basicItem(it.get()) }

        basicItem(AlloyanceItems.COPPER_CHESTPLATE.get())
        AlloyanceItems.CHESTPLATES.values.skipHandled().forEach { basicItem(it.get()) }

        basicItem(AlloyanceItems.COPPER_LEGGINGS.get())
        AlloyanceItems.LEGGINGS.values.skipHandled().forEach { basicItem(it.get()) }

        basicItem(AlloyanceItems.COPPER_BOOTS.get())
        AlloyanceItems.BOOTS.values.skipHandled().forEach { basicItem(it.get()) }
    }

    private fun basicBlockItem(block: DeferredBlock<out Block>) {
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

    private fun extendedHandheldItem(item: DeferredItem<*>) {
        singleTexture(
            item.id.path,
            modLoc("item/extended_handheld"),
            "layer0",
            modLoc("item/${item.id.path}"))
        handled.add(item)
    }

    private val handled = mutableListOf<Holder<*>>()
    private fun <T: Holder<*>> Iterable<T>.skipHandled(): Iterable<T> = this.filterNot { handled.contains(it) }
}
