package dev.inmo.tgbotapi.utils.serializers

import dev.inmo.tgbotapi.types.Username
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object UsernameAtLessSerializer : KSerializer<Username> {
    override val descriptor: SerialDescriptor
        get() = String.serializer().descriptor

    override fun serialize(encoder: Encoder, value: Username) {
        value.withoutAt.let(encoder::encodeString)
    }

    override fun deserialize(decoder: Decoder): Username {
        return Username.prepare(decoder.decodeString())
    }
}