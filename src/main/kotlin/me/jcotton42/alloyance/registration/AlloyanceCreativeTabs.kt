package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.CreativeModeTabs
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent
import net.neoforged.neoforge.registries.DeferredRegister

object AlloyanceCreativeTabs {
    val TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Alloyance.ID)

    fun register(bus: IEventBus) {
        TABS.register(bus)
        bus.addListener(::registerCreative)
    }

    private fun registerCreative(event: BuildCreativeModeTabContentsEvent) = when (event.tabKey) {
        CreativeModeTabs.BUILDING_BLOCKS -> {
            event.accept(AlloyanceBlocks.DEEP_IRON_BLOCK)
            event.accept(AlloyanceBlocks.PROMETHEUM_BLOCK)
            event.accept(AlloyanceBlocks.ZINC_BLOCK)
            event.accept(AlloyanceBlocks.TIN_BLOCK)
        }
        CreativeModeTabs.INGREDIENTS -> {
            AlloyanceItems.RAW_MATERIALS.values.forEach { event.accept(it.get()) }
            AlloyanceItems.NUGGETS.values.forEach { event.accept(it.get()) }
            AlloyanceItems.INGOTS.values.forEach { event.accept(it.get()) }
        }
        CreativeModeTabs.NATURAL_BLOCKS -> {
            event.accept(AlloyanceBlocks.DEEP_IRON_ORE)
            event.accept(AlloyanceBlocks.DEEPSLATE_DEEP_IRON_ORE)
            event.accept(AlloyanceBlocks.PROMETHEUM_ORE)
            event.accept(AlloyanceBlocks.DEEPSLATE_PROMETHEUM_ORE)
            event.accept(AlloyanceBlocks.ZINC_ORE)
            event.accept(AlloyanceBlocks.DEEPSLATE_ZINC_ORE)
            event.accept(AlloyanceBlocks.TIN_ORE)
        }
        else -> {}
    }
}
