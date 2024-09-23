package me.jcotton42.alloyance.machine.alloyer

import me.jcotton42.alloyance.Alloyance
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Inventory

class AlloyerScreen(
    menu: AlloyerMenu,
    playerInventory: Inventory,
    title: Component
) : AbstractContainerScreen<AlloyerMenu>(
        menu,
        playerInventory,
        title
) {
    companion object {
        private val BACKGROUND = ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "textures/gui/alloyer.png")
        private val BURNING_SPRITE = ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "alloyer/burning")
        private val METER_SPRITE = ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "alloyer/meter")
        private val SPOUT_SPRITE = ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "alloyer/spout")
    }

    init {
        imageWidth = 176
        imageHeight = 197
        titleLabelX = 10
        titleLabelY = 9
    }

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        super.render(guiGraphics, mouseX, mouseY, partialTick)
        this.renderTooltip(guiGraphics, mouseX, mouseY)
    }

    override fun renderBg(guiGraphics: GuiGraphics, partialTick: Float, mouseX: Int, mouseY: Int) {
        val alloyingTime = menu.containerData.get(AlloyerBlockEntity.ALLOYING_TIME_INDEX)
        val totalAlloyingTime = menu.containerData.get(AlloyerBlockEntity.TOTAL_ALLOYING_TIME_INDEX)
        val burnTimeRemaining = menu.containerData.get(AlloyerBlockEntity.BURN_TIME_REMAINING_INDEX)
        val totalBurnTime = menu.containerData.get(AlloyerBlockEntity.TOTAL_BURN_TIME_INDEX)

        guiGraphics.blit(BACKGROUND, leftPos, topPos, 0, 0, imageWidth, imageHeight)

        val burnLeftPercentage = burnTimeRemaining / totalBurnTime.toFloat()
        val burnHeight = (burnLeftPercentage * 17).toInt()
        val burnSkip = 17 - burnHeight
        guiGraphics.blitSprite(BURNING_SPRITE, 17, 17, 0, 17 - burnHeight, leftPos + 110, topPos + 61 + burnSkip, 17, burnHeight)

        val alloyProgressPercentage = alloyingTime / totalAlloyingTime.toFloat()
        val alloyHeight = (alloyProgressPercentage * 33).toInt()
        val alloySkip = 33 - alloyHeight
        guiGraphics.blitSprite(METER_SPRITE, 7, 33, 0, 33 - alloyHeight, leftPos + 40, topPos + 66 + alloySkip, 7, alloyHeight)

        guiGraphics.blitSprite(SPOUT_SPRITE, 10, 25, 0, 0, leftPos + 52, topPos + 32, 10, alloyHeight)
    }

    override fun renderLabels(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int) {
        // a nice Osmium blue
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 0xB40D8, false)
    }
}