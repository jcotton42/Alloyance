package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.machine.crusher.CrusherMenu
import me.jcotton42.alloyance.machine.crusher.CrusherScreen
import net.minecraft.core.registries.Registries
import net.minecraft.world.inventory.MenuType
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension
import net.neoforged.neoforge.registries.DeferredRegister

object AlloyanceMenuTypes {
    val MENUS_TYPES: DeferredRegister<MenuType<*>> = DeferredRegister.create(Registries.MENU, Alloyance.ID)

    val CRUSHER = MENUS_TYPES.register("crusher") { ->
        IMenuTypeExtension.create(::CrusherMenu)
    }

    fun register(bus: IEventBus) {
        MENUS_TYPES.register(bus)
        bus.addListener(::registerScreens)
    }

    private fun registerScreens(event: RegisterMenuScreensEvent) {
        event.register(CRUSHER.get(), ::CrusherScreen)
    }
}