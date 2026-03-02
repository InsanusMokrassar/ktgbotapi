package dev.inmo.tgbotapi.utils.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object UnitFromBooleanSerializer : KSerializer<Unit> {
    override val descriptor: SerialDescriptor
        get() = Boolean.serializer().descriptor

    override fun serialize(encoder: Encoder, value: Unit) {
        encoder.encodeBoolean(true)
    }

    override fun deserialize(decoder: Decoder) {
        return if (decoder.decodeBoolean()) {
            Unit
        } else {
            throw IllegalStateException("Can't deserialize Unit from false")
        }
    }
}