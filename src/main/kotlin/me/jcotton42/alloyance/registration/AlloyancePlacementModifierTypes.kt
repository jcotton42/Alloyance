package me.jcotton42.alloyance.registration

import com.mojang.serialization.MapCodec
import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.worldgen.BiomeTemperatureFilter
import me.jcotton42.alloyance.worldgen.StructureProximityFilter
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.levelgen.placement.PlacementModifier
import net.minecraft.world.level.levelgen.placement.PlacementModifierType
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister

object AlloyancePlacementModifierTypes {
    val TYPES = DeferredRegister.create(Registries.PLACEMENT_MODIFIER_TYPE, Alloyance.ID)

    val BIOME_TEMPERATURE_FILTER = register("biome_temperature_filter", BiomeTemperatureFilter.CODEC)
    val STRUCTURE_PROXIMITY_FILTER = register("structure_proximity_filter", StructureProximityFilter.CODEC)

    private fun <P: PlacementModifier> register(name: String, codec: MapCodec<P>): DeferredHolder<PlacementModifierType<*>, PlacementModifierType<P>> {
        val type = PlacementModifierType { codec }
        return TYPES.register(name) { -> type }
    }

    fun register(bus: IEventBus) {
        TYPES.register(bus)
    }
}