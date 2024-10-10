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
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.DropExperienceBlock
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

private val oreProps = BlockBehaviour.Properties.of()
    .mapColor(MapColor.STONE)
    .instrument(NoteBlockInstrument.BASEDRUM)
    .requiresCorrectToolForDrops()
    .sound(SoundType.STONE)
    .strength(3.0f, 3.0f)
private val deepslateOreProps = BlockBehaviour.Properties.of()
    .mapColor(MapColor.DEEPSLATE)
    .instrument(NoteBlockInstrument.BASEDRUM)
    .requiresCorrectToolForDrops()
    .sound(SoundType.DEEPSLATE)
    .strength(4.5f, 3.0f)

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

    val PHOSPHORITE_ORE = ore("phosphorite")
    val DEEPSLATE_PHOSPHORITE_ORE = deepslateOre("phosphorite")

    val POTASH_ORE = ore("potash")
    val DEEPSLATE_POTASH_ORE = deepslateOre("potash")
    val POTASH_BLOCK = BLOCKS.registerSimpleBlock("potash_block", BlockBehaviour.Properties.of()
        .mapColor(MapColor.COLOR_RED)
        .sound(SoundType.STONE)
        .instrument(NoteBlockInstrument.BASEDRUM)
        .strength(3f, 10f)
        .requiresCorrectToolForDrops()
    )

    val DEEPSLATE_SULFUR_ORE = deepslateOre("sulfur")
    val SULFUR_BLOCK = BLOCKS.registerSimpleBlock("sulfur_block", BlockBehaviour.Properties.of()
        .mapColor(MapColor.COLOR_LIGHT_GREEN)
        .sound(SoundType.STONE)
        .instrument(NoteBlockInstrument.BASEDRUM)
        .strength(3f, 10f)
        .requiresCorrectToolForDrops()
    )

    val DEEP_IRON_ORE = metalOre(DEEP_IRON)
    val DEEPSLATE_DEEP_IRON_ORE = metalDeepslateOre(DEEP_IRON)
    val DEEP_IRON_BLOCK = storageBlock(DEEP_IRON)

    val PROMETHEUM_ORE = metalOre(PROMETHEUM)
    val DEEPSLATE_PROMETHEUM_ORE = metalDeepslateOre(PROMETHEUM)
    val PROMETHEUM_BLOCK = storageBlock(PROMETHEUM)

    val ZINC_ORE = metalOre(ZINC)
    val DEEPSLATE_ZINC_ORE = metalDeepslateOre(ZINC)
    val ZINC_BLOCK = storageBlock(ZINC)

    val TIN_ORE = metalOre(TIN)
    val TIN_BLOCK = storageBlock(TIN)

    val BRONZE_BLOCK = storageBlock(BRONZE)

    val BRASS_BLOCK = storageBlock(BRASS)

    val DAMASCUS_STEEL_BLOCK = storageBlock(DAMASCUS_STEEL)

    val DEEPSLATE_OSMIUM_ORE = metalDeepslateOre(OSMIUM)
    val OSMIUM_BLOCK = storageBlock(OSMIUM)

    val SILVER_ORE = metalOre(SILVER)
    val DEEPSLATE_SILVER_ORE = metalDeepslateOre(SILVER)
    val SILVER_BLOCK = storageBlock(SILVER)

    val INFUSCOLIUM_ORE = metalOre(INFUSCOLIUM)
    val DEEPSLATE_INFUSCOLIUM_ORE = metalDeepslateOre(INFUSCOLIUM)
    val INFUSCOLIUM_BLOCK = storageBlock(INFUSCOLIUM)

    val MANGANESE_ORE = metalOre(MANGANESE)
    val DEEPSLATE_MANGANESE_ORE = metalDeepslateOre(MANGANESE)
    val MANGANESE_BLOCK = storageBlock(MANGANESE)

    val ANGMALLEN_BLOCK = storageBlock(ANGMALLEN)

    val STEEL_BLOCK = storageBlock(STEEL)

    val HEPATIZON_BLOCK = storageBlock(HEPATIZON)

    val BLACK_STEEL_BLOCK = storageBlock(BLACK_STEEL)

    val ELECTRUM_BLOCK = storageBlock(ELECTRUM)

    val ASTRAL_SILVER_ORE = metalOre(ASTRAL_SILVER)
    val ASTRAL_SILVER_BLOCK = storageBlock(ASTRAL_SILVER)

    val NETHER_IGNATIUS_ORE = netherOre(IGNATIUS)
    val IGNATIUS_BLOCK = storageBlock(IGNATIUS)

    val OURECLASE_ORE = metalOre(OURECLASE)
    val DEEPSLATE_OURECLASE_ORE = metalDeepslateOre(OURECLASE)
    val OURECLASE_BLOCK = storageBlock(OURECLASE)

    val RUBRACIUM_ORE = metalOre(RUBRACIUM)
    val DEEPSLATE_RUBRACIUM_ORE = metalDeepslateOre(RUBRACIUM)
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

    val ORICHALCUM_ORE = metalOre(ORICHALCUM)
    val DEEPSLATE_ORICHALCUM_ORE = metalDeepslateOre(ORICHALCUM)
    val ORICHALCUM_BLOCK = storageBlock(ORICHALCUM)

    val PLATINUM_ORE = metalOre(PLATINUM)
    val DEEPSLATE_PLATINUM_ORE = metalDeepslateOre(PLATINUM)
    val PLATINUM_BLOCK = storageBlock(PLATINUM)

    val NETHER_VULCANITE_ORE = netherOre(VULCANITE)
    val VULCANITE_BLOCK = storageBlock(VULCANITE)

    val CELENEGIL_BLOCK = storageBlock(CELENEGIL)

    val AMORDRINE_BLOCK = storageBlock(AMORDRINE)

    val CARMOT_ORE = metalOre(CARMOT)
    val DEEPSLATE_CARMOT_ORE = metalDeepslateOre(CARMOT)
    val CARMOT_BLOCK = storageBlock(CARMOT)

    val NETHER_LEMURITE_ORE = netherOre(LEMURITE)
    val LEMURITE_BLOCK = storageBlock(LEMURITE)

    val END_MEUTOITE_ORE = endOre(MEUTOITE)
    val MEUTOITE_BLOCK = storageBlock(MEUTOITE)

    val MITHRIL_ORE = metalOre(MITHRIL)
    val MITHRIL_BLOCK = storageBlock(MITHRIL)

    val NETHER_SANGUINITE_ORE = netherOre(SANGUINITE)
    val SANGUINITE_BLOCK = storageBlock(SANGUINITE)

    val NETHER_VYROXERES_ORE = netherOre(VYROXERES)
    val VYROXERES_BLOCK = storageBlock(VYROXERES)

    val SHADOW_STEEL_BLOCK = storageBlock(SHADOW_STEEL)

    val HADEROTH_BLOCK = storageBlock(HADEROTH)

    val DESICHALKOS_BLOCK = storageBlock(DESICHALKOS)

    val ATLARUS_ORE = metalOre(ATLARUS)
    val DEEPSLATE_ATLARUS_ORE = metalDeepslateOre(ATLARUS)
    val ATLARUS_BLOCK = storageBlock(ATLARUS)

    val ADAMANTINE_ORE = metalOre(ADAMANTINE)
    val DEEPSLATE_ADAMANTINE_ORE = metalDeepslateOre(ADAMANTINE)
    val ADAMANTINE_BLOCK = storageBlock(ADAMANTINE)

    val NETHER_ALDUORITE_ORE = netherOre(ALDUORITE)
    val ALDUORITE_BLOCK = storageBlock(ALDUORITE)

    val NETHER_LUTETIUM_ORE = netherOre(LUTETIUM)
    val LUTETIUM_BLOCK = storageBlock(LUTETIUM)

    val INOLASHITE_BLOCK = storageBlock(INOLASHITE)

    val KRIK_BLOCK = storageBlock(KRIK)

    val TARTARITE_BLOCK = storageBlock(TARTARITE)

    val ETHERIUM_BLOCK = storageBlock(ETHERIUM)

    fun register(bus: IEventBus) {
        BLOCKS.register(bus)
        BLOCK_CODECS.register(bus)
        BLOCK_ENTITIES.register(bus)
    }

    private fun metalOre(metal: Metal): DeferredBlock<Block> {
        val ore = BLOCKS.registerSimpleBlock("${metal.id}_ore", oreProps)
        ORES[metal] = ore
        return ore
    }

    private fun ore(name: String): DeferredBlock<Block> {
        return BLOCKS.register("${name}_ore") { ->
            DropExperienceBlock(UniformInt.of(2, 5), oreProps)
        }
    }

    private fun metalDeepslateOre(metal: Metal): DeferredBlock<Block> {
        val ore = BLOCKS.registerSimpleBlock("deepslate_${metal.id}_ore", deepslateOreProps)
        DEEPSLATE_ORES[metal] = ore
        return ore
    }

    private fun deepslateOre(name: String): DeferredBlock<Block> {
        return BLOCKS.register("deepslate_${name}_ore") { ->
            DropExperienceBlock(UniformInt.of(2, 5), deepslateOreProps)
        }
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
