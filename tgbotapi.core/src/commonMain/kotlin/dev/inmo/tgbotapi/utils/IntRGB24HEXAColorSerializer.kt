package dev.inmo.tgbotapi.utils

import dev.inmo.micro_utils.colors.common.HEXAColor
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object IntRGB24HEXAColorSerializer : KSerializer<HEXAColor> {
    override val descriptor: SerialDescriptor
        get() = Int.serializer().descriptor

    override fun deserialize(decoder: Decoder): HEXAColor {
        val raw = decoder.decodeInt()
        return HEXAColor(raw.shl(2).toUInt() + 0xffu)
    }

    override fun serialize(
        encoder: Encoder,
        value: HEXAColor,
    ) {
        encoder.encodeInt(value.rgbInt)
    }
}
