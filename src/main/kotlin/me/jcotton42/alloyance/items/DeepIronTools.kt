package me.jcotton42.alloyance.items

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import io.netty.buffer.ByteBuf
import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.client.TooltipStyle
import me.jcotton42.alloyance.registration.AlloyanceArmorMaterials
import me.jcotton42.alloyance.registration.AlloyanceDataComponents
import me.jcotton42.alloyance.registration.Metal
import me.jcotton42.alloyance.registration.MetalTiers
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.EquipmentSlotGroup
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.AxeItem
import net.minecraft.world.item.HoeItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.PickaxeItem
import net.minecraft.world.item.ShovelItem
import net.minecraft.world.item.component.ItemAttributeModifiers
import net.minecraft.world.level.Level
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.common.NeoForgeMod
import net.neoforged.neoforge.event.tick.EntityTickEvent

class DeepIronPickaxe: PickaxeItem(MetalTiers.DEEP_IRON, createProperties())

class DeepIronAxe: AxeItem(MetalTiers.DEEP_IRON, createProperties()) {
    override fun getAttackDamageBonus(target: Entity, damage: Float, damageSource: DamageSource): Float {
        val entity = damageSource.entity
            ?: return 0f
        if (entity.isEyeInFluidType(NeoForgeMod.WATER_TYPE.value())) {
            return 6f
        }

        return 0f
    }
}

class DeepIronShovel: ShovelItem(MetalTiers.DEEP_IRON, createProperties())

class DeepIronHoe: HoeItem(MetalTiers.DEEP_IRON, createProperties())

class DeepIronHelmet: ArmorItem(AlloyanceArmorMaterials.DEEP_IRON_MATERIAL, Type.HELMET, Item.Properties().durability(
    ArmorItem.Type.HELMET.getDurability(15)
)) {
    override fun inventoryTick(stack: ItemStack, level: Level, entity: Entity, slotId: Int, isSelected: Boolean) {
        if (entity !is LivingEntity) return
        entity.getItemBySlot(EquipmentSlot.BODY)
        super.inventoryTick(stack, level, entity, slotId, isSelected)
    }
}

enum class MetalEffectType(private val lowerName: String): StringRepresentable {
    AQUATIC("aquatic");

    override fun getSerializedName(): String = lowerName
}

data class MetalEffect(val type: MetalEffectType) {
    companion object {
        val CODEC: Codec<MetalEffect> = RecordCodecBuilder.create { instance ->
            instance.group(
                StringRepresentable.fromEnum(MetalEffectType::values).fieldOf("type").forGetter(MetalEffect::type)
            ).apply(instance, ::MetalEffect)
        }

        val STREAM_CODEC: StreamCodec<ByteBuf, MetalEffect> = StreamCodec.composite(
            ByteBufCodecs.idMapper(ByIdMap.continuous(MetalEffectType::ordinal,
                MetalEffectType.entries.toTypedArray(), ByIdMap.OutOfBoundsStrategy.ZERO),
                MetalEffectType::ordinal), MetalEffect::type,
            ::MetalEffect
        )
    }
}

@EventBusSubscriber(modid = Alloyance.ID)
object Events {
    @SubscribeEvent
    fun armorTick(event: EntityTickEvent.Post) {
        val entity = event.entity
        if (entity !is LivingEntity || entity.level().isClientSide) return

        var count = 0
        if (entity.slotHasEffect(EquipmentSlot.HEAD, MetalEffectType.AQUATIC)) count++
        if (entity.slotHasEffect(EquipmentSlot.CHEST, MetalEffectType.AQUATIC)) count++
        if (entity.slotHasEffect(EquipmentSlot.LEGS, MetalEffectType.AQUATIC)) count++
        if (entity.slotHasEffect(EquipmentSlot.FEET, MetalEffectType.AQUATIC)) count++

        if (count > 0 && entity.isEyeInFluidType(NeoForgeMod.WATER_TYPE.value())) {

        }
    }

    private fun LivingEntity.slotHasEffect(slot: EquipmentSlot, type: MetalEffectType): Boolean {
        return this.getItemBySlot(slot).get(AlloyanceDataComponents.METAL_EFFECT)?.type == type
    }
}

private fun createProperties(): Item.Properties {
    // TODO these will differ for pickaxe, axe, sword, etc.
    val attackDamage = 1.0
    val attackSpeed = -2.8
    val modifierId = ResourceLocation.fromNamespaceAndPath(Alloyance.ID, "diver_mining")
    val attributes = ItemAttributeModifiers.builder()
        // first two from PickaxeItem.createAttributes
        .add(
            Attributes.ATTACK_DAMAGE,
            AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, attackDamage + MetalTiers.DEEP_IRON.attackDamageBonus, AttributeModifier.Operation.ADD_VALUE),
            EquipmentSlotGroup.MAINHAND
        )
        .add(
            Attributes.ATTACK_SPEED,
            AttributeModifier(Item.BASE_ATTACK_SPEED_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE),
            EquipmentSlotGroup.MAINHAND
        )
        .add(
            Attributes.SUBMERGED_MINING_SPEED,
            AttributeModifier(modifierId, 3.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
            EquipmentSlotGroup.MAINHAND
        )
        .build()
    return Item.Properties()
        .component(AlloyanceDataComponents.TOOLTIP_STYLE, TooltipStyle(Metal.DEEP_IRON.color))
        .attributes(attributes)
}