package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.machine.crusher.CrusherBlock
import me.jcotton42.alloyance.registration.Metal.*
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument
import net.minecraft.world.level.material.MapColor
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredBlock
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier
import java.util.function.ToIntFunction

object AlloyanceBlocks {
    val BLOCKS = DeferredRegister.createBlocks(Alloyance.ID)
    val BLOCK_CODECS = DeferredRegister.create(Registries.BLOCK_TYPE, Alloyance.ID)

    val CRUSHER = BLOCKS.registerBlock(
        "crusher",
        ::CrusherBlock,
        BlockBehaviour.Properties.of()
            .sound(SoundType.METAL)
            .strength(6f, 8f)
            .mapColor(MapColor.METAL)
            .lightLevel(litBlockEmission(8))
            .requiresCorrectToolForDrops()
    )
    val CRUSHER_CODEC = BLOCK_CODECS.register("crusher", Supplier { BlockBehaviour.simpleCodec(::CrusherBlock) })

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

    fun register(bus: IEventBus) {
        BLOCKS.register(bus)
        BLOCK_CODECS.register(bus)
    }

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

    private fun litBlockEmission(lightValue: Int): ToIntFunction<BlockState> =
        ToIntFunction { state -> if (state.getValue(BlockStateProperties.LIT)) lightValue else 0 }
}
