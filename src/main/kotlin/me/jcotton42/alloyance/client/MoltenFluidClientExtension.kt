package me.jcotton42.alloyance.client

import me.jcotton42.alloyance.Alloyance
import net.minecraft.client.Minecraft
import net.minecraft.resources.ResourceLocation
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions

// TODO change from the Minecraft ones, this is just for testing
private val RENDER_OVERLAY = ResourceLocation.withDefaultNamespace("textures/misc/underwater.png")
private val STILL = ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "block/molten_metal_still")
private val FLOW = ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "block/molten_metal_flow")
private val BLOCK_OVERLAY = ResourceLocation.withDefaultNamespace("block/water_overlay")

class MoltenFluidClientExtension(private val color: Int): IClientFluidTypeExtensions {
    override fun getStillTexture(): ResourceLocation = STILL

    override fun getFlowingTexture(): ResourceLocation = FLOW

    override fun getOverlayTexture(): ResourceLocation = BLOCK_OVERLAY

    override fun getRenderOverlayTexture(mc: Minecraft): ResourceLocation = RENDER_OVERLAY

    override fun getTintColor(): Int = color
}