package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.Metal.*
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument
import net.minecraft.world.level.material.MapColor
import net.neoforged.neoforge.registries.DeferredBlock
import net.neoforged.neoforge.registries.DeferredRegister

object AlloyanceBlocks {
    val BLOCKS = DeferredRegister.createBlocks(Alloyance.ID)

    val ORES = mutableMapOf<Metal, DeferredBlock<Block>>()
    val DEEPSLATE_ORES = mutableMapOf<Metal, DeferredBlock<Block>>()
    val STORAGE_BLOCKS = mutableMapOf<Metal, DeferredBlock<Block>>()

    val DEEP_IRON_ORE = ore(DEEP_IRON)
    val DEEPSLATE_DEEP_IRON_ORE = deepslateOre(DEEP_IRON)
    val DEEP_IRON_BLOCK = storageBlock(DEEP_IRON)

    val PROMETHEUM_ORE = ore(PROMETHEUM)
    val DEEPSLATE_PROMETHEUM_ORE = deepslateOre(PROMETHEUM)
    val PROMETHEUM_BLOCK = storageBlock(PROMETHEUM)

    val ZINC_ORE = ore(ZINC)
    val DEEPSLATE_ZINC_ORE = deepslateOre(ZINC)
    val ZINC_BLOCK = storageBlock(ZINC)

    val TIN_ORE = ore(TIN)
    val TIN_BLOCK = storageBlock(TIN)

    private fun ore(metal: Metal): DeferredBlock<Block> {
        val ore = BLOCKS.registerSimpleBlock(
            "${metal.id}_ore",
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops()
                .sound(SoundType.STONE)
                .strength(3.0f, 3.0f)
        )
        ORES[metal] = ore
        return ore
    }

    private fun deepslateOre(metal: Metal): DeferredBlock<Block> {
        val ore = BLOCKS.registerSimpleBlock("deepslate_${metal.id}_ore",
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.DEEPSLATE)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops()
                .sound(SoundType.DEEPSLATE)
                .strength(4.5f, 3.0f)
        )
        DEEPSLATE_ORES[metal] = ore
        return ore
    }

    private fun storageBlock(metal: Metal): DeferredBlock<Block> {
        val block = BLOCKS.registerSimpleBlock(
            "${metal.id}_block",
            BlockBehaviour.Properties.of()
                // TODO custom map colors per metal
                .mapColor(MapColor.METAL)
                .sound(SoundType.METAL)
                .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                .strength(metal.hardness, metal.blockBlastResistance)
        )
        STORAGE_BLOCKS[metal] = block
        return block
    }
}
