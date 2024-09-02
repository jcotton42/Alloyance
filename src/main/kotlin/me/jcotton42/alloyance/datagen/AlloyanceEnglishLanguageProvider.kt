package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.machine.crusher.CrusherBlockEntity
import me.jcotton42.alloyance.registration.*
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
        AlloyanceBlocks.STORAGE_BLOCKS.forEach { (metal, block) ->
            add(block.get(), "Block of ${getEnglishName(metal)}")
        }
        AlloyanceBlocks.ORES.forEach { (metal, ore) ->
            add(ore.get(), "${getEnglishName(metal)} Ore")
        }
        AlloyanceBlocks.DEEPSLATE_ORES.forEach { (metal, ore) ->
            add(ore.get(), "Deepslate ${getEnglishName(metal)} Ore")
        }

        AlloyanceItems.RAW_MATERIALS.forEach { (metal, raw) ->
            add(raw.get(), "Raw ${getEnglishName(metal)}")
        }
        AlloyanceItems.INGOTS.forEach { (metal, ingot) ->
            add(ingot.get(), "${getEnglishName(metal)} Ingot")
        }
        AlloyanceItems.NUGGETS.forEach { (metal, nugget) ->
            add(nugget.get(), "${getEnglishName(metal)} Nugget")
        }
        AlloyanceItems.DUSTS.forEach { (metal, dust) ->
            add(dust.get(), "${getEnglishName(metal)} Dust")
        }

        add(AlloyanceBlocks.CRUSHER.get(), "Crusher")
        add(AlloyanceSounds.CRUSHER_AMBIENCE.id.toLanguageKey("sound"), "Crusher Working")
        add(AlloyanceSounds.CRUSHER_IMPACT.id.toLanguageKey("sound"), "Crusher Crushes")
        add(AlloyanceSounds.CRUSHER_WINDUP.id.toLanguageKey("sound"), "Crusher Starts")

        add(AlloyanceItems.COPPER_DUST.get(), "Copper Dust")
        add(AlloyanceItems.GOLD_DUST.get(), "Gold Dust")
        add(AlloyanceItems.IRON_DUST.get(), "Iron Dust")

        add(AlloyanceItems.THERMITE_DUST.get(), "Thermite Dust")
        add(
            AlloyanceItems.THERMITE_DUST.id.toLanguageKey("tooltip"),
            "§cActs as a fuel with the same strength of coal (works best in metallurgy machines 2x efficiency!) [check JEI for more information]"
        )
        add(AlloyanceItems.INFUSED_IGNATIUS.get(), "Infused Ignatius")
        add(
            AlloyanceItems.INFUSED_IGNATIUS.id.toLanguageKey("tooltip"),
            "§cActs as an upgraded fuel with 3 times the strength of coal (works best in metallurgy machines: 3x efficiency!)"
        )

        add(AlloyanceItemTags.DUSTS_COPPER, "Copper Dusts")
        add(AlloyanceItemTags.DUSTS_GOLD, "Gold Dusts")
        add(AlloyanceItemTags.DUSTS_IRON, "Iron Dusts")

        AlloyanceItemTags.RAW_MATERIALS.forEach { (metal, tag) ->
            add(tag, "${getEnglishName(metal)} Raw Materials")
        }
        AlloyanceItemTags.INGOTS.forEach { (metal, tag) ->
            add(tag, "${getEnglishName(metal)} Ingots")
        }
        AlloyanceItemTags.NUGGETS.forEach { (metal, tag) ->
            add(tag, "${getEnglishName(metal)} Nuggets")
        }
        AlloyanceItemTags.DUSTS.forEach { (metal, tag) ->
            add(tag, "${getEnglishName(metal)} Dusts")
        }
        AlloyanceItemTags.STORAGE_BLOCKS.forEach { (metal, tag) ->
            add(tag, "${getEnglishName(metal)} Storage Blocks")
        }
        AlloyanceItemTags.ORES.forEach { (metal, tag) ->
            add(tag, "${getEnglishName(metal)} Ores")
        }

        AlloyanceBlockTags.STORAGE_BLOCKS.forEach { (metal, tag) ->
            add(tag, "${getEnglishName(metal)} Storage Blocks")
        }
        AlloyanceBlockTags.ORES.forEach { (metal, tag) ->
            add(tag, "${getEnglishName(metal)} Ores")
        }
    }

    private fun getEnglishName(metal: Metal): String = when (metal) {
        Metal.DEEP_IRON -> "Deep Iron"
        Metal.PROMETHEUM -> "Prometheum"
        Metal.ZINC -> "Zinc"
        Metal.TIN -> "Tin"
    }
}