package dev.inmo.tgbotapi.requests

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.limitField
import dev.inmo.tgbotapi.types.message.RawMessage
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.ChatMessage
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.userIdField
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder

@Serializable
data class GetUserPersonalChatMessages(
    @SerialName(userIdField)
    val userId: ChatId,
    @SerialName(limitField)
    val limit: Int
) : SimpleRequest<List<ChatContentMessage<*>>> {
    override fun method(): String = "getUserPersonalChatMessages"
    override val resultDeserializer: DeserializationStrategy<List<ChatContentMessage<*>>>
        get() = resultSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    private object MessagesDeserializer : DeserializationStrategy<List<ChatContentMessage<*>>> {
        private val rawListSerializer = ListSerializer(RawMessage.serializer())
        override val descriptor: SerialDescriptor = rawListSerializer.descriptor
        @Suppress("UNCHECKED_CAST")
        override fun deserialize(decoder: Decoder): List<ChatContentMessage<*>> {
            return rawListSerializer.deserialize(decoder).map {
                it.asMessage as ChatContentMessage<*>
            }
        }
    }

    companion object {
        internal val resultSerializer: DeserializationStrategy<List<ChatContentMessage<*>>> = MessagesDeserializer
    }
}
