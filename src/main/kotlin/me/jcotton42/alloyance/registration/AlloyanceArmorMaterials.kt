package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.crafting.Ingredient
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister

object AlloyanceArmorMaterials {
    val MATERIALS: DeferredRegister<ArmorMaterial?> = DeferredRegister.create(Registries.ARMOR_MATERIAL, Alloyance.ID)

    val DEEP_IRON_MATERIAL = MATERIALS.register(Metal.DEEP_IRON.id) { ->
        ArmorMaterial(
            mapOf(
                ArmorItem.Type.BOOTS to 1,
                ArmorItem.Type.LEGGINGS to 3,
                ArmorItem.Type.CHESTPLATE to 4,
                ArmorItem.Type.HELMET to 2,
            ),
            20,
            SoundEvents.ARMOR_EQUIP_IRON,
            { Ingredient.of(AlloyanceItemTags.INGOTS_DEEP_IRON) },
            listOf(ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Alloyance.ID, Metal.DEEP_IRON.id))),
            1f,
            0f
        )
    }

    fun register(bus: IEventBus) {
        MATERIALS.register(bus)
    }
}