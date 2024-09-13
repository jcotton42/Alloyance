package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.ItemStack
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister

object AlloyanceCreativeTabs {
    val TABS: DeferredRegister<CreativeModeTab> = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Alloyance.ID)

    // TODO ARMOR
    // TODO pick a variety of metals for the icons
    val BLOCKS: DeferredHolder<CreativeModeTab, CreativeModeTab> = TABS.register("blocks") { location ->
        CreativeModeTab.builder()
            .title(Component.translatable(location.toLanguageKey("itemGroup")))
            .icon { ItemStack(AlloyanceBlocks.DEEP_IRON_BLOCK.get()) }
            .displayItems { params, output ->
                Metal.entries.forEach { metal ->
                    output.accept(AlloyanceBlocks.STORAGE_BLOCKS.getValue(metal))
                }
            }
            .build()
    }

    val DUSTS: DeferredHolder<CreativeModeTab, CreativeModeTab> = TABS.register("dusts") { location ->
        CreativeModeTab.builder()
            .title(Component.translatable(location.toLanguageKey("itemGroup")))
            .icon { ItemStack(AlloyanceItems.DEEP_IRON_DUST.get()) }
            .displayItems { params, output ->
                output.accept(AlloyanceItems.COPPER_DUST)
                output.accept(AlloyanceItems.IRON_DUST)
                output.accept(AlloyanceItems.GOLD_DUST)
                Metal.entries.forEach { metal ->
                    output.accept(AlloyanceItems.DUSTS.getValue(metal))
                }
            }
            .build()
    }

    val INGOTS: DeferredHolder<CreativeModeTab, CreativeModeTab> = TABS.register("ingots") { location ->
        CreativeModeTab.builder()
            .title(Component.translatable(location.toLanguageKey("itemGroup")))
            .icon { ItemStack(AlloyanceItems.DEEP_IRON_INGOT.get()) }
            .displayItems {params, output ->
                Metal.entries.forEach { metal ->
                    output.accept(AlloyanceItems.INGOTS.getValue(metal))
                }
            }
            .build()
    }

    val NUGGETS: DeferredHolder<CreativeModeTab, CreativeModeTab> = TABS.register("nuggets") { location ->
        CreativeModeTab.builder()
            .title(Component.translatable(location.toLanguageKey("itemGroup")))
            .icon { ItemStack(AlloyanceItems.DEEP_IRON_NUGGET.get()) }
            .displayItems {params, output ->
                Metal.entries.forEach { metal ->
                    output.accept(AlloyanceItems.NUGGETS.getValue(metal))
                }
            }
            .build()
    }

    val RAW_MATERIALS: DeferredHolder<CreativeModeTab, CreativeModeTab> = TABS.register("raw_materials") { location ->
        CreativeModeTab.builder()
            .title(Component.translatable(location.toLanguageKey("itemGroup")))
            .icon { ItemStack(AlloyanceItems.RAW_DEEP_IRON.get()) }
            .displayItems {params, output ->
                Metal.entries.forEach { metal ->
                    val raw = AlloyanceItems.RAW_MATERIALS[metal]
                    if (raw != null) {
                        output.accept(raw)
                    }
                }
            }
            .build()
    }

    val SPECIAL: DeferredHolder<CreativeModeTab, CreativeModeTab> = TABS.register("special") { location ->
        CreativeModeTab.builder()
            .title(Component.translatable(location.toLanguageKey("itemGroup")))
            .icon { ItemStack(AlloyanceItems.CRUSHER.get()) }
            .displayItems { params, output ->
                output.accept(AlloyanceItems.CRUSHER)
            }
            .build()
    }

    val ORES: DeferredHolder<CreativeModeTab, CreativeModeTab> = TABS.register("ores") { location ->
        CreativeModeTab.builder()
            .title(Component.translatable(location.toLanguageKey("itemGroup")))
            .icon { ItemStack(AlloyanceBlocks.DEEP_IRON_ORE.get()) }
            .displayItems { params, output ->
                Metal.entries.forEach { metal ->
                    val ore = AlloyanceBlocks.ORES[metal]
                    val deepslateOre = AlloyanceBlocks.DEEPSLATE_ORES[metal]
                    // TODO nether, end
                    if (ore != null) output.accept(ore)
                    if (deepslateOre != null) output.accept(deepslateOre)
                }
            }
            .build()
    }

    fun register(bus: IEventBus) {
        TABS.register(bus)
        bus.addListener(::registerCreative)
    }

    private fun registerCreative(event: BuildCreativeModeTabContentsEvent) = when (event.tabKey) {
        // TODO remove this?
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
