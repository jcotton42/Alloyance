package me.jcotton42.alloyance.machine.crusher

import me.jcotton42.alloyance.Alloyance
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Inventory

class CrusherScreen(
    menu: CrusherMenu,
    playerInventory: Inventory,
    title: Component
) : AbstractContainerScreen<CrusherMenu>(
        menu,
        playerInventory,
        title
) {
    companion object {
        private val BACKGROUND = ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "textures/gui/crusher.png")
        private val BURNING_SPRITE = ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "crusher/burning")
        private val MELTING_SPRITE = ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "crusher/melting")
        private val METER_SPRITE = ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "crusher/meter")
    }

    init {
        imageWidth = 176
        imageHeight = 197
        titleLabelX = 10
        titleLabelY = 9
    }

    override fun renderBg(guiGraphics: GuiGraphics, partialTick: Float, mouseX: Int, mouseY: Int) {
        guiGraphics.blit(BACKGROUND, leftPos, topPos, 0, 0, imageWidth, imageHeight)
        guiGraphics.blitSprite(MELTING_SPRITE, leftPos + 59, topPos + 45, 21, 18)
        val burnLeftPercentage = 0.25f
        val burnHeight = (burnLeftPercentage * 17).toInt()
        val burnSkip = 17 - burnHeight
        guiGraphics.blitSprite(BURNING_SPRITE, 17, 17, 0, 17 - burnHeight, leftPos + 128, topPos + 61 + burnSkip, 17, burnHeight)
        val crushProgressPercentage = 0.75f
        val crushHeight = (crushProgressPercentage * 33).toInt()
        val crushSkip = 33 - crushHeight
        guiGraphics.blitSprite(METER_SPRITE, 7, 33, 0, 33 - crushHeight, leftPos + 93, topPos + 65 + crushSkip, 7, crushHeight)
    }

    override fun renderLabels(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int) {
        // a nice Oureclase orange
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 0xEC8D69, false)
    }
}