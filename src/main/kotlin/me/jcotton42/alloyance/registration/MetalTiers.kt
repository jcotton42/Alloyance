package me.jcotton42.alloyance.registration

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
        metal = Metal.DEEP_IRON,
        uses = 230,
        speed = 6.2f,
        attackDamageBonus = 1.7f,
        enchantmentValue = 19
    )

    val PROMETHEUM = tier(
        metal = Metal.PROMETHEUM,
        uses = 121,
        speed = 4.7f,
        attackDamageBonus = 2.4f,
        enchantmentValue = 14
    )

    val BRONZE = tier(
        metal = Metal.BRONZE,
        uses = 224,
        speed = 7f,
        attackDamageBonus = 2.2f,
        enchantmentValue = 16
    )

    val BRASS = tier(
        metal = Metal.BRASS,
        uses = 895,
        speed = 6.8f,
        attackDamageBonus = 3.2f,
        enchantmentValue = 8
    )

    val DAMASCUS_STEEL = tier(
        metal = Metal.DAMASCUS_STEEL,
        uses = 395,
        speed = 6f,
        attackDamageBonus = 2.5f,
        enchantmentValue = 27
    )

    val SILVER = tier(
        metal = Metal.SILVER,
        uses = 75,
        speed = 11.2f,
        attackDamageBonus = 3.4f,
        enchantmentValue = 19
    )

    val ANGMALLEN = tier(
        metal = Metal.ANGMALLEN,
        uses = 164,
        speed = 7.2f,
        attackDamageBonus = 2.7f,
        enchantmentValue = 32
    )

    val STEEL = tier(
        metal = Metal.STEEL,
        uses = 421,
        speed = 6.4f,
        attackDamageBonus = 2.3f,
        enchantmentValue = 14
    )

    val HEPATIZON = tier(
        metal = Metal.HEPATIZON,
        uses = 761,
        speed = 7.2f,
        attackDamageBonus = 4.2f,
        enchantmentValue = 29
    )

    val BLACK_STEEL = tier(
        metal = Metal.BLACK_STEEL,
        uses = 976,
        speed = 6.6f,
        attackDamageBonus = 3.9f,
        enchantmentValue = 11
    )

    val ELECTRUM = tier(
        metal = Metal.ELECTRUM,
        uses = 366,
        speed = 7.9f,
        attackDamageBonus = 2.7f,
        enchantmentValue = 17
    )

    val ASTRAL_SILVER = tier(
        metal = Metal.ASTRAL_SILVER,
        uses = 523,
        speed = 8.5f,
        attackDamageBonus = 2.8f,
        enchantmentValue = 36
    )

    val IGNATIUS = tier(
        metal = Metal.IGNATIUS,
        uses = 84,
        speed = 4f,
        attackDamageBonus = 2.4f,
        enchantmentValue = 11
    )

    val OURECLASE = tier(
        metal = Metal.OURECLASE,
        uses = 198,
        speed = 8f,
        attackDamageBonus = 4.1f,
        enchantmentValue = 26
    )

    val SHADOW_IRON = tier(
        metal = Metal.SHADOW_IRON,
        uses = 118,
        speed = 6.4f,
        attackDamageBonus = 5.9f,
        enchantmentValue = 9
    )

    val QUICKSILVER = tier(
        metal = Metal.QUICKSILVER,
        uses = 452,
        speed = 10f,
        attackDamageBonus = 2.5f,
        enchantmentValue = 24
    )

    val CERUCLASE = tier(
        metal = Metal.CERUCLASE,
        uses = 103,
        speed = 5f,
        attackDamageBonus = 3.5f,
        enchantmentValue = 16
    )

    val EXIMITE = tier(
        metal = Metal.EXIMITE,
        uses = 789,
        speed = 7.8f,
        attackDamageBonus = 4.9f,
        enchantmentValue = 28
    )

    val KALENDRITE = tier(
        metal = Metal.KALENDRITE,
        uses = 212,
        speed = 8.1f,
        attackDamageBonus = 4f,
        enchantmentValue = 19
    )

    val MIDASIUM = tier(
        metal = Metal.MIDASIUM,
        uses = 89,
        speed = 7f,
        attackDamageBonus = 2.6f,
        enchantmentValue = 28
    )

    val ORICHALCUM = tier(
        metal = Metal.ORICHALCUM,
        uses = 1350,
        speed = 7.7f,
        attackDamageBonus = 6.2f,
        enchantmentValue = 20
    )

    val PLATINUM = tier(
        metal = Metal.PLATINUM,
        uses = 998,
        speed = 9f,
        attackDamageBonus = 3.2f,
        enchantmentValue = 30
    )

    val VULCANITE = tier(
        metal = Metal.VULCANITE,
        uses = 712,
        speed = 8.8f,
        attackDamageBonus = 3.8f,
        enchantmentValue = 19
    )

    val CELENEGIL = tier(
        metal = Metal.CELENEGIL,
        uses = 521,
        speed = 8.2f,
        attackDamageBonus = 4.8f,
        enchantmentValue = 23
    )

    val AMORDRINE = tier(
        metal = Metal.AMORDRINE,
        uses = 832,
        speed = 11f,
        attackDamageBonus = 4f,
        enchantmentValue = 40
    )

    val CARMOT = tier(
        metal = Metal.CARMOT,
        uses = 414,
        speed = 4.9f,
        attackDamageBonus = 1.4f,
        enchantmentValue = 7
    )

    val MITHRIL = tier(
        metal = Metal.MITHRIL,
        uses = 1111,
        speed = 9f,
        attackDamageBonus = 5.5f,
        enchantmentValue = 18
    )

    val SANGUINITE = tier(
        metal = Metal.SANGUINITE,
        uses = 666,
        speed = 11f,
        attackDamageBonus = 8f,
        enchantmentValue = 25
    )

    val VYROXERES = tier(
        metal = Metal.VYROXERES,
        uses = 487,
        speed = 11f,
        attackDamageBonus = 6.2f,
        enchantmentValue = 16
    )

    val SHADOW_STEEL = tier(
        metal = Metal.SHADOW_STEEL,
        uses = 214,
        speed = 9f,
        attackDamageBonus = 6.8f,
        enchantmentValue = 5
    )

    val HADEROTH = tier(
        metal = Metal.HADEROTH,
        uses = 80,
        speed = 12f,
        attackDamageBonus = 4f,
        enchantmentValue = 19
    )

    val DESICHALKOS = tier(
        metal = Metal.DESICHALKOS,
        uses = 2232,
        speed = 10f,
        attackDamageBonus = 3.7f,
        enchantmentValue = 21
    )

    val ATLARUS = tier(
        metal = Metal.ATLARUS,
        uses = 1750,
        speed = 11f,
        attackDamageBonus = 5f,
        enchantmentValue = 22
    )

    val ADAMANTINE = tier(
        metal = Metal.ADAMANTINE,
        uses = 2943,
        speed = 10.5f,
        attackDamageBonus = 5f,
        enchantmentValue = 22
    )

    val INOLASHITE = tier(
        metal = Metal.INOLASHITE,
        uses = 1028,
        speed = 7.2f,
        attackDamageBonus = 5f,
        enchantmentValue = 22
    )

    val KRIK = tier(
        metal = Metal.KRIK,
        uses = 1652,
        speed = 9.5f,
        attackDamageBonus = 4.1f,
        enchantmentValue = 17
    )

    val TARTARITE = tier(
        metal = Metal.TARTARITE,
        uses = 3782,
        speed = 12f,
        attackDamageBonus = 9f,
        enchantmentValue = 14
    )

    val ETHERIUM = tier(
        metal = Metal.ETHERIUM,
        uses = 2056,
        speed = 16f,
        attackDamageBonus = 6f,
        enchantmentValue = 30
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