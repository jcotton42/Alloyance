package me.jcotton42.alloyance.machine

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
import me.jcotton42.alloyance.common.HasExperience
import me.jcotton42.alloyance.extensions.copyInto
import me.jcotton42.alloyance.extensions.getAllStacks
import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.core.component.DataComponentMap
import net.minecraft.core.component.DataComponents
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.util.Mth
import net.minecraft.world.LockCode
import net.minecraft.world.Nameable
import net.minecraft.world.entity.ExperienceOrb
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.ItemContainerContents
import net.minecraft.world.item.crafting.RecipeHolder
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.Vec3
import net.neoforged.neoforge.items.ItemStackHandler

abstract class BaseMachineBlockEntity(
    type: BlockEntityType<*>,
    pos: BlockPos,
    state: BlockState
): BlockEntity(type, pos, state), Nameable {
    private var customName: Component? = null
    protected var lockKey: LockCode = LockCode.NO_LOCK
    protected val recipesUsed: Object2IntOpenHashMap<ResourceLocation> = Object2IntOpenHashMap()
    abstract val inventory: ItemStackHandler
    abstract val defaultName: Component

    abstract fun tickServer(level: Level, pos: BlockPos, state: BlockState)

    override fun saveAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        super.saveAdditional(tag, registries)
        if (customName != null) {
            tag.putString("custom_name", Component.Serializer.toJson(customName, registries))
        }

        lockKey.addToTag(tag)

        val recipesUsedTag = CompoundTag()
        recipesUsed.forEach { (recipeId, timesUsed) ->
            recipesUsedTag.putInt(recipeId.toString(), timesUsed)
        }
        tag.put("recipes_used", recipesUsedTag)
        tag.put("inventory", inventory.serializeNBT(registries))
    }

    override fun loadAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        super.loadAdditional(tag, registries)
        lockKey = LockCode.fromTag(tag)
        if (tag.contains("custom_name", 8)) {
            customName = parseCustomNameSafe(tag.getString("custom_name"), registries)
        }

        val recipesUsedTag = tag.getCompound("recipes_used")
        recipesUsedTag.allKeys.forEach { recipeId ->
            recipesUsed.put(ResourceLocation.parse(recipeId), recipesUsedTag.getInt(recipeId))
        }
        inventory.deserializeNBT(registries, tag.getCompound("inventory"))
    }

    override fun collectImplicitComponents(components: DataComponentMap.Builder) {
        super.collectImplicitComponents(components)
        components.set(DataComponents.CUSTOM_NAME, customName)
        if (lockKey != LockCode.NO_LOCK) {
            components.set(DataComponents.LOCK, lockKey)
        }

        components.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(inventory.getAllStacks()))
    }

    override fun applyImplicitComponents(componentInput: DataComponentInput) {
        super.applyImplicitComponents(componentInput)
        customName = componentInput.get(DataComponents.CUSTOM_NAME)
        lockKey = componentInput.getOrDefault(DataComponents.LOCK, LockCode.NO_LOCK)
        componentInput.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyInto(inventory)
    }

    @Suppress("OVERRIDE_DEPRECATION")
    override fun removeComponentsFromTag(tag: CompoundTag) {
        tag.remove("inventory")
        tag.remove("custom_name")
        tag.remove("lock")
    }


    override fun getName(): Component = customName ?: defaultName

    override fun getDisplayName(): Component = getName()

    override fun getCustomName(): Component? = customName

    fun canUnlock(player: Player, code: LockCode, displayName: Component): Boolean {
        if (player.isSpectator || code.unlocksWith(player.mainHandItem)) {
            return true
        }

        player.displayClientMessage(Component.translatable("container.isLocked", displayName), true)
        player.playNotifySound(SoundEvents.CHEST_LOCKED, SoundSource.BLOCKS, 1f, 1f)
        return false
    }

    fun awardUsedRecipesAndPopExperience(player: ServerPlayer) {
        val recipesToAward = getRecipesToAwardAndPopExperience(player.serverLevel(), player.position())
        player.awardRecipes(recipesToAward)
        val items = mutableListOf<ItemStack>()
        for (i in 0..<inventory.slots) {
            items.add(inventory.getStackInSlot(i))
        }

        recipesToAward.forEach { recipeHolder ->
            player.triggerRecipeCrafted(recipeHolder, items)
        }

        recipesUsed.clear()
    }

    fun getRecipesToAwardAndPopExperience(level: ServerLevel, popLocation: Vec3): List<RecipeHolder<*>> {
        val recipes = mutableListOf<RecipeHolder<*>>()

        recipesUsed.forEach { (recipeId, timesUed) ->
            level.recipeManager.byKey(recipeId).ifPresent { recipe ->
                recipes.add(recipe)
                createExperience(level, popLocation, (recipe.value as HasExperience).experience * timesUed)
            }
        }

        return recipes
    }

    private fun createExperience(level: ServerLevel, popLocation: Vec3, experience: Float) {
        var amount = Mth.floor(experience)
        val fraction = Mth.frac(experience)
        if (fraction != 0f && Math.random() < fraction) {
            amount++
        }

        ExperienceOrb.award(level, popLocation, amount)
    }
}
