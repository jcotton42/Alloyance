package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.machine.crusher.CrusherBlockEntity
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import me.jcotton42.alloyance.registration.AlloyanceItems
import me.jcotton42.alloyance.registration.Metal
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.LanguageProvider

class AlloyanceEnglishLanguageProvider(
    output: PackOutput,
): LanguageProvider(
    output,
    Alloyance.ID,
    "en_us"
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

        add(AlloyanceBlocks.CRUSHER.get(), "Crusher")
        add(CrusherBlockEntity.NAME_KEY, "Crusher")

        add(AlloyanceItems.THERMITE_DUST.get(), "Thermite Dust")
        add("tooltip.alloyance.thermite", "§cActs as a fuel with the same strength of coal (works best in metallurgy machines 2x efficiency!) [check JEI for more information]")
        add(AlloyanceItems.INFUSED_IGNATIUS.get(), "Infused Ignatius")
        add("tooltip.alloyance.infused_ignatius", "§cActs as an upgraded fuel with 3 times the strength of coal (works best in metallurgy machines: 3x efficiency!)")
    }

    private fun getEnglishName(metal: Metal) = when (metal) {
        Metal.DEEP_IRON -> "Deep Iron"
        Metal.PROMETHEUM -> "Prometheum"
        Metal.ZINC -> "Zinc"
        Metal.TIN -> "Tin"
    }
}