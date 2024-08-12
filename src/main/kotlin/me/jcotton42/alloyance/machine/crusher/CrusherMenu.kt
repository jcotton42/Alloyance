package me.jcotton42.alloyance.machine.crusher

import me.jcotton42.alloyance.machine.crusher.CrusherBlockEntity.Companion.FUEL_SLOT
import me.jcotton42.alloyance.machine.crusher.CrusherBlockEntity.Companion.INPUT_SLOT
import me.jcotton42.alloyance.machine.crusher.CrusherBlockEntity.Companion.OUTPUT_SLOT_1
import me.jcotton42.alloyance.machine.crusher.CrusherBlockEntity.Companion.OUTPUT_SLOT_2
import me.jcotton42.alloyance.machine.crusher.CrusherBlockEntity.Companion.OUTPUT_SLOT_3
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import me.jcotton42.alloyance.registration.AlloyanceMenuTypes
import net.minecraft.core.BlockPos
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerLevelAccess
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.items.IItemHandler
import net.neoforged.neoforge.items.ItemStackHandler
import net.neoforged.neoforge.items.SlotItemHandler

class CrusherMenu(
    containerId: Int,
    playerInventory: Inventory,
    crusherInventory: IItemHandler,
    private val pos: BlockPos,
): AbstractContainerMenu(
    AlloyanceMenuTypes.CRUSHER.get(),
    containerId
) {
    init {
        // if this breaks, bump the y coords by 31
        addSlot(SlotItemHandler(crusherInventory, INPUT_SLOT, 61, 27))
        addSlot(SlotItemHandler(crusherInventory, FUEL_SLOT, 129, 79))
        addSlot(SlotItemHandler(crusherInventory, OUTPUT_SLOT_1, 67, 67))
        addSlot(SlotItemHandler(crusherInventory, OUTPUT_SLOT_2, 48, 67))
        addSlot(SlotItemHandler(crusherInventory, OUTPUT_SLOT_3, 29, 67))

        // player inventory
        for (y in 0..<3) {
            for (x in 0..<9) {
                this.addSlot(Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 115 + y * 18))
            }
        }

        // player hotbar
        for (x in 0..<9) {
            this.addSlot(Slot(playerInventory, x, 8 + x * 18, 173))
        }

        // TODO add data slots
    }

    constructor(
        containerId: Int,
        playerInventory: Inventory,
        extraData: FriendlyByteBuf,
    ): this(containerId, playerInventory, ItemStackHandler(CrusherBlockEntity.SLOT_COUNT), extraData.readBlockPos())

    override fun quickMoveStack(
        player: Player,
        quickMovedIndex: Int
    ): ItemStack {
        val playerInventoryStart = 5
        val playerInventoryEnd = 41

        val quickMovedSlot = slots[quickMovedIndex]
        if (!quickMovedSlot.hasItem()) {
            return ItemStack.EMPTY
        }

        val stackToMove = quickMovedSlot.item
        val quickMovedStack = stackToMove.copy()

        if (quickMovedIndex < playerInventoryStart) {
            // move to player inventory
            if (!moveItemStackTo(stackToMove, playerInventoryStart, playerInventoryEnd, false)) {
                return ItemStack.EMPTY
            }
        }
        else {
            if (!moveItemStackTo(stackToMove, INPUT_SLOT, FUEL_SLOT + 1, true)) {
                return ItemStack.EMPTY
            }
        }

        if (stackToMove.isEmpty) {
            quickMovedSlot.set(ItemStack.EMPTY)
        } else {
            quickMovedSlot.setChanged()
        }

        if (stackToMove.count == quickMovedStack.count) {
            return ItemStack.EMPTY
        }

        quickMovedSlot.onTake(player, stackToMove)

        return quickMovedStack
    }

    override fun stillValid(player: Player): Boolean {
        return stillValid(ContainerLevelAccess.create(player.level(), pos), player, AlloyanceBlocks.CRUSHER.get())
    }
}