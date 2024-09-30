// BlockEntityType.Builder.build must always take null because it's a datafixer types, and those aren't valid for
// modded things
@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.machine.alloyer.AlloyerBlock
import me.jcotton42.alloyance.machine.alloyer.AlloyerBlockEntity
import me.jcotton42.alloyance.machine.crusher.CrusherBlock
import me.jcotton42.alloyance.machine.crusher.CrusherBlockEntity
import me.jcotton42.alloyance.registration.Metal.*
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument
import net.minecraft.world.level.material.MapColor
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredBlock
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.ToIntFunction

object AlloyanceBlocks {
    val BLOCKS = DeferredRegister.createBlocks(Alloyance.ID)
    val BLOCK_CODECS = DeferredRegister.create(Registries.BLOCK_TYPE, Alloyance.ID)
    val BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Alloyance.ID)

    val ORES = mutableMapOf<Metal, DeferredBlock<Block>>()
    val DEEPSLATE_ORES = mutableMapOf<Metal, DeferredBlock<Block>>()
    val END_ORES = mutableMapOf<Metal, DeferredBlock<Block>>()
    val NETHER_ORES = mutableMapOf<Metal, DeferredBlock<Block>>()
    val STORAGE_BLOCKS = mutableMapOf<Metal, DeferredBlock<Block>>()

    val ALLOYER = BLOCKS.registerBlock(
        "alloyer",
        ::AlloyerBlock,
        BlockBehaviour.Properties.of()
            .sound(SoundType.METAL)
            .strength(6f, 8f)
            .mapColor(MapColor.METAL)
            .lightLevel(litBlockEmission(8))
            .requiresCorrectToolForDrops()
    )
    val ALLOYER_CODEC = BLOCK_CODECS.register("alloyer") { -> BlockBehaviour.simpleCodec(::AlloyerBlock) }
    val ALLOYER_BLOCK_ENTITY = BLOCK_ENTITIES.register("alloyer") { ->
        BlockEntityType.Builder.of(::AlloyerBlockEntity, ALLOYER.get()).build(null)
    }

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
    val CRUSHER_CODEC = BLOCK_CODECS.register("crusher") { -> BlockBehaviour.simpleCodec(::CrusherBlock) }
    val CRUSHER_BLOCK_ENTITY = BLOCK_ENTITIES.register("crusher") { ->
        BlockEntityType.Builder.of(::CrusherBlockEntity, CRUSHER.get()).build(null)
    }

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

    val BRONZE_BLOCK = storageBlock(BRONZE)

    val BRASS_BLOCK = storageBlock(BRASS)

    val DAMASCUS_STEEL_BLOCK = storageBlock(DAMASCUS_STEEL)

    val DEEPSLATE_OSMIUM_ORE = deepslateOre(OSMIUM)
    val OSMIUM_BLOCK = storageBlock(OSMIUM)

    val SILVER_ORE = ore(SILVER)
    val DEEPSLATE_SILVER_ORE = deepslateOre(SILVER)
    val SILVER_BLOCK = storageBlock(SILVER)

    val INFUSCOLIUM_ORE = ore(INFUSCOLIUM)
    val DEEPSLATE_INFUSCOLIUM_ORE = deepslateOre(INFUSCOLIUM)
    val INFUSCOLIUM_BLOCK = storageBlock(INFUSCOLIUM)

    val MANGANESE_ORE = ore(MANGANESE)
    val DEEPSLATE_MANGANESE_ORE = deepslateOre(MANGANESE)
    val MANGANESE_BLOCK = storageBlock(MANGANESE)

    val ANGMALLEN_BLOCK = storageBlock(ANGMALLEN)

    val STEEL_BLOCK = storageBlock(STEEL)

    val HEPATIZON_BLOCK = storageBlock(HEPATIZON)

    val BLACK_STEEL_BLOCK = storageBlock(BLACK_STEEL)

    val ELECTRUM_BLOCK = storageBlock(ELECTRUM)

    val ASTRAL_SILVER_ORE = ore(ASTRAL_SILVER)
    val ASTRAL_SILVER_BLOCK = storageBlock(ASTRAL_SILVER)

    val NETHER_IGNATIUS_ORE = netherOre(IGNATIUS)
    val IGNATIUS_BLOCK = storageBlock(IGNATIUS)

    val OURECLASE_ORE = ore(OURECLASE)
    val DEEPSLATE_OURECLASE_ORE = deepslateOre(OURECLASE)
    val OURECLASE_BLOCK = storageBlock(OURECLASE)

    val RUBRACIUM_ORE = ore(RUBRACIUM)
    val DEEPSLATE_RUBRACIUM_ORE = deepslateOre(RUBRACIUM)
    val RUBRACIUM_BLOCK = storageBlock(RUBRACIUM)

    val NETHER_SHADOW_IRON_ORE = netherOre(SHADOW_IRON)
    val SHADOW_IRON_BLOCK = storageBlock(SHADOW_IRON)

    val QUICKSILVER_BLOCK = storageBlock(QUICKSILVER)

    val NETHER_CERUCLASE_ORE = netherOre(CERUCLASE)
    val CERUCLASE_BLOCK = storageBlock(CERUCLASE)

    val END_EXIMITE_ORE = endOre(EXIMITE)
    val EXIMITE_BLOCK = storageBlock(EXIMITE)

    val NETHER_KALENDRITE_ORE = netherOre(KALENDRITE)
    val KALENDRITE_BLOCK = storageBlock(KALENDRITE)

    val NETHER_MIDASIUM_ORE = netherOre(MIDASIUM)
    val MIDASIUM_BLOCK = storageBlock(MIDASIUM)

    val ORICHALCUM_ORE = ore(ORICHALCUM)
    val DEEPSLATE_ORICHALCUM_ORE = deepslateOre(ORICHALCUM)
    val ORICHALCUM_BLOCK = storageBlock(ORICHALCUM)

    val PLATINUM_ORE = ore(PLATINUM)
    val DEEPSLATE_PLATINUM_ORE = deepslateOre(PLATINUM)
    val PLATINUM_BLOCK = storageBlock(PLATINUM)

    val NETHER_VULCANITE_ORE = netherOre(VULCANITE)
    val VULCANITE_BLOCK = storageBlock(VULCANITE)

    val CELENEGIL_BLOCK = storageBlock(CELENEGIL)

    val AMORDRINE_BLOCK = storageBlock(AMORDRINE)

    fun register(bus: IEventBus) {
        BLOCKS.register(bus)
        BLOCK_CODECS.register(bus)
        BLOCK_ENTITIES.register(bus)
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

    private fun endOre(metal: Metal): DeferredBlock<Block> {
        val ore = BLOCKS.registerSimpleBlock("end_${metal.id}_ore",
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.SAND)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops()
                .sound(SoundType.STONE)
                .strength(3.0f, 9.0f)
        )
        END_ORES[metal] = ore
        return ore
    }

    private fun netherOre(metal: Metal): DeferredBlock<Block> {
        val ore = BLOCKS.registerSimpleBlock("nether_${metal.id}_ore",
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.NETHER)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops()
                // TODO NETHER_GOLD_ORE may be a more fitting sound
                .sound(SoundType.NETHER_ORE)
                .strength(3.0f, 3.0f)
        )
        NETHER_ORES[metal] = ore
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
                .requiresCorrectToolForDrops()
                .strength(metal.hardness, metal.blockBlastResistance)
        )
        STORAGE_BLOCKS[metal] = block
        return block
    }

    private fun litBlockEmission(lightValue: Int): ToIntFunction<BlockState> =
        ToIntFunction { state -> if (state.getValue(BlockStateProperties.LIT)) lightValue else 0 }
}
