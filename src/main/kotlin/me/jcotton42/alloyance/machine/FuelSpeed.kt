package me.jcotton42.alloyance.machine

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

@JvmRecord
data class FuelSpeed(val speed: Int) {
    companion object {
        val CODEC = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.intRange(1, Int.MAX_VALUE).fieldOf("speed").forGetter(FuelSpeed::speed)
            ).apply(instance, ::FuelSpeed)
        }
    }
}
