package dev.inmo.tgbotapi.requests

import dev.inmo.tgbotapi.abstracts.types.MessageAction
import dev.inmo.tgbotapi.abstracts.types.MessagesAction
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class DeleteMessages(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdsField)
    override val messageIds: List<MessageId>
) : SimpleRequest<Unit>, MessagesAction {
    override fun method(): String = "deleteMessages"

    init {
        require(messageIds.size in deleteMessagesLimit) {
            "Messages count for deleteMessages must be in $deleteMessagesLimit range"
        }
    }

    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
