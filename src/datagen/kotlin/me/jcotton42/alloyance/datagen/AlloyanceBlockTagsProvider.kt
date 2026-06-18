package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.AlloyanceBlockTags
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import me.jcotton42.alloyance.registration.Metal
import me.jcotton42.alloyance.registration.Metal.*
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.tags.BlockTags.*
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.common.Tags
import net.neoforged.neoforge.common.data.BlockTagsProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class AlloyanceBlockTagsProvider(
    output: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>,
    existingFileHelper: ExistingFileHelper
): BlockTagsProvider(
    output,
    lookupProvider,
    Alloyance.ID,
    existingFileHelper
) {
    override fun addTags(provider: HolderLookup.Provider) = with(AlloyanceBlockTags) {
        setupToolTags()

        tag(ORES_PHOSPHORITE).add(AlloyanceBlocks.PHOSPHORITE_ORE.get(), AlloyanceBlocks.DEEPSLATE_PHOSPHORITE_ORE.get())

        tag(ORES_POTASH).add(AlloyanceBlocks.POTASH_ORE.get(), AlloyanceBlocks.DEEPSLATE_POTASH_ORE.get())
        tag(STORAGE_BLOCKS_POTASH).add(AlloyanceBlocks.POTASH_BLOCK.get())

        tag(ORES_SULFUR).add(AlloyanceBlocks.SULFUR_ORE.get(), AlloyanceBlocks.DEEPSLATE_SULFUR_ORE.get())
        tag(STORAGE_BLOCKS_SULFUR).add(AlloyanceBlocks.SULFUR_BLOCK.get())

        tag(MINEABLE_WITH_PICKAXE).add(
            AlloyanceBlocks.ALLOYER.get(),
            AlloyanceBlocks.CRUSHER.get(),
            AlloyanceBlocks.BIMETAL_STRUCTURE.get(),
            AlloyanceBlocks.PHOSPHORITE_ORE.get(),
            AlloyanceBlocks.DEEPSLATE_PHOSPHORITE_ORE.get(),
            AlloyanceBlocks.POTASH_ORE.get(),
            AlloyanceBlocks.DEEPSLATE_POTASH_ORE.get(),
            AlloyanceBlocks.POTASH_BLOCK.get(),
            AlloyanceBlocks.SULFUR_ORE.get(),
            AlloyanceBlocks.DEEPSLATE_SULFUR_ORE.get(),
            AlloyanceBlocks.SULFUR_BLOCK.get(),
        )
        tag(NEEDS_STONE_TOOL).add(
            AlloyanceBlocks.PHOSPHORITE_ORE.get(),
            AlloyanceBlocks.DEEPSLATE_PHOSPHORITE_ORE.get(),
            AlloyanceBlocks.POTASH_ORE.get(),
            AlloyanceBlocks.DEEPSLATE_POTASH_ORE.get(),
            AlloyanceBlocks.POTASH_BLOCK.get(),
            AlloyanceBlocks.SULFUR_ORE.get(),
            AlloyanceBlocks.DEEPSLATE_SULFUR_ORE.get(),
            AlloyanceBlocks.SULFUR_BLOCK.get(),
        )
        tag(NEEDS_IRON_TOOL).add(
            AlloyanceBlocks.ALLOYER.get(),
            AlloyanceBlocks.CRUSHER.get(),
            AlloyanceBlocks.BIMETAL_STRUCTURE.get(),
        )
        tag(Tags.Blocks.STORAGE_BLOCKS).addTags(
            STORAGE_BLOCKS_POTASH,
            STORAGE_BLOCKS_SULFUR,
        )
        tag(Tags.Blocks.ORES).addTags(
            ORES_PHOSPHORITE,
            ORES_POTASH,
            ORES_SULFUR,
        )
        tag(Tags.Blocks.ORES_IN_GROUND_STONE).add(
            AlloyanceBlocks.PHOSPHORITE_ORE.get(),
            AlloyanceBlocks.POTASH_ORE.get(),
            AlloyanceBlocks.SULFUR_ORE.get(),
        )
        tag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE).add(
            AlloyanceBlocks.DEEPSLATE_PHOSPHORITE_ORE.get(),
            AlloyanceBlocks.DEEPSLATE_POTASH_ORE.get(),
            AlloyanceBlocks.DEEPSLATE_SULFUR_ORE.get(),
        )

        AlloyanceBlocks.STORAGE_BLOCKS.forEach { (metal, block) ->
            tag(MINEABLE_WITH_PICKAXE).add(block.get())
            tag(BEACON_BASE_BLOCKS).add(block.get())
            tag(getNeedsToolTagForBlock(metal)).add(block.get())
        }
        AlloyanceBlocks.ORES.forEach { (metal, block) ->
            tag(MINEABLE_WITH_PICKAXE).add(block.get())
            tag(getNeedsToolTagForBlock(metal)).add(block.get())
        }
        AlloyanceBlocks.DEEPSLATE_ORES.forEach { (metal, block) ->
            tag(MINEABLE_WITH_PICKAXE).add(block.get())
            tag(getNeedsToolTagForBlock(metal)).add(block.get())
        }
        AlloyanceBlocks.END_ORES.forEach { (metal, block) ->
            tag(MINEABLE_WITH_PICKAXE).add(block.get())
            tag(getNeedsToolTagForBlock(metal)).add(block.get())
        }
        AlloyanceBlocks.NETHER_ORES.forEach { (metal, block) ->
            tag(MINEABLE_WITH_PICKAXE).add(block.get())
            tag(getNeedsToolTagForBlock(metal)).add(block.get())
        }

        STORAGE_BLOCKS.forEach { (metal, blockTag) ->
            tag(Tags.Blocks.STORAGE_BLOCKS).addTag(blockTag)
            tag(blockTag).add(AlloyanceBlocks.STORAGE_BLOCKS.getValue(metal).get())
        }

        ORES.forEach { (metal, blockTag) ->
            tag(Tags.Blocks.ORES).addTag(blockTag)
            val ore = AlloyanceBlocks.ORES[metal]?.get()
            val deepslateOre = AlloyanceBlocks.DEEPSLATE_ORES[metal]?.get()
            val endOre = AlloyanceBlocks.END_ORES[metal]?.get()
            val netherOre = AlloyanceBlocks.NETHER_ORES[metal]?.get()
            if (ore != null) {
                tag(blockTag).add(ore)
                tag(Tags.Blocks.ORES_IN_GROUND_STONE).add(ore)
                tag(Tags.Blocks.ORE_RATES_SINGULAR).add(ore)
            }
            if (deepslateOre != null) {
                tag(blockTag).add(deepslateOre)
                tag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE).add(deepslateOre)
                tag(Tags.Blocks.ORE_RATES_SINGULAR).add(deepslateOre)
            }
            if (endOre != null) {
                tag(blockTag).add(endOre)
                tag(ORES_IN_GROUND_END_STONE).add(endOre)
                tag(Tags.Blocks.ORE_RATES_SINGULAR).add(endOre)
            }
            if (netherOre != null) {
                tag(blockTag).add(netherOre)
                tag(Tags.Blocks.ORES_IN_GROUND_NETHERRACK).add(netherOre)
                tag(Tags.Blocks.ORE_RATES_SINGULAR).add(netherOre)
            }
        }
    }

    private fun setupToolTags() = with(AlloyanceBlockTags) {
        tag(NEEDS_COPPER_TOOL).addTag(NEEDS_STONE_TOOL)
        tag(INCORRECT_FOR_COPPER_TOOL).addTag(INCORRECT_FOR_STONE_TOOL)

        val rankedIncorrectFor = arrayOf(
            arrayOf(INCORRECT_FOR_WOODEN_TOOL, INCORRECT_FOR_GOLD_TOOL),
            arrayOf(INCORRECT_FOR_STONE_TOOL, INCORRECT_FOR_COPPER_TOOL),
            arrayOf(
                INCORRECT_FOR_IRON_TOOL,
                INCORRECT_FOR_ANGMALLEN_TOOL,
                INCORRECT_FOR_BRASS_TOOL,
                INCORRECT_FOR_BRONZE_TOOL,
                INCORRECT_FOR_COPPER_TOOL,
                INCORRECT_FOR_DEEP_IRON_TOOL,
                INCORRECT_FOR_ELECTRUM_TOOL,
                INCORRECT_FOR_PROMETHEUM_TOOL,
                INCORRECT_FOR_SILVER_TOOL,
            ),
            arrayOf(
                INCORRECT_FOR_DIAMOND_TOOL,
                INCORRECT_FOR_ASTRAL_SILVER_TOOL,
                INCORRECT_FOR_BLACK_STEEL_TOOL,
                INCORRECT_FOR_DAMASCUS_STEEL_TOOL,
                INCORRECT_FOR_HEPATIZON_TOOL,
                INCORRECT_FOR_OURECLASE_TOOL,
                INCORRECT_FOR_QUICKSILVER_TOOL,
                INCORRECT_FOR_STEEL_TOOL,
            ),
            arrayOf(
                INCORRECT_FOR_NETHERITE_TOOL,
                INCORRECT_FOR_AMORDRINE_TOOL,
                INCORRECT_FOR_CERUCLASE_TOOL,
                INCORRECT_FOR_IGNATIUS_TOOL,
                INCORRECT_FOR_KALENDRITE_TOOL,
                INCORRECT_FOR_MIDASIUM_TOOL,
                INCORRECT_FOR_ORICHALCUM_TOOL,
                INCORRECT_FOR_PLATINUM_TOOL,
                INCORRECT_FOR_SHADOW_IRON_TOOL,
            ),
            arrayOf(
                INCORRECT_FOR_CARMOT_TOOL,
                INCORRECT_FOR_CELENEGIL_TOOL,
                INCORRECT_FOR_EXIMITE_TOOL,
                INCORRECT_FOR_MITHRIL_TOOL,
                INCORRECT_FOR_SANGUINITE_TOOL,
                INCORRECT_FOR_VULCANITE_TOOL,
                INCORRECT_FOR_VYROXERES_TOOL,
            ),
            arrayOf(
                INCORRECT_FOR_ADAMANTINE_TOOL,
                INCORRECT_FOR_ATLARUS_TOOL,
                INCORRECT_FOR_DESICHALKOS_TOOL,
                INCORRECT_FOR_ETHERIUM_TOOL,
                INCORRECT_FOR_HADEROTH_TOOL,
                INCORRECT_FOR_INOLASHITE_TOOL,
                INCORRECT_FOR_KRIK_TOOL,
                INCORRECT_FOR_SHADOW_STEEL_TOOL,
            ),
            arrayOf(INCORRECT_FOR_TARTARITE_TOOL),
        )

        val rankedNeedsTool = arrayOf(
            arrayOf(NEEDS_STONE_TOOL, NEEDS_COPPER_TOOL),
            arrayOf(NEEDS_IRON_TOOL, NEEDS_ANGMALLEN_TOOL, NEEDS_BRASS_TOOL, NEEDS_BRONZE_TOOL, NEEDS_DEEP_IRON_TOOL, NEEDS_ELECTRUM_TOOL, NEEDS_PROMETHEUM_TOOL, NEEDS_SILVER_TOOL),
            arrayOf(NEEDS_DIAMOND_TOOL, NEEDS_ASTRAL_SILVER_TOOL, NEEDS_BLACK_STEEL_TOOL, NEEDS_DAMASCUS_STEEL_TOOL, NEEDS_HEPATIZON_TOOL, NEEDS_OURECLASE_TOOL, NEEDS_QUICKSILVER_TOOL, NEEDS_STEEL_TOOL),
            arrayOf(Tags.Blocks.NEEDS_NETHERITE_TOOL, NEEDS_AMORDRINE_TOOL, NEEDS_CERUCLASE_TOOL, NEEDS_IGNATIUS_TOOL, NEEDS_KALENDRITE_TOOL, NEEDS_MIDASIUM_TOOL, NEEDS_ORICHALCUM_TOOL, NEEDS_PLATINUM_TOOL, NEEDS_SHADOW_IRON_TOOL),
            arrayOf(NEEDS_CARMOT_TOOL, NEEDS_CELENEGIL_TOOL, NEEDS_EXIMITE_TOOL, NEEDS_MITHRIL_TOOL, NEEDS_SANGUINITE_TOOL, NEEDS_VULCANITE_TOOL, NEEDS_VYROXERES_TOOL),
            arrayOf(NEEDS_ADAMANTINE_TOOL, NEEDS_ATLARUS_TOOL, NEEDS_DESICHALKOS_TOOL, NEEDS_ETHERIUM_TOOL, NEEDS_HADEROTH_TOOL, NEEDS_INOLASHITE_TOOL, NEEDS_KRIK_TOOL, NEEDS_SHADOW_STEEL_TOOL),
            arrayOf(NEEDS_TARTARITE_TOOL),
        )
        val vanillaIncorrectFor = arrayOf(INCORRECT_FOR_WOODEN_TOOL, INCORRECT_FOR_GOLD_TOOL, INCORRECT_FOR_STONE_TOOL, INCORRECT_FOR_IRON_TOOL, INCORRECT_FOR_DIAMOND_TOOL, INCORRECT_FOR_NETHERITE_TOOL)
        val vanillaNeeds = arrayOf(NEEDS_STONE_TOOL, NEEDS_IRON_TOOL, NEEDS_DIAMOND_TOOL, Tags.Blocks.NEEDS_NETHERITE_TOOL)

        rankedIncorrectFor.forEachIndexed { index, incorrectFors ->
            incorrectFors.forEach { incorrectFor ->
                val incorrectForTag = tag(incorrectFor)
                for (needsIndex in index..<rankedNeedsTool.size) {
                    for (needs in rankedNeedsTool[needsIndex]) {
                        if (vanillaNeeds.contains(needs) && vanillaIncorrectFor.contains(incorrectFor)) continue
                        tag(needs)
                        incorrectForTag.addTag(needs)
                    }
                }
            }
        }
    }
}

private fun getNeedsToolTagForBlock(metal: Metal): TagKey<Block> = when (metal) {
    // tier 1
    DEEP_IRON, PROMETHEUM, ZINC, TIN, BRONZE, BRASS -> NEEDS_STONE_TOOL
    DAMASCUS_STEEL -> NEEDS_IRON_TOOL

    // tier 2
    OSMIUM, SILVER, INFUSCOLIUM, MANGANESE, ANGMALLEN, ELECTRUM -> NEEDS_IRON_TOOL
    STEEL, HEPATIZON, BLACK_STEEL -> NEEDS_DIAMOND_TOOL

    // tier 3
    ASTRAL_SILVER, IGNATIUS, OURECLASE, RUBRACIUM, SHADOW_IRON, QUICKSILVER -> NEEDS_DIAMOND_TOOL

    // tier 4
    CERUCLASE, EXIMITE, KALENDRITE, MIDASIUM, ORICHALCUM, PLATINUM, VULCANITE, AMORDRINE -> Tags.Blocks.NEEDS_NETHERITE_TOOL
    CELENEGIL -> AlloyanceBlockTags.NEEDS_CARMOT_TOOL

    // tier 5
    CARMOT, LEMURITE, MEUTOITE, MITHRIL, SANGUINITE, VYROXERES -> AlloyanceBlockTags.NEEDS_CARMOT_TOOL
    SHADOW_STEEL, HADEROTH, DESICHALKOS -> AlloyanceBlockTags.NEEDS_ADAMANTINE_TOOL

    // tier 6
    ATLARUS, ADAMANTINE, ALDUORITE, LUTETIUM, INOLASHITE, KRIK, TARTARITE, ETHERIUM -> AlloyanceBlockTags.NEEDS_ADAMANTINE_TOOL
}
