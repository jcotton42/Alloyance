package me.jcotton42.deluxemetals

import me.jcotton42.deluxemetals.block.ComplexBlockRenderer
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.client.event.EntityRenderersEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

// I hate this, would prefer explicit registration
@Mod.EventBusSubscriber(modid = DeluxeMetals.ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = [Dist.CLIENT])
object ClientSetup {
    @SubscribeEvent
    fun initClient(event: EntityRenderersEvent.RegisterRenderers) {
        event.registerBlockEntityRenderer(Registration.COMPLEX_BLOCK_ENTITY.get(), ::ComplexBlockRenderer)
    }
}