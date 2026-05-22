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

    // MARKER

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