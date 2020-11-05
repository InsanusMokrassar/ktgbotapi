package dev.inmo.tgbotapi.types

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

object MessageIdSerializer : KSerializer<MessageIdentifier> {
    override val descriptor: SerialDescriptor = JsonObject.serializer().descriptor

    override fun deserialize(decoder: Decoder): MessageIdentifier = JsonObject.serializer().deserialize(decoder)[messageIdField]!!.jsonPrimitive.long

    override fun serialize(encoder: Encoder, value: MessageIdentifier) {
        JsonObject.serializer().serialize(
            encoder,
            JsonObject(
                mapOf(
                    messageIdField to JsonPrimitive(value)
                )
            )
        )
    }
}
