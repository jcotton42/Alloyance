package me.jcotton42.deluxemetals.datagen

import me.jcotton42.deluxemetals.DeluxeMetals
import me.jcotton42.deluxemetals.Registration
import me.jcotton42.deluxemetals.block.ComplexBlockEntity
import net.minecraft.data.loot.packs.VanillaBlockLoot
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.DynamicLoot
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction
import net.minecraft.world.level.storage.loot.functions.SetContainerContents
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import net.minecraftforge.registries.ForgeRegistries

class TutLootTables : VanillaBlockLoot() {
    override fun generate() {
        dropSelf(Registration.SIMPLE_BLOCK.get())
        createStandardTable(Registration.COMPLEX_BLOCK.get(), Registration.COMPLEX_BLOCK_ENTITY.get())
    }

    override fun getKnownBlocks(): Iterable<Block> = ForgeRegistries.BLOCKS.entries.stream()
        .filter { it.key.location().namespace.equals(DeluxeMetals.ID) }
        .map { it.value }
        .toList()

    fun createStandardTable(block: Block, type: BlockEntityType<*>) {
        val builder = LootPool.lootPool()
            .setRolls(ConstantValue.exactly(1f))
            .add(LootItem.lootTableItem(block)
                .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                    .copy(ComplexBlockEntity.ITEMS_TAG, "BlockEntityTag.${ComplexBlockEntity.ITEMS_TAG}",
                        CopyNbtFunction.MergeStrategy.REPLACE
                    ))
                .apply(SetContainerContents.setContents(type)
                    .withEntry(DynamicLoot.dynamicEntry(ResourceLocation("minecraft", "contents"))))
            )
        add(block, LootTable.lootTable().withPool(builder))
    }
}