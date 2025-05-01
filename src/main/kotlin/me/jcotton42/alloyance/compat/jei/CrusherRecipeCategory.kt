package me.jcotton42.alloyance.compat.jei

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.machine.crusher.CrusherRecipe
import me.jcotton42.alloyance.machine.crusher.CrusherScreen
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import me.jcotton42.alloyance.registration.AlloyanceItems
import me.jcotton42.alloyance.registration.Metal
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder
import mezz.jei.api.gui.drawable.IDrawable
import mezz.jei.api.gui.drawable.IDrawableAnimated
import mezz.jei.api.gui.drawable.IDrawableBuilder
import mezz.jei.api.gui.placement.HorizontalAlignment
import mezz.jei.api.gui.placement.VerticalAlignment
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder
import mezz.jei.api.helpers.IGuiHelper
import mezz.jei.api.recipe.IFocusGroup
import mezz.jei.api.recipe.RecipeType
import mezz.jei.api.recipe.category.IRecipeCategory
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation

private val UID = ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "crusher")

class CrusherRecipeCategory(guiHelper: IGuiHelper): IRecipeCategory<CrusherRecipe> {
    private val background: IDrawable = guiHelper.createDrawable(CrusherScreen.BACKGROUND, 0, 0, 176, 108)
    private val icon: IDrawable = guiHelper.createDrawableItemLike(AlloyanceItems.CRUSHER)
    private val burning: IDrawableAnimated = guiHelper.drawableBuilder(
        ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "textures/gui/sprites/crusher/burning.png"), 0, 0, 17, 17)
        .setTextureSize(17, 17)
        .buildAnimated(300, IDrawableAnimated.StartDirection.TOP, true)
    private val melting: IDrawable = guiHelper.drawableBuilder(
        ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "textures/gui/sprites/crusher/melting.png"), 0, 0, 21, 18)
        .setTextureSize(21, 18)
        .build()
    private val meterBuilder: IDrawableBuilder = guiHelper.drawableBuilder(
        ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "textures/gui/sprites/crusher/meter.png"), 0, 0, 7, 33)
        .setTextureSize(7, 33)

    override fun getRecipeType(): RecipeType<CrusherRecipe> = RECIPE_TYPE

    override fun getTitle(): Component = Component.translatable(AlloyanceBlocks.CRUSHER.id.toLanguageKey("block"))

    override fun getIcon(): IDrawable = icon

    @Suppress("removal")
    override fun getBackground(): IDrawable = background

    override fun setRecipe(builder: IRecipeLayoutBuilder, recipe: CrusherRecipe, focuses: IFocusGroup) {
        builder.addInputSlot(61, 27).addIngredients(recipe.ingredient)
        builder.addOutputSlot(67, 67).addItemStack(recipe.result)
    }

    override fun createRecipeExtras(builder: IRecipeExtrasBuilder, recipe: CrusherRecipe, focuses: IFocusGroup) {
        val meter = meterBuilder.buildAnimated(recipe.crushingTime, IDrawableAnimated.StartDirection.BOTTOM, false)
        builder.addDrawable(meter, 93, 65)
        builder.addDrawable(burning, 128, 61)
        builder.addDrawable(melting, 59, 45)

        val xp = recipe.experience
        if (xp > 0) {
            builder.addText(Component.literal(String.format("%.2f XP", xp)), width - 20, 10)
                .setPosition(28, 87, 57, 14, HorizontalAlignment.LEFT, VerticalAlignment.TOP)
                .setTextAlignment(HorizontalAlignment.LEFT)
                .setColor(Metal.OURECLASE.color)
        }
    }

    companion object {
        val RECIPE_TYPE = RecipeType(UID, CrusherRecipe::class.java)
    }
}