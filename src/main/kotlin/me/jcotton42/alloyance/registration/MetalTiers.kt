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

    val BRONZE = tier(
        Metal.BRONZE,
        224,
        7f,
        2.2f,
        16
    )

    val BRASS = tier(
        Metal.BRASS,
        895,
        6.8f,
        3.2f,
        8
    )

    val DAMASCUS_STEEL = tier(
        Metal.DAMASCUS_STEEL,
        395,
        6f,
        2.5f,
        27
    )

    val SILVER = tier(
        Metal.SILVER,
        75,
        11.2f,
        3.4f,
        19
    )

    val ANGMALLEN = tier(
        Metal.ANGMALLEN,
        164,
        7.2f,
        2.7f,
        32
    )

    val STEEL = tier(
        Metal.STEEL,
        421,
        6.4f,
        2.3f,
        14
    )

    val HEPATIZON = tier(
        Metal.HEPATIZON,
        761,
        7.2f,
        4.2f,
        29
    )

    val BLACK_STEEL = tier(
        Metal.BLACK_STEEL,
        976,
        6.6f,
        3.9f,
        11
    )

    val ELECTRUM = tier(
        Metal.ELECTRUM,
        366,
        7.9f,
        2.7f,
        17
    )

    val ASTRAL_SILVER = tier(
        Metal.ASTRAL_SILVER,
        523,
        8.5f,
        2.8f,
        36
    )

    val IGNATIUS = tier(
        Metal.IGNATIUS,
        84,
        4f,
        2.4f,
        11
    )

    val OURECLASE = tier(
        Metal.OURECLASE,
        198,
        8f,
        4.1f,
        26
    )

    val SHADOW_IRON = tier(
        Metal.SHADOW_IRON,
        118,
        6.4f,
        5.9f,
        9
    )

    val QUICKSILVER = tier(
        Metal.QUICKSILVER,
        452,
        10f,
        2.5f,
        24
    )

    val CERUCLASE = tier(
        Metal.CERUCLASE,
        103,
        5f,
        3.5f,
        16
    )

    val EXIMITE = tier(
        Metal.EXIMITE,
        789,
        7.8f,
        4.9f,
        28
    )

    val KALENDRITE = tier(
        Metal.KALENDRITE,
        212,
        8.1f,
        4f,
        19
    )

    val MIDASIUM = tier(
        Metal.MIDASIUM,
        89,
        7f,
        2.6f,
        28
    )

    val ORICHALCUM = tier(
        Metal.ORICHALCUM,
        1350,
        7.7f,
        6.2f,
        20
    )

    val PLATINUM = tier(
        Metal.PLATINUM,
        998,
        9f,
        3.2f,
        30
    )

    val VULCANITE = tier(
        Metal.VULCANITE,
        712,
        8.8f,
        3.8f,
        19
    )

    val CELENEGIL = tier(
        Metal.CELENEGIL,
        521,
        8.2f,
        4.8f,
        23
    )

    val AMORDRINE = tier(
        Metal.AMORDRINE,
        832,
        11f,
        4f,
        40
    )

    val CARMOT = tier(
        Metal.CARMOT,
        414,
        4.9f,
        1.4f,
        7
    )

    val MITHRIL = tier(
        Metal.MITHRIL,
        1111,
        9f,
        5.5f,
        18
    )

    val SANGUINITE = tier(
        Metal.SANGUINITE,
        666,
        11f,
        8f,
        25
    )

    val VYROXERES = tier(
        Metal.VYROXERES,
        487,
        11f,
        6.2f,
        16
    )

    val SHADOW_STEEL = tier(
        Metal.SHADOW_STEEL,
        214,
        9f,
        6.8f,
        5
    )

    val HADEROTH = tier(
        Metal.HADEROTH,
        80,
        12f,
        4f,
        19
    )

    val DESICHALKOS = tier(
        Metal.DESICHALKOS,
        2232,
        10f,
        3.7f,
        21
    )

    val ATLARUS = tier(
        Metal.ATLARUS,
        1750,
        11f,
        5f,
        22
    )

    val ADAMANTINE = tier(
        Metal.ADAMANTINE,
        2943,
        10.5f,
        5f,
        22
    )

    val INOLASHITE = tier(
        Metal.INOLASHITE,
        1028,
        7.2f,
        5f,
        22
    )

    val KRIK = tier(
        Metal.KRIK,
        1652,
        9.5f,
        4.1f,
        17
    )

    val TARTARITE = tier(
        Metal.TARTARITE,
        3782,
        12f,
        9f,
        14
    )

    val ETHERIUM = tier(
        Metal.ETHERIUM,
        2056,
        16f,
        6f,
        30
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