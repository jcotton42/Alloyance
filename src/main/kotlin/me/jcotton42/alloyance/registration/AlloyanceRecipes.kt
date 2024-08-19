package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.machine.crusher.CrusherRecipe
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.RecipeType
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister

object AlloyanceRecipes {
    val RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, Alloyance.ID)
    val RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Alloyance.ID)

    val CRUSHER_TYPE = RECIPE_TYPES.register("crusher") { ->
        RecipeType.simple<CrusherRecipe>(ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "crusher"))
    }
    val CRUSHER_SERIALIZER = RECIPE_SERIALIZERS.register("crusher", CrusherRecipe::Serializer)

    fun register(bus: IEventBus) {
        RECIPE_TYPES.register(bus)
        RECIPE_SERIALIZERS.register(bus)
    }
}