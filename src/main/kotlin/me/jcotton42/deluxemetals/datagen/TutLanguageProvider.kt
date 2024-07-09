package me.jcotton42.deluxemetals.datagen

import me.jcotton42.deluxemetals.DeluxeMetals
import me.jcotton42.deluxemetals.Registration
import net.minecraft.data.PackOutput
import net.minecraftforge.common.data.LanguageProvider

class TutLanguageProvider(output: PackOutput, locale: String) : LanguageProvider(output, DeluxeMetals.ID, locale) {
    override fun addTranslations() {
        add(Registration.SIMPLE_BLOCK.get(), "Simple Block")
        add(Registration.COMPLEX_BLOCK_ITEM.get(), "Complex Block")
    }
}