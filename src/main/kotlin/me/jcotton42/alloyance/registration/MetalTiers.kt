package me.jcotton42.alloyance.registration

import net.minecraft.world.item.Tier
import net.minecraft.world.item.crafting.Ingredient
import net.neoforged.neoforge.common.SimpleTier

object MetalTiers {
    val TIERS = mutableMapOf<Metal, Tier>()

    val DEEP_IRON = tier(
        Metal.DEEP_IRON,
        230,
        6.2f,
        1.7f,
        19
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