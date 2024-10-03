package me.jcotton42.alloyance.client

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import io.netty.buffer.ByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec

@JvmRecord
data class TooltipStyle(val color: Int) {
    companion object {
        val CODEC: Codec<TooltipStyle> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.INT.fieldOf("color").forGetter(TooltipStyle::color)
            ).apply(instance, ::TooltipStyle)
        }

        val STREAM_CODEC: StreamCodec<ByteBuf, TooltipStyle> = StreamCodec.composite(
            ByteBufCodecs.INT, TooltipStyle::color,
            ::TooltipStyle
        )
    }
}
