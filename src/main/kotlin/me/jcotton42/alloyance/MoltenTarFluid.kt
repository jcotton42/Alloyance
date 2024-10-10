package me.jcotton42.alloyance

import me.jcotton42.alloyance.registration.AlloyanceBlocks
import me.jcotton42.alloyance.registration.AlloyanceFluids
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.FluidState
import net.neoforged.neoforge.fluids.BaseFlowingFluid

abstract class MoltenTarFluid(properties: Properties): BaseFlowingFluid(properties) {
    class Flowing(properties: Properties): MoltenTarFluid(properties) {
        init {
            registerDefaultState(getStateDefinition().any().setValue(LEVEL, 7))
        }

        override fun createFluidStateDefinition(builder: StateDefinition.Builder<Fluid, FluidState>) {
            super.createFluidStateDefinition(builder)
            builder.add(LEVEL)
        }

        override fun isSource(state: FluidState): Boolean = false

        override fun getAmount(state: FluidState): Int {
            return state.getValue(LEVEL)
        }
    }

    class Source(properties: Properties): MoltenTarFluid(properties) {
        override fun getAmount(state: FluidState): Int = 8

        override fun isSource(state: FluidState): Boolean = true

        override fun isRandomlyTicking(): Boolean = true

        override fun randomTick(level: Level, pos: BlockPos, state: FluidState, random: RandomSource) {
            for (d in Direction.entries) {
                val adjacent = level.getFluidState(pos.relative(d))
                if (adjacent.isSourceOfType(AlloyanceFluids.MOLTEN_TAR.get())) {
                    return
                }
            }
            level.setBlock(pos, AlloyanceBlocks.TAR_ORE.get().defaultBlockState(), 3)
        }
    }
}