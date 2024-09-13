package me.jcotton42.alloyance.datagen

import net.minecraft.data.loot.LootTableProvider
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.neoforged.neoforge.data.event.GatherDataEvent

fun generateData(event: GatherDataEvent) {
    val existingFileHelper = event.existingFileHelper
    val generator = event.generator
    val packOutput = generator.packOutput
    val lookupProvider = event.lookupProvider

    generator.addProvider(event.includeClient(), AlloyanceBlockStatesProvider(packOutput, existingFileHelper))
    generator.addProvider(event.includeClient(), AlloyanceItemModelsProvider(packOutput, existingFileHelper))
    generator.addProvider(event.includeClient(), AlloyanceEnglishLanguageProvider(packOutput))
    generator.addProvider(event.includeClient(), AlloyanceSoundDefinitionsProvider(packOutput, existingFileHelper))

    val blockTags = AlloyanceBlockTagsProvider(packOutput, lookupProvider, existingFileHelper)
    generator.addProvider(event.includeServer(), blockTags)
    generator.addProvider(event.includeServer(), AlloyanceItemTagsProvider(packOutput, lookupProvider, blockTags, existingFileHelper))
    generator.addProvider(event.includeServer(), AlloyanceBiomeTagsProvider(packOutput, lookupProvider, existingFileHelper))
    generator.addProvider(event.includeServer(), AlloyanceRecipesProvider(packOutput, lookupProvider))
    generator.addProvider(event.includeServer(), AlloyanceWorldGenProvider(packOutput, lookupProvider))
    generator.addProvider(event.includeServer(), AlloyanceDataMapProvider(packOutput, lookupProvider))

    val blockLoot = LootTableProvider.SubProviderEntry(
        ::AlloyanceBlockLootProvider,
        LootContextParamSets.BLOCK
    )
    generator.addProvider(event.includeServer(),
        LootTableProvider(packOutput, emptySet(), listOf(blockLoot), lookupProvider)
    )
}