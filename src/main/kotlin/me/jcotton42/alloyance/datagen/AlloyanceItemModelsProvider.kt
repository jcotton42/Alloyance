package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import me.jcotton42.alloyance.registration.AlloyanceItems
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.client.model.generators.ItemModelProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper

class AlloyanceItemModelsProvider(output: PackOutput, existingFileHelper: ExistingFileHelper): ItemModelProvider(
    output,
    Alloyance.ID,
    existingFileHelper
) {
    override fun registerModels() {
        AlloyanceBlocks.ORES.forEach { (metal, ore) ->
            withExistingParent(ore.id.path, modLoc("block/${metal.id}_ore"))
        }
        AlloyanceBlocks.DEEPSLATE_ORES.forEach { (metal, ore) ->
            withExistingParent(ore.id.path, modLoc("block/deepslate_${metal.id}_ore"))
        }
        AlloyanceBlocks.STORAGE_BLOCKS.forEach { (metal, ore) ->
            withExistingParent(ore.id.path, modLoc("block/${metal.id}_block"))
        }
        AlloyanceItems.INGOTS.values.forEach { basicItem(it.get()) }
        AlloyanceItems.RAW_MATERIALS.values.forEach { basicItem(it.get()) }
        AlloyanceItems.NUGGETS.values.forEach { basicItem(it.get()) }
        AlloyanceItems.DUSTS.values.forEach { basicItem(it.get()) }

        basicItem(AlloyanceItems.COPPER_DUST.get())
        basicItem(AlloyanceItems.IRON_DUST.get())
        basicItem(AlloyanceItems.GOLD_DUST.get())
        basicItem(AlloyanceItems.INFUSED_IGNATIUS.get())
        basicItem(AlloyanceItems.THERMITE_DUST.get())
    }
}
