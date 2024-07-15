package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.Registration
import net.minecraft.data.PackOutput
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.common.data.ExistingFileHelper

class AlloyanceItemModels(output: PackOutput, existingFileHelper: ExistingFileHelper): ItemModelProvider(
    output,
    Alloyance.ID,
    existingFileHelper
) {
    override fun registerModels() {
        withExistingParent(Registration.DEEP_IRON_ORE.id.path, modLoc("block/deep_iron_ore"))
        withExistingParent(Registration.DEEP_IRON_BLOCK.id.path, modLoc("block/deep_iron_block"))
        basicItem(Registration.DEEP_IRON_NUGGET.get())
        basicItem(Registration.DEEP_IRON_INGOT.get())
        basicItem(Registration.RAW_DEEP_IRON.get())
    }
}