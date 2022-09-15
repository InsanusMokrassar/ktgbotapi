package dev.inmo.tgbotapi.types

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

object MessageIdSerializer : KSerializer<MessageId> {
    override val descriptor: SerialDescriptor = JsonObject.serializer().descriptor

    override fun deserialize(decoder: Decoder): MessageId = JsonObject.serializer().deserialize(decoder)[messageIdField]!!.jsonPrimitive.long

    override fun serialize(encoder: Encoder, value: MessageId) {
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
