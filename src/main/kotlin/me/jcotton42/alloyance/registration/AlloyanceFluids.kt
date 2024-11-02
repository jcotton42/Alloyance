package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.MoltenTarFluid
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.pathfinder.PathType
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.fluids.BaseFlowingFluid
import net.neoforged.neoforge.fluids.FluidType
import net.neoforged.neoforge.registries.DeferredRegister
import net.neoforged.neoforge.registries.NeoForgeRegistries

object AlloyanceFluids {
    val FLUIDS = DeferredRegister.create(Registries.FLUID, Alloyance.ID)
    val FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, Alloyance.ID)

    val TAR_TYPE = FLUID_TYPES.register("molten_tar") { ->
        FluidType(
            FluidType.Properties.create()
                .temperature(400)
                .canDrown(true)
                .canSwim(true)
                .pathType(PathType.WATER)
                .density(800)
                .viscosity(4000)
                .motionScale(0.0028)
                .descriptionId(AlloyanceBlocks.MOLTEN_TAR.id.toLanguageKey("block"))
        )
    }
    val MOLTEN_TAR = FLUIDS.register("molten_tar") { -> MoltenTarFluid.Source(tarProperties()) }
    val FLOWING_MOLTEN_TAR = FLUIDS.register("flowing_molten_tar") { -> MoltenTarFluid.Flowing(tarProperties()) }

    fun register(bus: IEventBus) {
        FLUIDS.register(bus)
        FLUID_TYPES.register(bus)
    }

    private fun tarProperties(): BaseFlowingFluid.Properties {
        return BaseFlowingFluid.Properties(TAR_TYPE, MOLTEN_TAR, FLOWING_MOLTEN_TAR)
            .block(AlloyanceBlocks.MOLTEN_TAR)
            .bucket(AlloyanceItems.MOLTEN_TAR_BUCKET)
    }
}