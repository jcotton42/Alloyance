package me.jcotton42.alloyance.datagen

import net.minecraft.data.loot.LootTableProvider
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.minecraftforge.data.event.GatherDataEvent

fun generateData(event: GatherDataEvent) {
    val generator = event.generator
    val packOutput = generator.packOutput
    val lookupProvider = event.lookupProvider

    generator.addProvider(event.includeClient(), AlloyanceBlockStates(packOutput, event.existingFileHelper))
    generator.addProvider(event.includeClient(), AlloyanceItemModels(packOutput, event.existingFileHelper))
    generator.addProvider(event.includeClient(), AlloyanceLanguageProvider(packOutput, "en_us"))

    val blockTags = AlloyanceBlockTags(packOutput, lookupProvider, event.existingFileHelper)
    generator.addProvider(event.includeServer(), blockTags)
    generator.addProvider(event.includeServer(), AlloyanceItemTags(packOutput, lookupProvider, blockTags, event.existingFileHelper))
    generator.addProvider(event.includeServer(), AlloyanceRecipes(packOutput))
    generator.addProvider(event.includeServer(), LootTableProvider(packOutput, emptySet(), listOf(
        LootTableProvider.SubProviderEntry(::AlloyanceLootTables, LootContextParamSets.BLOCK)
    )))
}