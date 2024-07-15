package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.Registration
import net.minecraft.data.PackOutput
import net.minecraftforge.common.data.LanguageProvider

class AlloyanceLanguageProvider(
    output: PackOutput,
    locale: String
): LanguageProvider(
    output,
    Alloyance.ID,
    locale
) {
    override fun addTranslations() {
        add(Registration.DEEP_IRON_ORE.get(), "Deep Iron Ore")
        add(Registration.RAW_DEEP_IRON.get(), "Raw Deep Iron")
        add(Registration.DEEP_IRON_INGOT.get(), "Deep Iron Ingot")
        add(Registration.DEEP_IRON_BLOCK.get(), "Block of Deep Iron")
        add(Registration.DEEP_IRON_NUGGET.get(), "Deep Iron Nugget")
    }
}