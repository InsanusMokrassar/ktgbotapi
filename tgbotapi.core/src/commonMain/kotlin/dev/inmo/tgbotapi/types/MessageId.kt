package dev.inmo.tgbotapi.types

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import kotlin.jvm.JvmInline

object MessageIdSerializer : KSerializer<MessageId> {
    override val descriptor: SerialDescriptor = JsonObject.serializer().descriptor

    override fun deserialize(decoder: Decoder): MessageId = JsonObject.serializer().deserialize(decoder)[messageIdField]!!.jsonPrimitive.long.asTelegramMessageId()

    override fun serialize(encoder: Encoder, value: MessageId) {
        JsonObject.serializer().serialize(
            encoder,
            JsonObject(
                mapOf(
                    messageIdField to JsonPrimitive(value.long)
                )
            )
        )
    }
}

@Serializable
@JvmInline
value class MessageId(
    val long: Long
) {
    override fun toString(): String {
        return long.toString()
    }
}

fun Long.asTelegramMessageId() = MessageId(this)

@Deprecated("Renamed", ReplaceWith("MessageId", "dev.inmo.tgbotapi.types.MessageId"))
typealias MessageIdentifier = MessageId