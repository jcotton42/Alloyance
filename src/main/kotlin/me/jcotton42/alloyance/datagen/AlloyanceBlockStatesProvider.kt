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
        AlloyanceBlocks.ORES.values.forEach { simpleBlock(it.get()) }
        AlloyanceBlocks.DEEPSLATE_ORES.values.forEach { simpleBlock(it.get()) }
        AlloyanceBlocks.END_ORES.values.forEach { simpleBlock(it.get()) }
        AlloyanceBlocks.NETHER_ORES.values.forEach { simpleBlock(it.get()) }
        AlloyanceBlocks.STORAGE_BLOCKS.values.forEach { simpleBlock(it.get()) }
    }
}