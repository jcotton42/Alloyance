package me.jcotton42.alloyance.machine

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import io.netty.buffer.ByteBuf
import me.jcotton42.alloyance.registration.AlloyanceDataComponents
import me.jcotton42.alloyance.registration.AlloyanceDataMaps
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.ItemStack

@JvmRecord
data class FuelProperties(val speed: Int) {
    companion object {
        val CODEC: Codec<FuelProperties> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.INT.fieldOf("speed").forGetter(FuelProperties::speed)
            ).apply(instance, ::FuelProperties)
        }

        val STREAM_CODEC: StreamCodec<ByteBuf, FuelProperties> = StreamCodec.composite(
            ByteBufCodecs.INT, FuelProperties::speed,
            ::FuelProperties
        )
    }
}

fun getFuelProperties(stack: ItemStack): FuelProperties? {
    return stack.get(AlloyanceDataComponents.FUEL_PROPERTIES)
        ?: stack.itemHolder.getData(AlloyanceDataMaps.FUEL_PROPERTIES)
}
