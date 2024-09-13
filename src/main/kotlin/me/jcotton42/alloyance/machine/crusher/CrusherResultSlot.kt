package me.jcotton42.alloyance.machine.crusher

import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.items.IItemHandler
import net.neoforged.neoforge.items.SlotItemHandler
import kotlin.math.min

class CrusherResultSlot(
    private val player: Player,
    private val grantAchievements: (ServerPlayer) -> Unit,
    itemHandler: IItemHandler,
    index: Int,
    xPosition: Int,
    yPosition: Int
) : SlotItemHandler(itemHandler, index, xPosition, yPosition) {
    private var removeCount: Int = 0

    override fun remove(amount: Int): ItemStack {
        if (hasItem()) {
            removeCount += min(amount, item.count)
        }

        return super.remove(amount)
    }

    override fun onTake(player: Player, stack: ItemStack) {
        checkTakeAchievements(stack)
        super.onTake(player, stack)
    }

    override fun onQuickCraft(stack: ItemStack, amount: Int) {
        removeCount += amount
        checkTakeAchievements(stack)
    }

    override fun checkTakeAchievements(stack: ItemStack) {
        stack.onCraftedBy(player.level(), player, removeCount)
        if (player is ServerPlayer) {
            grantAchievements(player)
        }

        removeCount = 0
    }
}