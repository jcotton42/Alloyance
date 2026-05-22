package me.jcotton42.alloyance.registration

import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Tier
import net.minecraft.world.item.crafting.Ingredient
import net.neoforged.neoforge.common.SimpleTier
import net.neoforged.neoforge.common.Tags

object MetalTiers {
    val TIERS = mutableMapOf<Metal, Tier>()

    val COPPER = SimpleTier(
        AlloyanceBlockTags.INCORRECT_FOR_COPPER_TOOL,
        120,
        6.1f,
        1.2f,
        25
    ) { Ingredient.of(Tags.Items.INGOTS_COPPER) }

    val DEEP_IRON = tier(
        Metal.DEEP_IRON,
        230,
        6.2f,
        1.7f,
        19
    )

    val PROMETHEUM = tier(
        Metal.PROMETHEUM,
        121,
        4.7f,
        2.4f,
        14
    )


    private fun tier(metal: Metal, uses: Int, speed: Float, attackDamageBonus: Float, enchantmentValue: Int): Tier {
        val tier = SimpleTier(
            AlloyanceBlockTags.INCORRECT_FOR_TOOL.getValue(metal),
            uses,
            speed,
            attackDamageBonus,
            enchantmentValue
        ) { Ingredient.of(AlloyanceItemTags.INGOTS.getValue(metal)) }
        TIERS[metal] = tier
        return tier
    }
}