package me.jcotton42.alloyance.machine.crusher

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import me.jcotton42.alloyance.registration.AlloyanceRecipes
import net.minecraft.core.HolderLookup
import net.minecraft.core.NonNullList
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.item.crafting.SingleRecipeInput
import net.minecraft.world.level.Level

class CrusherRecipe(
    private val group: String,
    val ingredient: Ingredient,
    val result: ItemStack,
    val experience: Float,
    val crushingTime: Int,
): Recipe<SingleRecipeInput> {
    private val ingredients: NonNullList<Ingredient> = NonNullList.create()

    init {
        ingredients.add(ingredient)
    }

    override fun getIngredients(): NonNullList<Ingredient> = ingredients

    override fun matches(input: SingleRecipeInput, level: Level): Boolean = ingredient.test(input.item())

    override fun assemble(input: SingleRecipeInput, registries: HolderLookup.Provider): ItemStack = result.copy()

    override fun canCraftInDimensions(width: Int, height: Int): Boolean = width * height > 0

    override fun getResultItem(registries: HolderLookup.Provider): ItemStack = result

    override fun getToastSymbol(): ItemStack = ItemStack(AlloyanceBlocks.CRUSHER.get())

    override fun getGroup(): String = group

    override fun getSerializer(): RecipeSerializer<*> = AlloyanceRecipes.CRUSHER_SERIALIZER.get()

    override fun getType(): RecipeType<*> = AlloyanceRecipes.CRUSHER_TYPE.get()

    class Serializer: RecipeSerializer<CrusherRecipe> {
        companion object {
            val CODEC: MapCodec<CrusherRecipe> = RecordCodecBuilder.mapCodec { instance ->
                instance.group(
                    Codec.STRING.optionalFieldOf("group", "").forGetter(CrusherRecipe::group),
                    Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(CrusherRecipe::ingredient),
                    ItemStack.CODEC.fieldOf("result").forGetter(CrusherRecipe::result),
                    Codec.FLOAT.optionalFieldOf("experience", 0F).forGetter(CrusherRecipe::experience),
                    Codec.INT.optionalFieldOf("crushingtime", CrusherBlockEntity.TOTAL_CRUSHING_TIME).forGetter(CrusherRecipe::crushingTime)
                ).apply(instance, ::CrusherRecipe)
            }
            val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, CrusherRecipe> = StreamCodec.composite(
                ByteBufCodecs.STRING_UTF8, CrusherRecipe::group,
                Ingredient.CONTENTS_STREAM_CODEC, CrusherRecipe::ingredient,
                ItemStack.STREAM_CODEC, CrusherRecipe::result,
                ByteBufCodecs.FLOAT, CrusherRecipe::experience,
                ByteBufCodecs.INT, CrusherRecipe::crushingTime,
                ::CrusherRecipe
            )
        }

        override fun codec(): MapCodec<CrusherRecipe> = CODEC

        override fun streamCodec(): StreamCodec<RegistryFriendlyByteBuf, CrusherRecipe> = STREAM_CODEC
    }
}