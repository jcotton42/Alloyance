package me.jcotton42.alloyance

import net.minecraft.client.resources.sounds.Sound
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.MapColor
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister

object Registration {
    val BLOCKS = DeferredRegister.create(Registries.BLOCK, Alloyance.ID)
    val ITEMS = DeferredRegister.create(Registries.ITEM, Alloyance.ID)

    // TODO deepslate maybe?
    val DEEP_IRON_ORE = BLOCKS.register("deep_iron_ore") {
        Block(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                // TODO make sure this is right
                .strength(3.0f)
                .requiresCorrectToolForDrops()
        )
    }
    val DEEP_IRON_ORE_ITEM = ITEMS.register("deep_iron_ore") {
        BlockItem(DEEP_IRON_ORE.get(), Item.Properties())
    }

    val RAW_DEEP_IRON = ITEMS.register("raw_deep_iron") {
        Item(Item.Properties())
    }

    val DEEP_IRON_INGOT = ITEMS.register("deep_iron_ingot") {
        Item(Item.Properties())
    }

    val DEEP_IRON_NUGGET = ITEMS.register("deep_iron_nugget") {
        Item(Item.Properties())
    }

    val DEEP_IRON_BLOCK = BLOCKS.register("deep_iron_block") {
        Block(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.METAL)
                .sound(SoundType.METAL)
                .requiresCorrectToolForDrops()
                // TODO .strength
        )
    }
    val DEEP_IRON_BLOCK_ITEM = ITEMS.register("deep_iron_block") {
        BlockItem(DEEP_IRON_BLOCK.get(), Item.Properties())
    }

    fun register(bus: IEventBus) {
        BLOCKS.register(bus)
        ITEMS.register(bus)
        bus.addListener(::addCreative)
    }

    private fun addCreative(event: BuildCreativeModeTabContentsEvent) {
        // TODO raw and nugget and ingot deep iron
        when (event.tabKey) {
            CreativeModeTabs.NATURAL_BLOCKS -> event.accept(DEEP_IRON_ORE)
            CreativeModeTabs.BUILDING_BLOCKS -> event.accept(DEEP_IRON_BLOCK)
            CreativeModeTabs.INGREDIENTS -> {
                event.accept(RAW_DEEP_IRON)
                event.accept(DEEP_IRON_NUGGET)
                event.accept(DEEP_IRON_INGOT)
            }
        }
    }
}