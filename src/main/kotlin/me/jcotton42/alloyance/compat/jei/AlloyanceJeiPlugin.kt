package me.jcotton42.alloyance.compat.jei

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.machine.crusher.CrusherScreen
import me.jcotton42.alloyance.registration.AlloyanceItems
import me.jcotton42.alloyance.registration.AlloyanceRecipes
import mezz.jei.api.IModPlugin
import mezz.jei.api.JeiPlugin
import mezz.jei.api.registration.IGuiHandlerRegistration
import mezz.jei.api.registration.IRecipeCatalystRegistration
import mezz.jei.api.registration.IRecipeCategoryRegistration
import mezz.jei.api.registration.IRecipeRegistration
import net.minecraft.client.Minecraft
import net.minecraft.resources.ResourceLocation

@JeiPlugin
class AlloyanceJeiPlugin: IModPlugin {
    override fun getPluginUid(): ResourceLocation = ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "jei_plugin")

    override fun registerCategories(registration: IRecipeCategoryRegistration) {
        val guiHelper = registration.jeiHelpers.guiHelper
        registration.addRecipeCategories(CrusherRecipeCategory(guiHelper))
    }

    override fun registerRecipes(registration: IRecipeRegistration) {
        val recipeManager = Minecraft.getInstance().level!!.recipeManager

        val crusherRecipes = recipeManager.getAllRecipesFor(AlloyanceRecipes.CRUSHER_TYPE.get())
        registration.addRecipes(CrusherRecipeCategory.RECIPE_TYPE, crusherRecipes.map { it.value })
    }

    override fun registerGuiHandlers(registration: IGuiHandlerRegistration) {
        registration.addRecipeClickArea(CrusherScreen::class.java, 59, 45, 21, 18, CrusherRecipeCategory.RECIPE_TYPE)
    }

    override fun registerRecipeCatalysts(registration: IRecipeCatalystRegistration) {
        registration.addRecipeCatalyst(AlloyanceItems.CRUSHER, CrusherRecipeCategory.RECIPE_TYPE)
    }
}