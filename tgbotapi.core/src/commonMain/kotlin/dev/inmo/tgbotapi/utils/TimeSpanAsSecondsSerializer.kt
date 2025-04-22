package dev.inmo.tgbotapi.utils

import korlibs.time.TimeSpan
import korlibs.time.seconds
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object TimeSpanAsSecondsSerializer : KSerializer<TimeSpan> {
    override val descriptor: SerialDescriptor
        get() = Long.serializer().descriptor

    override fun deserialize(decoder: Decoder): TimeSpan {
        return decoder.decodeLong().seconds
    }

    override fun serialize(
        encoder: Encoder,
        value: TimeSpan,
    ) {
        encoder.encodeLong(value.inWholeSeconds)
    }
}
