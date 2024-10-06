package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.client.model.generators.BlockStateProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper

class AlloyanceBlockStatesProvider(output: PackOutput, existingFileHelper: ExistingFileHelper): BlockStateProvider(
    output,
    Alloyance.ID,
    existingFileHelper
) {
    override fun registerStatesAndModels() {
        simpleBlock(AlloyanceBlocks.POTASH_ORE.get())
        simpleBlock(AlloyanceBlocks.DEEPSLATE_POTASH_ORE.get())
        simpleBlock(AlloyanceBlocks.POTASH_BLOCK.get())

        simpleBlock(AlloyanceBlocks.DEEPSLATE_SULFUR_ORE.get())
        simpleBlock(AlloyanceBlocks.SULFUR_BLOCK.get())

        AlloyanceBlocks.ORES.values.forEach { simpleBlock(it.get()) }
        AlloyanceBlocks.DEEPSLATE_ORES.values.forEach { simpleBlock(it.get()) }
        AlloyanceBlocks.END_ORES.values.forEach { simpleBlock(it.get()) }
        AlloyanceBlocks.NETHER_ORES.values.forEach { simpleBlock(it.get()) }
        AlloyanceBlocks.STORAGE_BLOCKS.values.forEach { simpleBlock(it.get()) }
    }
}