package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.registration.AlloyanceSounds
import net.minecraft.data.PackOutput
import net.minecraft.sounds.SoundEvent
import net.neoforged.neoforge.common.data.ExistingFileHelper
import net.neoforged.neoforge.common.data.SoundDefinition
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider
import net.neoforged.neoforge.registries.DeferredHolder

class AlloyanceSoundDefinitionsProvider(output: PackOutput, existingFileHelper: ExistingFileHelper) :
    SoundDefinitionsProvider(output, Alloyance.ID, existingFileHelper) {
    override fun registerSounds() {
        standardSound(AlloyanceSounds.ALLOYER_AMBIENCE, stream = true)
        standardSound(AlloyanceSounds.ALLOYER_IMPACT, stream = false)
        standardSound(AlloyanceSounds.ALLOYER_WINDUP, stream = false)

        standardSound(AlloyanceSounds.CRUSHER_AMBIENCE, stream = true)
        standardSound(AlloyanceSounds.CRUSHER_IMPACT, stream = false)
        standardSound(AlloyanceSounds.CRUSHER_WINDUP, stream = false)
    }

    private fun standardSound(soundEvent: DeferredHolder<SoundEvent, SoundEvent>, stream: Boolean) {
        val definition = SoundDefinition.definition().with(
            sound(soundEvent.id).stream(stream)
        ).subtitle(soundEvent.id.toLanguageKey("sound"))
        add(soundEvent, definition)
    }
}