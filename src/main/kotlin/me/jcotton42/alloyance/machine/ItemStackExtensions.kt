package me.jcotton42.alloyance.machine

import me.jcotton42.alloyance.registration.AlloyanceDataComponents
import me.jcotton42.alloyance.registration.AlloyanceDataMaps
import net.minecraft.world.item.ItemStack

fun ItemStack.getFuelSpeed(): Int {
    return this.get(AlloyanceDataComponents.FUEL_SPEED)
        ?: this.itemHolder.getData(AlloyanceDataMaps.FUEL_SPEED)
        ?: 1
}
