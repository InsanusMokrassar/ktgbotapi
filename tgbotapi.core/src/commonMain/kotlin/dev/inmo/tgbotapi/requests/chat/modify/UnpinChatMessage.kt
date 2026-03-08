package dev.inmo.tgbotapi.requests.chat.modify

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.abstracts.types.OptionallyBusinessConnectionRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class UnpinChatMessage(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    val messageId: MessageId? = null,
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId? = null
): ChatRequest, SimpleRequest<Unit>, OptionallyBusinessConnectionRequest {
    override fun method(): String = "unpinChatMessage"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
