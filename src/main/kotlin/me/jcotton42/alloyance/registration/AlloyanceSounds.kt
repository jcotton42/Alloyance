package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister

object AlloyanceSounds {
    val SOUND_EVENTS: DeferredRegister<SoundEvent> = DeferredRegister.create(Registries.SOUND_EVENT, Alloyance.ID)

    val ALLOYER_AMBIENCE = sound("machines/alloyer_ambience")
    val ALLOYER_IMPACT = sound("machines/alloyer_impact")
    val ALLOYER_WINDUP = sound("machines/alloyer_windup")

    val CRUSHER_AMBIENCE = sound("machines/crusher_ambience")
    val CRUSHER_IMPACT = sound("machines/crusher_impact")
    val CRUSHER_WINDUP = sound("machines/crusher_windup")

    fun register(bus: IEventBus) {
        SOUND_EVENTS.register(bus)
    }

    private fun sound(path: String): DeferredHolder<SoundEvent, SoundEvent> {
        return SOUND_EVENTS.register(path) { ->
            SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Alloyance.ID, path))
        }
    }
}