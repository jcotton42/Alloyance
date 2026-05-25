package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.crafting.Ingredient
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.common.Tags
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister

object AlloyanceArmorMaterials {
    val REGISTRY: DeferredRegister<ArmorMaterial?> = DeferredRegister.create(Registries.ARMOR_MATERIAL, Alloyance.ID)
    val MATERIALS = mutableMapOf<Metal, DeferredHolder<ArmorMaterial?, ArmorMaterial>>()

    val COPPER = REGISTRY.register("copper") { ->
        ArmorMaterial(
            mapOf(
                ArmorItem.Type.BOOTS to 1,
                ArmorItem.Type.LEGGINGS to 2,
                ArmorItem.Type.CHESTPLATE to 3,
                ArmorItem.Type.HELMET to 2,
            ),
            25,
            SoundEvents.ARMOR_EQUIP_IRON,
            { Ingredient.of(Tags.Items.INGOTS_COPPER) },
            listOf(ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "copper"))),
            0f,
            0f
        )
    }

    val DEEP_IRON_MATERIAL = material(
        Metal.DEEP_IRON,
        bootDefense = 1,
        leggingDefense = 3,
        chestplateDefense = 4,
        helmetDefense = 2,
        enchantability = 20,
        toughness = 1f,
        knockbackResistance = 0f
    )

    val PROMETHEUM_MATERIAL = material(
        Metal.PROMETHEUM,
        bootDefense = 1,
        leggingDefense = 2,
        chestplateDefense = 2,
        helmetDefense = 2,
        enchantability = 14,
        toughness = 0.5f,
        knockbackResistance = 0f
    )

    val BRONZE_MATERIAL = material(
        Metal.BRONZE,
        bootDefense = 2,
        leggingDefense = 3,
        chestplateDefense = 4,
        helmetDefense = 3,
        enchantability = 9,
        toughness = 1f,
        knockbackResistance = 0f
    )

    val BRASS_MATERIAL = material(
        Metal.BRASS,
        bootDefense = 2,
        leggingDefense = 3,
        chestplateDefense = 3,
        helmetDefense = 2,
        enchantability = 18,
        toughness = 3f,
        knockbackResistance = 0.2f
    )

    val DAMASCUS_STEEL_MATERIAL = material(
        Metal.DAMASCUS_STEEL,
        bootDefense = 3,
        leggingDefense = 5,
        chestplateDefense = 6,
        helmetDefense = 3,
        enchantability = 27,
        toughness = 0f,
        knockbackResistance = 0f
    )

    val OSMIUM_MATERIAL = material(
        Metal.OSMIUM,
        bootDefense = 5,
        leggingDefense = 6,
        chestplateDefense = 6,
        helmetDefense = 5,
        enchantability = 8,
        toughness = 3.5f,
        knockbackResistance = 1f
    )

    val SILVER_MATERIAL = material(
        Metal.SILVER,
        bootDefense = 2,
        leggingDefense = 3,
        chestplateDefense = 4,
        helmetDefense = 2,
        enchantability = 20,
        toughness = 2.1f,
        knockbackResistance = 0f
    )

    val ANGMALLEN_MATERIAL = material(
        Metal.ANGMALLEN,
        bootDefense = 2,
        leggingDefense = 3,
        chestplateDefense = 4,
        helmetDefense = 2,
        enchantability = 32,
        toughness = 0.5f,
        knockbackResistance = 0f
    )

    val STEEL_MATERIAL = material(
        Metal.STEEL,
        bootDefense = 3,
        leggingDefense = 5,
        chestplateDefense = 6,
        helmetDefense = 3,
        enchantability = 14,
        toughness = 3f,
        knockbackResistance = 0f
    )

    val HEPATIZON_MATERIAL = material(
        Metal.HEPATIZON,
        bootDefense = 3,
        leggingDefense = 4,
        chestplateDefense = 4,
        helmetDefense = 3,
        enchantability = 29,
        toughness = 1f,
        knockbackResistance = 0f
    )

    val BLACK_STEEL_MATERIAL = material(
        Metal.BLACK_STEEL,
        bootDefense = 2,
        leggingDefense = 3,
        chestplateDefense = 3,
        helmetDefense = 2,
        enchantability = 2,
        toughness = 6.5f,
        knockbackResistance = 0f
    )

    val ELECTRUM_MATERIAL = material(
        Metal.ELECTRUM,
        bootDefense = 2,
        leggingDefense = 4,
        chestplateDefense = 5,
        helmetDefense = 3,
        enchantability = 19,
        toughness = 1f,
        knockbackResistance = 0f
    )

    val ASTRAL_SILVER_MATERIAL = material(
        Metal.ASTRAL_SILVER,
        bootDefense = 2,
        leggingDefense = 3,
        chestplateDefense = 4,
        helmetDefense = 3,
        enchantability = 36,
        toughness = 0f,
        knockbackResistance = 0f
    )

    val IGNATIUS_MATERIAL = material(
        Metal.IGNATIUS,
        bootDefense = 2,
        leggingDefense = 3,
        chestplateDefense = 4,
        helmetDefense = 2,
        enchantability = 11,
        toughness = 0f,
        knockbackResistance = 0f
    )

    val OURECLASE_MATERIAL = material(
        Metal.OURECLASE,
        bootDefense = 4,
        leggingDefense = 6,
        chestplateDefense = 5,
        helmetDefense = 3,
        enchantability = 26,
        toughness = 2.5f,
        knockbackResistance = 0.88f
    )

    val SHADOW_IRON_MATERIAL = material(
        Metal.SHADOW_IRON,
        bootDefense = 4,
        leggingDefense = 5,
        chestplateDefense = 6,
        helmetDefense = 4,
        enchantability = 9,
        toughness = 0f,
        knockbackResistance = -0.5f
    )

    val QUICKSILVER_MATERIAL = material(
        Metal.QUICKSILVER,
        bootDefense = 5,
        leggingDefense = 5,
        chestplateDefense = 4,
        helmetDefense = 4,
        enchantability = 24,
        toughness = 0f,
        knockbackResistance = 0f
    )

    val CERUCLASE_MATERIAL = material(
        Metal.CERUCLASE,
        bootDefense = 3,
        leggingDefense = 4,
        chestplateDefense = 5,
        helmetDefense = 4,
        enchantability = 50,
        toughness = 0f,
        knockbackResistance = 0f
    )

    val EXIMITE_MATERIAL = material(
        Metal.EXIMITE,
        bootDefense = 3,
        leggingDefense = 4,
        chestplateDefense = 4,
        helmetDefense = 3,
        enchantability = 28,
        toughness = 3f,
        knockbackResistance = 0f
    )

    val KALENDRITE_MATERIAL = material(
        Metal.KALENDRITE,
        bootDefense = 1,
        leggingDefense = 2,
        chestplateDefense = 2,
        helmetDefense = 1,
        enchantability = 16,
        toughness = 3f,
        knockbackResistance = 0f
    )

    val MIDASIUM_MATERIAL = material(
        Metal.MIDASIUM,
        bootDefense = 4,
        leggingDefense = 4,
        chestplateDefense = 5,
        helmetDefense = 4,
        enchantability = 28,
        toughness = 2f,
        knockbackResistance = 0f
    )

    val ORICHALCUM_MATERIAL = material(
        Metal.ORICHALCUM,
        bootDefense = 2,
        leggingDefense = 6,
        chestplateDefense = 7,
        helmetDefense = 4,
        enchantability = 14,
        toughness = 1.5f,
        knockbackResistance = 0f
    )

    val PLATINUM_MATERIAL = material(
        Metal.PLATINUM,
        bootDefense = 3,
        leggingDefense = 4,
        chestplateDefense = 4,
        helmetDefense = 3,
        enchantability = 30,
        toughness = 0f,
        knockbackResistance = 0f
    )

    val VULCANITE_MATERIAL = material(
        Metal.VULCANITE,
        bootDefense = 3,
        leggingDefense = 6,
        chestplateDefense = 6,
        helmetDefense = 3,
        enchantability = 19,
        toughness = 4f,
        knockbackResistance = 0f
    )

    val CELENEGIL_MATERIAL = material(
        Metal.CELENEGIL,
        bootDefense = 3,
        leggingDefense = 5,
        chestplateDefense = 6,
        helmetDefense = 4,
        enchantability = 23,
        toughness = 4f,
        knockbackResistance = 0f
    )

    val AMORDRINE_MATERIAL = material(
        Metal.AMORDRINE,
        bootDefense = 3,
        leggingDefense = 4,
        chestplateDefense = 5,
        helmetDefense = 3,
        enchantability = 40,
        toughness = 2f,
        knockbackResistance = 0f
    )

    val CARMOT_MATERIAL = material(
        Metal.CARMOT,
        bootDefense = 3,
        leggingDefense = 4,
        chestplateDefense = 5,
        helmetDefense = 3,
        enchantability = 7,
        toughness = 1f,
        knockbackResistance = 0f
    )

    val MITHRIL_MATERIAL = material(
        Metal.MITHRIL,
        bootDefense = 2,
        leggingDefense = 4,
        chestplateDefense = 5,
        helmetDefense = 3,
        enchantability = 20,
        toughness = 2f,
        knockbackResistance = 0f
    )

    val SANGUINITE_MATERIAL = material(
        Metal.SANGUINITE,
        bootDefense = 4,
        leggingDefense = 6,
        chestplateDefense = 7,
        helmetDefense = 5,
        enchantability = 25,
        toughness = 4f,
        knockbackResistance = 0f
    )

    val VYROXERES_MATERIAL = material(
        Metal.VYROXERES,
        bootDefense = 4,
        leggingDefense = 5,
        chestplateDefense = 6,
        helmetDefense = 3,
        enchantability = 16,
        toughness = 3f,
        knockbackResistance = 0f
    )

    val SHADOW_STEEL_MATERIAL = material(
        Metal.SHADOW_STEEL,
        bootDefense = 4,
        leggingDefense = 5,
        chestplateDefense = 6,
        helmetDefense = 4,
        enchantability = 3,
        toughness = 3f,
        knockbackResistance = 0f
    )

    val HADEROTH_MATERIAL = material(
        Metal.HADEROTH,
        bootDefense = 4,
        leggingDefense = 5,
        chestplateDefense = 6,
        helmetDefense = 4,
        enchantability = 19,
        toughness = 6f,
        knockbackResistance = 0f
    )

    val DESICHALKOS_MATERIAL = material(
        Metal.DESICHALKOS,
        bootDefense = 3,
        leggingDefense = 4,
        chestplateDefense = 5,
        helmetDefense = 4,
        enchantability = 21,
        toughness = 2.5f,
        knockbackResistance = 0f
    )

    val ATLARUS_MATERIAL = material(
        Metal.ATLARUS,
        bootDefense = 4,
        leggingDefense = 4,
        chestplateDefense = 4,
        helmetDefense = 4,
        enchantability = 2,
        toughness = 3.3f,
        knockbackResistance = -0.4f
    )

    val ADAMANTINE_MATERIAL = material(
        Metal.ADAMANTINE,
        bootDefense = 3,
        leggingDefense = 4,
        chestplateDefense = 5,
        helmetDefense = 3,
        enchantability = 8,
        toughness = 3.5f,
        knockbackResistance = 0f
    )

    val LUTETIUM_MATERIAL = material(
        Metal.LUTETIUM,
        bootDefense = 5,
        leggingDefense = 6,
        chestplateDefense = 5,
        helmetDefense = 4,
        enchantability = 17,
        toughness = 3f,
        knockbackResistance = 0f
    )

    val INOLASHITE_MATERIAL = material(
        Metal.INOLASHITE,
        bootDefense = 3,
        leggingDefense = 6,
        chestplateDefense = 7,
        helmetDefense = 5,
        enchantability = 22,
        toughness = 1f,
        knockbackResistance = 0f
    )

    val KRIK_MATERIAL = material(
        Metal.KRIK,
        bootDefense = 2,
        leggingDefense = 3,
        chestplateDefense = 4,
        helmetDefense = 3,
        enchantability = 17,
        toughness = 2f,
        knockbackResistance = 0f
    )

    val TARTARITE_MATERIAL = material(
        Metal.TARTARITE,
        bootDefense = 5,
        leggingDefense = 6,
        chestplateDefense = 7,
        helmetDefense = 5,
        enchantability = 14,
        toughness = 6f,
        knockbackResistance = 0f
    )

    val ETHERIUM_MATERIAL = material(
        Metal.ETHERIUM,
        bootDefense = 3,
        leggingDefense = 5,
        chestplateDefense = 6,
        helmetDefense = 4,
        enchantability = 30,
        toughness = 0f,
        knockbackResistance = 0f
    )

    private fun material(
        metal: Metal,
        bootDefense: Int,
        leggingDefense: Int,
        chestplateDefense: Int,
        helmetDefense: Int,
        enchantability: Int,
        toughness: Float,
        knockbackResistance: Float): DeferredHolder<ArmorMaterial?, ArmorMaterial?> {
        val material = REGISTRY.register(metal.id) { ->
            ArmorMaterial(
                mapOf(
                    ArmorItem.Type.BOOTS to bootDefense,
                    ArmorItem.Type.LEGGINGS to leggingDefense,
                    ArmorItem.Type.CHESTPLATE to chestplateDefense,
                    ArmorItem.Type.HELMET to helmetDefense,
                ),
                enchantability,
                SoundEvents.ARMOR_EQUIP_IRON,
                { Ingredient.of(AlloyanceItemTags.INGOTS.getValue(metal)) },
                listOf(ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Alloyance.ID, metal.id))),
                toughness,
                knockbackResistance
            )
        }
        MATERIALS[metal] = material
        return material
    }

    fun register(bus: IEventBus) {
        REGISTRY.register(bus)
    }
}