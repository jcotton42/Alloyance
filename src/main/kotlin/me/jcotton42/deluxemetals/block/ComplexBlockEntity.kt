package me.jcotton42.deluxemetals.block

import me.jcotton42.deluxemetals.Registration
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.Connection
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ForgeCapabilities
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.items.ItemStackHandler

class ComplexBlockEntity(pos: BlockPos, state: BlockState): BlockEntity(Registration.COMPLEX_BLOCK_ENTITY.get(), pos, state) {
    companion object {
        const val ITEMS_TAG = "Inventory"
        const val SLOT_COUNT = 1
        const val SLOT = 0
    }

    private val items = createItemHandler()
    private val itemHandler = LazyOptional.of { items }

    fun tickServer() {
        if (level!!.gameTime % 20 != 0L) {
            return
        }

        val stack = items.getStackInSlot(SLOT)
        if (stack.isEmpty) {
            return
        }

        if (!stack.isDamageableItem) {
            ejectItem()
            return
        }

        val value = stack.damageValue
        if (value > 0) {
            stack.damageValue = value - 1
            return
        }

        ejectItem()
    }

    private fun ejectItem() {
        val pos = worldPosition.relative(Direction.UP)
        Block.popResource(level!!, pos, items.extractItem(SLOT, 1, false))
    }

    override fun invalidateCaps() {
        super.invalidateCaps()
        itemHandler.invalidate()
    }

    override fun <T : Any?> getCapability(cap: Capability<T?>, side: Direction?): LazyOptional<T?> = when (cap) {
        ForgeCapabilities.ITEM_HANDLER -> itemHandler.cast()
        else -> super.getCapability(cap, side)
    }

    override fun saveAdditional(tag: CompoundTag) {
        super.saveAdditional(tag)
        saveClientData(tag)
    }

    private fun saveClientData(tag: CompoundTag) {
        tag.put(ITEMS_TAG, items.serializeNBT())
    }

    override fun load(tag: CompoundTag) {
        super.load(tag)
        loadClientData(tag)
    }

    private fun loadClientData(tag: CompoundTag) {
        if (tag.contains(ITEMS_TAG)) {
            items.deserializeNBT(tag.getCompound(ITEMS_TAG))
        }
    }

    override fun getUpdateTag(): CompoundTag {
        val tag = super.getUpdateTag()
        saveClientData(tag)
        return tag
    }

    override fun handleUpdateTag(tag: CompoundTag?) {
        if (tag != null) {
            loadClientData(tag)
        }
    }

    override fun getUpdatePacket(): ClientboundBlockEntityDataPacket = ClientboundBlockEntityDataPacket.create(this)

    override fun onDataPacket(net: Connection?, pkt: ClientboundBlockEntityDataPacket?) {
        val tag = pkt?.tag
        if (tag != null) {
            handleUpdateTag(tag)
        }
    }

    private fun createItemHandler() = object: ItemStackHandler(SLOT_COUNT) {
        override fun onContentsChanged(slot: Int) {
            setChanged()
            level!!.sendBlockUpdated(worldPosition, blockState, blockState, Block.UPDATE_ALL)
        }
    }
}