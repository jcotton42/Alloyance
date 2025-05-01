package me.jcotton42.alloyance.compat.jei

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.machine.alloyer.AlloyerRecipe
import me.jcotton42.alloyance.machine.alloyer.AlloyerScreen
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

private val UID = ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "alloyer")

class AlloyerRecipeCategory(guiHelper: IGuiHelper): IRecipeCategory<AlloyerRecipe> {
    private val background: IDrawable = guiHelper.createDrawable(AlloyerScreen.BACKGROUND, 0, 0, 176, 108)
    private val icon: IDrawable = guiHelper.createDrawableItemLike(AlloyanceItems.ALLOYER)
    private val burning: IDrawableAnimated = guiHelper.drawableBuilder(
        ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "textures/gui/sprites/alloyer/burning.png"), 0, 0, 17, 17)
        .setTextureSize(17, 17)
        .buildAnimated(300, IDrawableAnimated.StartDirection.TOP, true)
    private val meterBuilder: IDrawableBuilder = guiHelper.drawableBuilder(
        ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "textures/gui/sprites/alloyer/meter.png"), 0, 0, 7, 33)
        .setTextureSize(7, 33)
    private val spoutBuilder: IDrawableBuilder = guiHelper.drawableBuilder(
        ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "textures/gui/sprites/alloyer/spout.png"), 0, 0, 10, 25)
        .setTextureSize(10, 25)

    override fun getRecipeType(): RecipeType<AlloyerRecipe> = RECIPE_TYPE

    override fun getTitle(): Component = Component.translatable(AlloyanceBlocks.ALLOYER.id.toLanguageKey("block"))

    override fun getIcon(): IDrawable = icon

    @Suppress("removal")
    override fun getBackground(): IDrawable = background

    override fun setRecipe(builder: IRecipeLayoutBuilder, recipe: AlloyerRecipe, focuses: IFocusGroup) {
        builder.addInputSlot(102, 23).addItemStacks(recipe.ingredient1.items.asList())
        builder.addInputSlot(123, 23).addItemStacks(recipe.ingredient2.items.asList())
        builder.addOutputSlot(57, 73).addItemStack(recipe.result)
    }

    override fun createRecipeExtras(builder: IRecipeExtrasBuilder, recipe: AlloyerRecipe, focuses: IFocusGroup) {
        val meter = meterBuilder.buildAnimated(recipe.alloyingTime, IDrawableAnimated.StartDirection.BOTTOM, false)
        val spout = spoutBuilder.buildAnimated(recipe.alloyingTime, IDrawableAnimated.StartDirection.TOP, false)
        builder.addDrawable(meter, 40, 65)
        builder.addDrawable(spout, 52, 32)
        builder.addDrawable(burning, 110, 61)

        val xp = recipe.experience
        if (xp > 0) {
            builder.addText(Component.literal(String.format("%.2f XP", xp)), width - 20, 10)
                .setPosition(56, 93, 22, 8, HorizontalAlignment.LEFT, VerticalAlignment.TOP)
                .setTextAlignment(HorizontalAlignment.LEFT)
                .setColor(Metal.OSMIUM.color)
        }
    }

    companion object {
        val RECIPE_TYPE = RecipeType(UID, AlloyerRecipe::class.java)
    }
}