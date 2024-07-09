package me.jcotton42.deluxemetals.block

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.math.Axis
import me.jcotton42.deluxemetals.DeluxeMetals
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.LightTexture
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.inventory.InventoryMenu
import net.minecraft.world.item.ItemDisplayContext
import net.minecraftforge.common.capabilities.ForgeCapabilities

private val LIGHT = ResourceLocation(DeluxeMetals.ID, "block/light")

class ComplexBlockRenderer(context: BlockEntityRendererProvider.Context) : BlockEntityRenderer<ComplexBlockEntity> {
    override fun render(
        blockEntity: ComplexBlockEntity,
        partialTick: Float,
        poseStack: PoseStack,
        buffer: MultiBufferSource,
        packedLight: Int,
        packedOverlay: Int
    ) = blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent { handler ->
        val stack = handler.getStackInSlot(ComplexBlockEntity.SLOT)
        if (stack.isEmpty) {
            return@ifPresent
        }

        val itemRenderer = Minecraft.getInstance().itemRenderer
        val millis = System.currentTimeMillis()

        poseStack.pushPose()
        poseStack.pushPose()
        poseStack.scale(0.5f, 0.5f, 0.5f)
        poseStack.translate(1f, 2.8f, 1f)
        val angle = (millis / 45) % 360
        poseStack.mulPose(Axis.YP.rotationDegrees(angle.toFloat()))
        itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, LightTexture.FULL_BRIGHT, packedOverlay, poseStack, buffer,
            Minecraft.getInstance().level, 0
        )
        poseStack.popPose()

        poseStack.translate(0f, 0.5f, 0f)
        renderBillboardQuadBright(poseStack, buffer.getBuffer(RenderType.translucent()), 0.5f, LIGHT)
        poseStack.popPose()
    }

    private fun renderBillboardQuadBright(matrixStack: PoseStack, builder: VertexConsumer, scale: Float, texture: ResourceLocation) {
        val b1 = LightTexture.FULL_BRIGHT shr 16 and 65535
        val b2 = LightTexture.FULL_BRIGHT and 65535
        val sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(texture)
        matrixStack.pushPose()
        matrixStack.translate(0.5, 0.95, 0.5)
        val rotation = Minecraft.getInstance().gameRenderer.mainCamera.rotation()
        matrixStack.mulPose(rotation)
        val matrix = matrixStack.last().pose()
        builder.vertex(matrix, -scale, -scale, 0.0f).color(255, 255, 255, 255).uv(sprite.u0, sprite.v0).uv2(b1, b2).normal(1f, 0f, 0f).endVertex();
        builder.vertex(matrix, -scale, scale, 0.0f).color(255, 255, 255, 255).uv(sprite.u0, sprite.v1).uv2(b1, b2).normal(1f, 0f, 0f).endVertex();
        builder.vertex(matrix, scale, scale, 0.0f).color(255, 255, 255, 255).uv(sprite.u1, sprite.v1).uv2(b1, b2).normal(1f, 0f, 0f).endVertex();
        builder.vertex(matrix, scale, -scale, 0.0f).color(255, 255, 255, 255).uv(sprite.u1, sprite.v0).uv2(b1, b2).normal(1f, 0f, 0f).endVertex();
        matrixStack.popPose()
    }
}