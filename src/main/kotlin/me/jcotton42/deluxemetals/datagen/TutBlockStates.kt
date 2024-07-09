package me.jcotton42.deluxemetals.datagen

import me.jcotton42.deluxemetals.DeluxeMetals
import me.jcotton42.deluxemetals.Registration
import net.minecraft.data.PackOutput
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.common.data.ExistingFileHelper

class TutBlockStates(output: PackOutput, existingFileHelper: ExistingFileHelper) : BlockStateProvider(
    output,
    DeluxeMetals.ID,
    existingFileHelper
) {
    override fun registerStatesAndModels() {
        simpleBlock(Registration.SIMPLE_BLOCK.get())
        simpleBlock(Registration.COMPLEX_BLOCK.get())
    }
}