package me.jcotton42.alloyance.client

import me.jcotton42.alloyance.registration.AlloyanceDataComponents
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.RenderTooltipEvent

const val MASK: Int = 0xFF000000.toInt()

object AlloyanceClientEvents {
    @SubscribeEvent
    fun tooltipColor(event: RenderTooltipEvent.Color) {
        val style = event.itemStack.get(AlloyanceDataComponents.TOOLTIP_STYLE)
            ?: return
        event.borderStart = MASK or style.color
        event.borderEnd = MASK or style.color
    }
}