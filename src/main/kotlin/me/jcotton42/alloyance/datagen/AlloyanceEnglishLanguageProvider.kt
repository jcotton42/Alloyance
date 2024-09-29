package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.*
import net.minecraft.data.PackOutput
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.neoforged.neoforge.common.data.LanguageProvider
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredItem

class AlloyanceEnglishLanguageProvider(
    output: PackOutput,
): LanguageProvider(
    output,
    Alloyance.ID,
    "en_us"
) {
    override fun addTranslations() {
        // TODO clean this up and categorize
        addTab(AlloyanceCreativeTabs.BLOCKS, "Alloyance Blocks")
        addTab(AlloyanceCreativeTabs.DUSTS, "Alloyance Dusts")
        addTab(AlloyanceCreativeTabs.INGOTS, "Alloyance Ingots")
        addTab(AlloyanceCreativeTabs.NUGGETS, "Alloyance Nuggets")
        addTab(AlloyanceCreativeTabs.RAW_MATERIALS, "Alloyance Raw Materials")
        addTab(AlloyanceCreativeTabs.SPECIAL, "Alloyance Special")
        addTab(AlloyanceCreativeTabs.ORES, "Alloyance Ores")

        AlloyanceBlocks.STORAGE_BLOCKS.forEach { (metal, block) ->
            add(block.get(), "Block of ${getEnglishName(metal)}")
        }
        AlloyanceBlocks.ORES.forEach { (metal, ore) ->
            add(ore.get(), "${getEnglishName(metal)} Ore")
        }
        AlloyanceBlocks.DEEPSLATE_ORES.forEach { (metal, ore) ->
            add(ore.get(), "Deepslate ${getEnglishName(metal)} Ore")
        }
        AlloyanceBlocks.END_ORES.forEach { (metal, ore) ->
            add(ore.get(), "End ${getEnglishName(metal)} Ore")
        }
        AlloyanceBlocks.NETHER_ORES.forEach { (metal, ore) ->
            add(ore.get(), "Nether ${getEnglishName(metal)} Ore")
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

        add(AlloyanceBlocks.ALLOYER.get(), "Alloyer")
        addSubtitle(AlloyanceSounds.ALLOYER_AMBIENCE, "Alloyer working")
        addSubtitle(AlloyanceSounds.ALLOYER_IMPACT, "Alloyer alloys")
        addSubtitle(AlloyanceSounds.ALLOYER_WINDUP, "Alloyer starts")

        add(AlloyanceBlocks.CRUSHER.get(), "Crusher")
        addSubtitle(AlloyanceSounds.CRUSHER_AMBIENCE, "Crusher working")
        addSubtitle(AlloyanceSounds.CRUSHER_IMPACT, "Crusher crushes")
        addSubtitle(AlloyanceSounds.CRUSHER_WINDUP, "Crusher starts")

        add(AlloyanceItems.COPPER_DUST.get(), "Copper Dust")
        add(AlloyanceItems.GOLD_DUST.get(), "Gold Dust")
        add(AlloyanceItems.IRON_DUST.get(), "Iron Dust")

        add(AlloyanceItems.THERMITE_DUST.get(), "Thermite Dust")
        addTooltip(
            AlloyanceItems.THERMITE_DUST,
            "§cActs as a fuel with the same strength of coal (works best in metallurgy machines 2x efficiency!) [check JEI for more information]"
        )
        add(AlloyanceItems.INFUSED_IGNATIUS.get(), "Infused Ignatius")
        addTooltip(
            AlloyanceItems.INFUSED_IGNATIUS,
            "§cActs as an upgraded fuel with 3 times the strength of coal (works best in metallurgy machines: 3x efficiency!)"
        )

        add(AlloyanceItemTags.DUSTS_COPPER, "Copper Dusts")
        add(AlloyanceItemTags.DUSTS_GOLD, "Gold Dusts")
        add(AlloyanceItemTags.DUSTS_IRON, "Iron Dusts")

        add(AlloyanceItemTags.ALLOYABLES_ROOT, "Alloyables")

        add(AlloyanceItemTags.ALLOYABLES_COPPER, "Copper Alloyables")
        add(AlloyanceItemTags.ALLOYABLES_GOLD, "Gold Alloyables")
        add(AlloyanceItemTags.ALLOYABLES_IRON, "Iron Alloyables")

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
        AlloyanceItemTags.ALLOYABLES.forEach { (metal, tag) ->
            add(tag, "${getEnglishName(metal)} Alloyables")
        }

        AlloyanceBlockTags.STORAGE_BLOCKS.forEach { (metal, tag) ->
            add(tag, "${getEnglishName(metal)} Storage Blocks")
        }
        AlloyanceBlockTags.ORES.forEach { (metal, tag) ->
            add(tag, "${getEnglishName(metal)} Ores")
        }
    }

    private fun addTab(tab: DeferredHolder<CreativeModeTab, CreativeModeTab>, name: String) {
        add(tab.id.toLanguageKey("itemGroup"), name)
    }

    private fun addSubtitle(sound: DeferredHolder<SoundEvent, SoundEvent>, subtitle: String) {
        add(sound.id.toLanguageKey("sound"), subtitle)
    }

    private fun addTooltip(item: DeferredItem<Item>, tooltip: String) {
        add(item.id.toLanguageKey("tooltip"), tooltip)
    }

    private fun getEnglishName(metal: Metal): String = when (metal) {
        Metal.DEEP_IRON -> "Deep Iron"
        Metal.PROMETHEUM -> "Prometheum"
        Metal.ZINC -> "Zinc"
        Metal.TIN -> "Tin"
        Metal.BRONZE -> "Bronze"
        Metal.BRASS -> "Brass"
        Metal.DAMASCUS_STEEL -> "Damascus Steel"

        Metal.OSMIUM -> "Osmium"
        Metal.SILVER -> "Silver"
        Metal.INFUSCOLIUM -> "Infuscolium"
        Metal.MANGANESE -> "Manganese"
        Metal.ANGMALLEN -> "Angmallen"
        Metal.STEEL -> "Steel"
        Metal.HEPATIZON -> "Hepatizon"
        Metal.BLACK_STEEL -> "Black Steel"
        Metal.ELECTRUM -> "Electrum"

        Metal.ASTRAL_SILVER -> "Astral Silver"
        Metal.IGNATIUS -> "Ignatius"
        Metal.OURECLASE -> "Oureclase"
    }
}
