package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.machine.alloyer.AlloyerMenu
import me.jcotton42.alloyance.machine.alloyer.AlloyerScreen
import me.jcotton42.alloyance.machine.crusher.CrusherMenu
import me.jcotton42.alloyance.machine.crusher.CrusherScreen
import net.minecraft.core.registries.Registries
import net.minecraft.world.inventory.MenuType
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister

object AlloyanceMenuTypes {
    val MENU_TYPES: DeferredRegister<MenuType<*>> = DeferredRegister.create(Registries.MENU, Alloyance.ID)

    val ALLOYER: DeferredHolder<MenuType<*>, MenuType<AlloyerMenu>> = MENU_TYPES.register("alloyer") { ->
        IMenuTypeExtension.create(::AlloyerMenu)
    }

    val CRUSHER: DeferredHolder<MenuType<*>, MenuType<CrusherMenu>> = MENU_TYPES.register("crusher") { ->
        IMenuTypeExtension.create(::CrusherMenu)
    }

    fun register(bus: IEventBus) {
        MENU_TYPES.register(bus)
        bus.addListener(::registerScreens)
    }

    private fun registerScreens(event: RegisterMenuScreensEvent) {
        event.register(ALLOYER.get(), ::AlloyerScreen)
        event.register(CRUSHER.get(), ::CrusherScreen)
    }
}