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
        uses = 250,
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
        uses = 657,
        speed = 7f,
        attackDamageBonus = 2.2f,
        enchantmentValue = 16
    )

    val BRASS = tier(
        metal = Metal.BRASS,
        uses = 388,
        speed = 6.8f,
        attackDamageBonus = 3.2f,
        enchantmentValue = 8
    )

    val DAMASCUS_STEEL = tier(
        metal = Metal.DAMASCUS_STEEL,
        uses = 988,
        speed = 6f,
        attackDamageBonus = 2.5f,
        enchantmentValue = 27
    )

    val SILVER = tier(
        metal = Metal.SILVER,
        uses = 100,
        speed = 11.2f,
        attackDamageBonus = 3.4f,
        enchantmentValue = 17
    )

    val ANGMALLEN = tier(
        metal = Metal.ANGMALLEN,
        uses = 412,
        speed = 7.2f,
        attackDamageBonus = 2.7f,
        enchantmentValue = 32
    )

    val STEEL = tier(
        metal = Metal.STEEL,
        uses = 1096,
        speed = 6.4f,
        attackDamageBonus = 2.3f,
        enchantmentValue = 14
    )

    val HEPATIZON = tier(
        metal = Metal.HEPATIZON,
        uses = 1386,
        speed = 7.2f,
        attackDamageBonus = 4.2f,
        enchantmentValue = 29
    )

    val BLACK_STEEL = tier(
        metal = Metal.BLACK_STEEL,
        uses = 1213,
        speed = 6.6f,
        attackDamageBonus = 3.9f,
        enchantmentValue = 11
    )

    val ELECTRUM = tier(
        metal = Metal.ELECTRUM,
        uses = 366,
        speed = 7.9f,
        attackDamageBonus = 2.7f,
        enchantmentValue = 22
    )

    val ASTRAL_SILVER = tier(
        metal = Metal.ASTRAL_SILVER,
        uses = 820,
        speed = 8.5f,
        attackDamageBonus = 2.8f,
        enchantmentValue = 36
    )

    val IGNATIUS = tier(
        metal = Metal.IGNATIUS,
        uses = 384,
        speed = 4f,
        attackDamageBonus = 2.4f,
        enchantmentValue = 11
    )

    val OURECLASE = tier(
        metal = Metal.OURECLASE,
        uses = 555,
        speed = 8f,
        attackDamageBonus = 4.1f,
        enchantmentValue = 26
    )

    val SHADOW_IRON = tier(
        metal = Metal.SHADOW_IRON,
        uses = 450,
        speed = 6.4f,
        attackDamageBonus = 5.9f,
        enchantmentValue = 9
    )

    val QUICKSILVER = tier(
        metal = Metal.QUICKSILVER,
        uses = 714,
        speed = 10f,
        attackDamageBonus = 2.5f,
        enchantmentValue = 24
    )

    val CERUCLASE = tier(
        metal = Metal.CERUCLASE,
        uses = 503,
        speed = 5f,
        attackDamageBonus = 3.5f,
        enchantmentValue = 16
    )

    val EXIMITE = tier(
        metal = Metal.EXIMITE,
        uses = 1089,
        speed = 7.8f,
        attackDamageBonus = 4.9f,
        enchantmentValue = 28
    )

    val KALENDRITE = tier(
        metal = Metal.KALENDRITE,
        uses = 512,
        speed = 8.1f,
        attackDamageBonus = 4f,
        enchantmentValue = 19
    )

    val MIDASIUM = tier(
        metal = Metal.MIDASIUM,
        uses = 439,
        speed = 7f,
        attackDamageBonus = 2.6f,
        enchantmentValue = 28
    )

    val ORICHALCUM = tier(
        metal = Metal.ORICHALCUM,
        uses = 1850,
        speed = 7.7f,
        attackDamageBonus = 6.2f,
        enchantmentValue = 20
    )

    val PLATINUM = tier(
        metal = Metal.PLATINUM,
        uses = 1398,
        speed = 9f,
        attackDamageBonus = 3.2f,
        enchantmentValue = 30
    )

    val VULCANITE = tier(
        metal = Metal.VULCANITE,
        uses = 1212,
        speed = 8.8f,
        attackDamageBonus = 3.8f,
        enchantmentValue = 19
    )

    val CELENEGIL = tier(
        metal = Metal.CELENEGIL,
        uses = 2255,
        speed = 8.2f,
        attackDamageBonus = 4.8f,
        enchantmentValue = 23
    )

    val AMORDRINE = tier(
        metal = Metal.AMORDRINE,
        uses = 1232,
        speed = 11f,
        attackDamageBonus = 4f,
        enchantmentValue = 40
    )

    val CARMOT = tier(
        metal = Metal.CARMOT,
        uses = 914,
        speed = 4.9f,
        attackDamageBonus = 1.4f,
        enchantmentValue = 22
    )

    val MITHRIL = tier(
        metal = Metal.MITHRIL,
        uses = 1651,
        speed = 9f,
        attackDamageBonus = 5.5f,
        enchantmentValue = 18
    )

    val SANGUINITE = tier(
        metal = Metal.SANGUINITE,
        uses = 1166,
        speed = 11f,
        attackDamageBonus = 8f,
        enchantmentValue = 25
    )

    val VYROXERES = tier(
        metal = Metal.VYROXERES,
        uses = 1087,
        speed = 11f,
        attackDamageBonus = 6.2f,
        enchantmentValue = 16
    )

    val SHADOW_STEEL = tier(
        metal = Metal.SHADOW_STEEL,
        uses = 1566,
        speed = 9f,
        attackDamageBonus = 6.8f,
        enchantmentValue = 5
    )

    val HADEROTH = tier(
        metal = Metal.HADEROTH,
        // TODO if the improves-after-breaking effect is added, revert this to 80
        uses = 2580,
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
        uses = 2250,
        speed = 11f,
        attackDamageBonus = 5f,
        enchantmentValue = 22
    )

    val ADAMANTINE = tier(
        metal = Metal.ADAMANTINE,
        uses = 3443,
        speed = 10.5f,
        attackDamageBonus = 5f,
        enchantmentValue = 22
    )

    val INOLASHITE = tier(
        metal = Metal.INOLASHITE,
        uses = 1528,
        speed = 7.2f,
        attackDamageBonus = 5f,
        enchantmentValue = 22
    )

    val KRIK = tier(
        metal = Metal.KRIK,
        uses = 2152,
        speed = 9.5f,
        attackDamageBonus = 4.1f,
        enchantmentValue = 17
    )

    val TARTARITE = tier(
        metal = Metal.TARTARITE,
        uses = 4782,
        speed = 12f,
        attackDamageBonus = 9f,
        enchantmentValue = 14
    )

    val ETHERIUM = tier(
        metal = Metal.ETHERIUM,
        uses = 2556,
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