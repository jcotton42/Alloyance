package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import me.jcotton42.alloyance.registration.AlloyanceItems
import me.jcotton42.alloyance.registration.Metal
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.LanguageProvider

class AlloyanceLanguageProvider(
    output: PackOutput,
    locale: String
): LanguageProvider(
    output,
    Alloyance.ID,
    locale
) {
    override fun addTranslations() {
        AlloyanceBlocks.STORAGE_BLOCKS.forEach {(metal, block) ->
            add(block.get(), "Block of ${getEnglishName(metal)}")
        }
        AlloyanceBlocks.ORES.forEach {(metal, ore) ->
            add(ore.get(), "${getEnglishName(metal)} Ore")
        }
        AlloyanceBlocks.DEEPSLATE_ORES.forEach { (metal, ore) ->
            add(ore.get(), "Deepslate ${getEnglishName(metal)} Ore")
        }

        AlloyanceItems.RAW_MATERIALS.forEach {(metal, raw) ->
            add(raw.get(), "Raw ${getEnglishName(metal)}")
        }
        AlloyanceItems.INGOTS.forEach {(metal, ingot) ->
            add(ingot.get(), "${getEnglishName(metal)} Ingot")
        }
        AlloyanceItems.NUGGETS.forEach {(metal, nugget) ->
            add(nugget.get(), "${getEnglishName(metal)} Nugget")
        }
    }

    private fun getEnglishName(metal: Metal) = when (metal) {
        Metal.DEEP_IRON -> "Deep Iron"
        Metal.PROMETHEUM -> "Prometheum"
        Metal.ZINC -> "Zinc"
        Metal.TIN -> "Tin"
    }
}