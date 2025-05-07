package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.neoforged.bus.api.IEventBus
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
            .displayItems { _, output ->
                Metal.entries.forEach { metal ->
                    output.accept(AlloyanceBlocks.STORAGE_BLOCKS.getValue(metal))
                }
                output.accept(AlloyanceBlocks.POTASH_BLOCK)
                output.accept(AlloyanceBlocks.SULFUR_BLOCK)
                output.accept(AlloyanceBlocks.BIMETAL_STRUCTURE)
            }
            .build()
    }

    val DUSTS: DeferredHolder<CreativeModeTab, CreativeModeTab> = TABS.register("dusts") { location ->
        CreativeModeTab.builder()
            .title(Component.translatable(location.toLanguageKey("itemGroup")))
            .icon { ItemStack(AlloyanceItems.DEEP_IRON_DUST.get()) }
            .displayItems { _, output ->
                output.accept(AlloyanceItems.COPPER_DUST)
                output.accept(AlloyanceItems.IRON_DUST)
                output.accept(AlloyanceItems.GOLD_DUST)
                Metal.entries.forEach { metal ->
                    output.accept(AlloyanceItems.DUSTS.getValue(metal))
                }
                output.accept(AlloyanceItems.PHOSPHORUS)
                output.accept(AlloyanceItems.POTASH)
                output.accept(AlloyanceItems.SULFUR)
                output.accept(AlloyanceItems.THERMITE_DUST)
                output.accept(AlloyanceItems.BITUMEN)
                output.accept(AlloyanceItems.TAR)
            }
            .build()
    }

    val FLUIDS: DeferredHolder<CreativeModeTab, CreativeModeTab> = TABS.register("fluids") { location ->
        CreativeModeTab.builder()
            .title(Component.translatable(location.toLanguageKey("itemGroup")))
            .icon { ItemStack(AlloyanceItems.MOLTEN_TAR_BUCKET.get()) }
            .displayItems { _, output ->
                output.accept(AlloyanceItems.MOLTEN_TAR_BUCKET.get())
            }
            .build()
    }

    val INGOTS: DeferredHolder<CreativeModeTab, CreativeModeTab> = TABS.register("ingots") { location ->
        CreativeModeTab.builder()
            .title(Component.translatable(location.toLanguageKey("itemGroup")))
            .icon { ItemStack(AlloyanceItems.DEEP_IRON_INGOT.get()) }
            .displayItems { _, output ->
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
            .displayItems { _, output ->
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
            .displayItems { _, output ->
                Metal.entries.forEach { metal ->
                    AlloyanceItems.RAW_MATERIALS[metal]?.let(output::accept)
                }
            }
            .build()
    }

    val SPECIAL: DeferredHolder<CreativeModeTab, CreativeModeTab> = TABS.register("special") { location ->
        CreativeModeTab.builder()
            .title(Component.translatable(location.toLanguageKey("itemGroup")))
            .icon { ItemStack(AlloyanceItems.CRUSHER.get()) }
            .displayItems { _, output ->
                output.accept(AlloyanceItems.CRUSHER)
                output.accept(AlloyanceItems.ALLOYER)
                output.accept(AlloyanceItems.INFUSED_IGNATIUS)
            }
            .build()
    }

    val ORES: DeferredHolder<CreativeModeTab, CreativeModeTab> = TABS.register("ores") { location ->
        CreativeModeTab.builder()
            .title(Component.translatable(location.toLanguageKey("itemGroup")))
            .icon { ItemStack(AlloyanceBlocks.DEEP_IRON_ORE.get()) }
            .displayItems { _, output ->
                Metal.entries.forEach { metal ->
                    AlloyanceBlocks.ORES[metal]?.let(output::accept)
                    AlloyanceBlocks.DEEPSLATE_ORES[metal]?.let(output::accept)
                    AlloyanceBlocks.END_ORES[metal]?.let(output::accept)
                    AlloyanceBlocks.NETHER_ORES[metal]?.let(output::accept)
                }
                output.accept(AlloyanceBlocks.PHOSPHORITE_ORE)
                output.accept(AlloyanceBlocks.DEEPSLATE_PHOSPHORITE_ORE)
                output.accept(AlloyanceBlocks.POTASH_ORE)
                output.accept(AlloyanceBlocks.DEEPSLATE_POTASH_ORE)
                output.accept(AlloyanceBlocks.SULFUR_ORE)
                output.accept(AlloyanceBlocks.DEEPSLATE_SULFUR_ORE)
                output.accept(AlloyanceBlocks.TAR_ORE)
            }
            .build()
    }

    val TOOLS: DeferredHolder<CreativeModeTab, CreativeModeTab> = TABS.register("tools") { location ->
        CreativeModeTab.builder()
            .title(Component.translatable(location.toLanguageKey("itemGroup")))
            .icon { ItemStack(AlloyanceItems.DEEP_IRON_PICKAXE.get()) }
            .displayItems { _, output ->
                Metal.entries.forEach { metal ->
                    AlloyanceItems.SHOVELS[metal]?.let(output::accept)
                    AlloyanceItems.PICKAXES[metal]?.let(output::accept)
                    AlloyanceItems.AXES[metal]?.let(output::accept)
                    AlloyanceItems.HOES[metal]?.let(output::accept)
                    AlloyanceItems.SWORDS[metal]?.let(output::accept)
                }
            }.build()
    }

    fun register(bus: IEventBus) {
        TABS.register(bus)
    }
}
