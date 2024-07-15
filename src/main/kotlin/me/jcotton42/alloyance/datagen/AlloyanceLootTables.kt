package me.jcotton42.alloyance.datagen

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.Registration
import net.minecraft.data.loot.packs.VanillaBlockLoot
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraftforge.registries.ForgeRegistries

class AlloyanceLootTables: VanillaBlockLoot() {
    override fun generate() {
        dropSelf(Registration.DEEP_IRON_BLOCK.get())
        oreDropsItem(Registration.DEEP_IRON_ORE.get(), Registration.RAW_DEEP_IRON.get())
    }

    override fun getKnownBlocks(): Iterable<Block> = ForgeRegistries.BLOCKS.entries.stream()
        .filter { it.key.location().namespace == Alloyance.ID }
        .map { it.value }
        .toList()

    private fun oreDropsItem(block: Block, item: Item) {
        add(block, createOreDrop(block, item))
    }
}