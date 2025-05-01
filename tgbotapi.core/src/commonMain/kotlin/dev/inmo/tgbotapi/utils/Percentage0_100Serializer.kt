package dev.inmo.tgbotapi.utils

import dev.inmo.micro_utils.common.Percentage
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object Percentage0_100Serializer : KSerializer<Percentage> {
    override val descriptor: SerialDescriptor
        get() = Double.serializer().descriptor

    override fun deserialize(decoder: Decoder): Percentage {
        return decoder.decodeDouble().let(Percentage::of100)
    }

    override fun serialize(encoder: Encoder, value: Percentage) {
        encoder.encodeDouble(value.of100)
    }

}