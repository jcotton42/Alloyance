package me.jcotton42.deluxemetals

import me.jcotton42.deluxemetals.block.ComplexBlock
import me.jcotton42.deluxemetals.block.ComplexBlockEntity
import me.jcotton42.deluxemetals.block.SimpleBlock
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object Registration {
    val BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DeluxeMetals.ID)
    val ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DeluxeMetals.ID)
    val BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DeluxeMetals.ID)

    val SIMPLE_BLOCK = BLOCKS.register("simple_block", ::SimpleBlock)
    val SIMPLE_BLOCK_ITEM = ITEMS.register("simple_block") {
        BlockItem(SIMPLE_BLOCK.get(), Item.Properties())
    }

    val COMPLEX_BLOCK = BLOCKS.register("complex_block", ::ComplexBlock)
    val COMPLEX_BLOCK_ITEM = ITEMS.register("complex_block") {
        BlockItem(COMPLEX_BLOCK.get(), Item.Properties())
    }
    val COMPLEX_BLOCK_ENTITY = BLOCK_ENTITIES.register("complex_block") {
        BlockEntityType.Builder.of(::ComplexBlockEntity, COMPLEX_BLOCK.get()).build(null)
    }

    fun register(bus: IEventBus) {
        BLOCKS.register(bus)
        ITEMS.register(bus)
        BLOCK_ENTITIES.register(bus)

        bus.addListener(::addCreative)
    }

    private fun addCreative(event: BuildCreativeModeTabContentsEvent) {
        when (event.tabKey) {
            CreativeModeTabs.BUILDING_BLOCKS -> {
                event.accept(SIMPLE_BLOCK_ITEM)
                event.accept(COMPLEX_BLOCK_ITEM)
            }
        }
    }
}