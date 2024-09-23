package me.jcotton42.alloyance.machine.alloyer

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import me.jcotton42.alloyance.common.HasExperience
import me.jcotton42.alloyance.machine.DualRecipeInput
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
import net.minecraft.world.level.Level
import net.neoforged.neoforge.common.crafting.SizedIngredient

class AlloyerRecipe(
    private val group: String,
    val ingredient1: SizedIngredient,
    val ingredient2: SizedIngredient,
    val result: ItemStack,
    override val experience: Float,
    val alloyingTime: Int,
): Recipe<DualRecipeInput>, HasExperience {
    private val ingredients: NonNullList<Ingredient> = NonNullList.create()

    init {
        ingredients.add(ingredient1.ingredient())
        ingredients.add(ingredient2.ingredient())
    }

    override fun getIngredients(): NonNullList<Ingredient> = ingredients

    override fun matches(input: DualRecipeInput, level: Level): Boolean {
        return (ingredient1.test(input.item1) && ingredient2.test(input.item2))
                || (ingredient1.test(input.item2) && ingredient2.test(input.item1))
    }

    override fun assemble(input: DualRecipeInput, registries: HolderLookup.Provider): ItemStack = result.copy()

    override fun canCraftInDimensions(width: Int, height: Int): Boolean = width * height > 0

    override fun getResultItem(registries: HolderLookup.Provider): ItemStack = result

    override fun getToastSymbol(): ItemStack {
        TODO("Alloyer icon")
    }

    override fun getGroup(): String = group

    override fun getSerializer(): RecipeSerializer<*> = AlloyanceRecipes.ALLOYER_SERIALIZER.get()

    override fun getType(): RecipeType<*> = AlloyanceRecipes.ALLOYER_TYPE.get()

    class Serializer: RecipeSerializer<AlloyerRecipe> {
        companion object {
            val CODEC: MapCodec<AlloyerRecipe> = RecordCodecBuilder.mapCodec { instance ->
                instance.group(
                    Codec.STRING.optionalFieldOf("group", "").forGetter(AlloyerRecipe::group),
                    SizedIngredient.FLAT_CODEC.fieldOf("ingredient1").forGetter(AlloyerRecipe::ingredient1),
                    SizedIngredient.FLAT_CODEC.fieldOf("ingredient2").forGetter(AlloyerRecipe::ingredient2),
                    ItemStack.CODEC.fieldOf("result").forGetter(AlloyerRecipe::result),
                    Codec.FLOAT.optionalFieldOf("experience", 0F).forGetter(AlloyerRecipe::experience),
                    Codec.INT.optionalFieldOf("alloying_time", AlloyerBlockEntity.NOMINAL_ALLOYING_TIME).forGetter(AlloyerRecipe::alloyingTime)
                ).apply(instance, ::AlloyerRecipe)
            }
            val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, AlloyerRecipe> = StreamCodec.composite(
                ByteBufCodecs.STRING_UTF8, AlloyerRecipe::group,
                SizedIngredient.STREAM_CODEC, AlloyerRecipe::ingredient1,
                SizedIngredient.STREAM_CODEC, AlloyerRecipe::ingredient2,
                ItemStack.STREAM_CODEC, AlloyerRecipe::result,
                ByteBufCodecs.FLOAT, AlloyerRecipe::experience,
                ByteBufCodecs.INT, AlloyerRecipe::alloyingTime,
                ::AlloyerRecipe
            )
        }

        override fun codec(): MapCodec<AlloyerRecipe> = CODEC

        override fun streamCodec(): StreamCodec<RegistryFriendlyByteBuf, AlloyerRecipe> = STREAM_CODEC
    }
}
