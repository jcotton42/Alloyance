package me.jcotton42.deluxemetals.datagen

import me.jcotton42.deluxemetals.DeluxeMetals
import me.jcotton42.deluxemetals.Registration
import net.minecraft.data.PackOutput
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.common.data.ExistingFileHelper

class TutItemModels(output: PackOutput, existingFileHelper: ExistingFileHelper) : ItemModelProvider(
    output,
    DeluxeMetals.ID,
    existingFileHelper
) {
    override fun registerModels() {
        withExistingParent(Registration.SIMPLE_BLOCK.id.path, modLoc("block/simple_block"))
        withExistingParent(Registration.COMPLEX_BLOCK.id.path, modLoc("block/complex_block"))
    }
}