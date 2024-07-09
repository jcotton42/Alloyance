package me.jcotton42.deluxemetals.datagen

import net.minecraft.data.loot.LootTableProvider
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.minecraftforge.data.event.GatherDataEvent

object DataGeneration {
    fun generate(event: GatherDataEvent) {
        val generator = event.generator
        val packOutput = generator.packOutput
        val lookupProvider = event.lookupProvider

        generator.addProvider(event.includeClient(), TutBlockStates(packOutput, event.existingFileHelper))
        generator.addProvider(event.includeClient(), TutItemModels(packOutput, event.existingFileHelper))
        generator.addProvider(event.includeClient(), TutLanguageProvider(packOutput, "en_us"))

        val blockTags = TutBlockTags(packOutput, lookupProvider, event.existingFileHelper)
        generator.addProvider(event.includeServer(), blockTags)
        generator.addProvider(event.includeServer(), TutItemTags(packOutput, lookupProvider, blockTags, event.existingFileHelper))
        generator.addProvider(event.includeServer(), TutRecipes(packOutput))
        generator.addProvider(event.includeServer(), LootTableProvider(packOutput, emptySet(), listOf(
            LootTableProvider.SubProviderEntry(::TutLootTables, LootContextParamSets.BLOCK)
        )))
    }
}