package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.Registration
import net.minecraft.data.PackOutput
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.common.data.ExistingFileHelper

class AlloyanceBlockStates(output: PackOutput, existingFileHelper: ExistingFileHelper): BlockStateProvider(
    output,
    Alloyance.ID,
    existingFileHelper
) {
    override fun registerStatesAndModels() {
        // simpleBlock, etc.
        simpleBlock(Registration.DEEP_IRON_ORE.get())
        simpleBlock(Registration.DEEP_IRON_BLOCK.get())
    }
}