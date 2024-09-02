package me.jcotton42.alloyance.machine.crusher

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.extensions.copyInto
import me.jcotton42.alloyance.extensions.getAllStacks
import me.jcotton42.alloyance.machine.ExtractOnlyItemHandler
import me.jcotton42.alloyance.registration.AlloyanceBlocks
import me.jcotton42.alloyance.registration.AlloyanceDataMaps
import me.jcotton42.alloyance.registration.AlloyanceRecipes
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
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
import net.minecraft.world.MenuProvider
import net.minecraft.world.Nameable
import net.minecraft.world.entity.ExperienceOrb
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerData
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.component.ItemContainerContents
import net.minecraft.world.item.crafting.RecipeHolder
import net.minecraft.world.item.crafting.RecipeManager
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.item.crafting.SingleRecipeInput
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.Vec3
import net.neoforged.neoforge.items.IItemHandler
import net.neoforged.neoforge.items.ItemStackHandler
import net.neoforged.neoforge.items.wrapper.CombinedInvWrapper
import net.neoforged.neoforge.items.wrapper.RangedWrapper
import kotlin.math.max

class CrusherBlockEntity(
    pos: BlockPos,
    state: BlockState
): BlockEntity(
    AlloyanceBlocks.CRUSHER_BLOCK_ENTITY.get(),
    pos,
    state
), MenuProvider, Nameable {
    companion object {
        // fuel burn times are scaled to this
        const val NOMINAL_CRUSHING_TIME = 140
        const val SLOT_COUNT = 5
        const val INPUT_SLOT = 0
        const val FUEL_SLOT = 1
        const val OUTPUT_SLOT_1 = 2
        const val OUTPUT_SLOT_2 = 3
        const val OUTPUT_SLOT_3 = 4

        const val CRUSHING_TIME_INDEX = 0
        const val TOTAL_CRUSHING_TIME_INDEX = 1
        const val BURN_TIME_REMAINING_INDEX = 2
        const val TOTAL_BURN_TIME_INDEX = 3
        const val DATA_SLOT_COUNT = 4
    }

    private var name: Component? = null
    private var lockKey: LockCode = LockCode.NO_LOCK
    private var crushingTime: Int = 0
    private var totalCrushingTime: Int = 0
    private var burnTimeRemaining: Int = 0
    private var totalBurnTime: Int = 0
    private var crushProgressPerTick: Int = 0
    private val recipesUsed: Object2IntOpenHashMap<ResourceLocation> = Object2IntOpenHashMap()

    val inventory = object: ItemStackHandler(5) {
        override fun onContentsChanged(slot: Int) {
            // TODO maybe add a flag here to suppress updates when doing serverTick stuff, to reduce packets
            setChanged()
        }

        override fun isItemValid(slot: Int, stack: ItemStack): Boolean = when (slot) {
            FUEL_SLOT -> stack.getBurnTime(RecipeType.SMELTING) > 0 || stack.`is`(Items.BUCKET)
            else -> true
        }
    }

    private val input = RangedWrapper(inventory, INPUT_SLOT, FUEL_SLOT)
    private val fuel = RangedWrapper(inventory, FUEL_SLOT, OUTPUT_SLOT_1)
    private val outputs = ExtractOnlyItemHandler(RangedWrapper(inventory, OUTPUT_SLOT_1, OUTPUT_SLOT_3 + 1))
    private val all = CombinedInvWrapper(input, fuel, outputs)

    private val containerData = object: ContainerData {
        override fun get(index: Int): Int = when (index) {
            CRUSHING_TIME_INDEX -> crushingTime
            TOTAL_CRUSHING_TIME_INDEX -> totalCrushingTime
            BURN_TIME_REMAINING_INDEX -> burnTimeRemaining
            TOTAL_BURN_TIME_INDEX -> totalBurnTime
            else -> TODO("Missing data index $index")
        }

        override fun set(index: Int, value: Int) = when (index) {
            CRUSHING_TIME_INDEX -> crushingTime = value
            TOTAL_CRUSHING_TIME_INDEX -> totalCrushingTime = value
            BURN_TIME_REMAINING_INDEX -> burnTimeRemaining = value
            TOTAL_BURN_TIME_INDEX -> totalBurnTime = value
            else -> TODO("Missing data index $index")
        }

        override fun getCount(): Int = DATA_SLOT_COUNT
    }

    private val quickCheck = RecipeManager.createCheck(AlloyanceRecipes.CRUSHER_TYPE.get())

    fun tickSerer(level: Level, pos: BlockPos, state: BlockState) {
        val inputStack = inventory.getStackInSlot(INPUT_SLOT)
        val fuelStack = inventory.getStackInSlot(FUEL_SLOT)

        val wasBurning = isBurning()
        burnTimeRemaining = max(burnTimeRemaining - 1, 0)
        if (inputStack.isEmpty) {
            crushingTime = 0
            updateLitState(wasBurning, isBurning(), level, pos, state)
            return
        }

        val canCrush = tryCrush(inputStack, level, simulate = true)
        if (!wasBurning && !fuelStack.isEmpty && canCrush) {
            crushProgressPerTick = fuelStack.itemHolder.getData(AlloyanceDataMaps.FUEL_SPEED)?.speed ?: 1
            totalBurnTime = fuelStack.getBurnTime(RecipeType.SMELTING) * NOMINAL_CRUSHING_TIME / 200
            burnTimeRemaining = totalBurnTime
            if (fuelStack.hasCraftingRemainingItem()) {
                inventory.setStackInSlot(FUEL_SLOT, fuelStack.craftingRemainingItem)
            } else {
                fuelStack.shrink(1)
            }
        }

        if (canCrush) {
            if (isBurning()) {
                crushingTime += crushProgressPerTick
            }
            if (crushingTime >= totalCrushingTime) {
                tryCrush(inputStack, level, simulate = false)
                crushingTime = 0
            }
        } else {
            crushingTime = 0
        }

        updateLitState(wasBurning, isBurning(), level, pos, state)
    }

    private fun isBurning(): Boolean = burnTimeRemaining > 0

    private fun tryCrush(inputStack: ItemStack, level: Level, simulate: Boolean): Boolean {
        val input = SingleRecipeInput(inputStack)
        val recipeHolder = quickCheck.getRecipeFor(input, level).orElse(null)
            ?: return false
        val recipe = recipeHolder.value
        totalCrushingTime = recipe.crushingTime
        var result = recipe.assemble(input, level.registryAccess())
        var actualResult = result.copy()
        for (slot in OUTPUT_SLOT_1..OUTPUT_SLOT_3) {
            result = inventory.insertItem(slot, result, true)
            if (result.isEmpty) break
        }

        if (simulate) return result.isEmpty
        if (!result.isEmpty) return false

        for (slot in OUTPUT_SLOT_1..OUTPUT_SLOT_3) {
            actualResult = inventory.insertItem(slot, actualResult, false)
            if (actualResult.isEmpty) break
        }

        inputStack.shrink(1)
        recipesUsed.addTo(recipeHolder.id, 1)
        return true
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
                createExperience(level, popLocation, (recipe.value as CrusherRecipe).experience * timesUed)
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

    private fun updateLitState(wasBurning: Boolean, isBurning: Boolean, level: Level, pos: BlockPos, state: BlockState) {
        if (isBurning != wasBurning) {
            val newState = state.setValue(CrusherBlock.LIT, isBurning)
            level.setBlockAndUpdate(pos, newState)
        }
    }

    fun getItemHandler(side: Direction?): IItemHandler = when (side) {
        Direction.UP -> input
        Direction.DOWN -> outputs
        null -> all
        else -> fuel
    }

    override fun saveAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        super.saveAdditional(tag, registries)
        if (name != null) {
            tag.putString("custom_name", Component.Serializer.toJson(name, registries))
        }

        lockKey.addToTag(tag)

        tag.putInt("crushing_time", crushingTime)
        tag.putInt("total_crushing_time", totalCrushingTime)
        tag.putInt("burn_time_remaining", burnTimeRemaining)
        tag.putInt("total_burn_time", totalBurnTime)
        tag.putInt("crush_progress_per_tick", crushProgressPerTick)
        tag.put("inventory", inventory.serializeNBT(registries))

        val recipesUsedTag = CompoundTag()
        recipesUsed.forEach { (recipeId, timesUsed) ->
            recipesUsedTag.putInt(recipeId.toString(), timesUsed)
        }
        tag.put("recipes_used", recipesUsedTag)
    }

    override fun loadAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        super.loadAdditional(tag, registries)
        lockKey = LockCode.fromTag(tag)
        if (tag.contains("custom_name", 8)) {
            name = parseCustomNameSafe(tag.getString("custom_name"), registries)
        }

        crushingTime = tag.getInt("crushing_time")
        totalCrushingTime = tag.getInt("total_crushing_time")
        burnTimeRemaining = tag.getInt("burn_time_remaining")
        totalBurnTime = tag.getInt("total_burn_time")
        crushProgressPerTick = tag.getInt("crush_progress_per_tick")
        inventory.deserializeNBT(registries, tag.getCompound("inventory"))

        val recipesUsedTag = tag.getCompound("recipes_used")
        recipesUsedTag.allKeys.forEach { recipeId ->
            recipesUsed.put(ResourceLocation.parse(recipeId), recipesUsedTag.getInt(recipeId))
        }
    }

    override fun collectImplicitComponents(components: DataComponentMap.Builder) {
        super.collectImplicitComponents(components)
        components.set(DataComponents.CUSTOM_NAME, name)
        if (lockKey != LockCode.NO_LOCK) {
            components.set(DataComponents.LOCK, lockKey)
        }

        components.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(inventory.getAllStacks()))
    }

    override fun applyImplicitComponents(componentInput: DataComponentInput) {
        super.applyImplicitComponents(componentInput)
        name = componentInput.get(DataComponents.CUSTOM_NAME)
        lockKey = componentInput.getOrDefault(DataComponents.LOCK, LockCode.NO_LOCK)
        componentInput.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyInto(inventory)
    }

    @Suppress("OVERRIDE_DEPRECATION")
    override fun removeComponentsFromTag(tag: CompoundTag) {
        tag.remove("inventory")
        tag.remove("custom_name")
        tag.remove("lock")
    }

    fun canUnlock(player: Player, code: LockCode, displayName: Component): Boolean {
        if (player.isSpectator || code.unlocksWith(player.mainHandItem)) {
            return true
        }

        player.displayClientMessage(Component.translatable("container.isLocked", displayName), true)
        player.playNotifySound(SoundEvents.CHEST_LOCKED, SoundSource.BLOCKS, 1f, 1f)
        return false
    }

    override fun createMenu(containerId: Int, playerInventory: Inventory, player: Player): AbstractContainerMenu? {
        return if(canUnlock(player, lockKey, displayName)) {
            CrusherMenu(containerId, playerInventory, all, containerData, ::awardUsedRecipesAndPopExperience, blockPos)
        } else {
            null
        }
    }

    override fun getName(): Component {
        return name ?: Component.translatable(AlloyanceBlocks.CRUSHER.id.toLanguageKey("block"))
    }

    override fun getDisplayName(): Component = getName()

    override fun getCustomName(): Component? = name
}