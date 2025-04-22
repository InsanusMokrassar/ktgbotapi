package dev.inmo.tgbotapi.requests

import dev.inmo.tgbotapi.abstracts.types.MessagesAction
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class DeleteMessages(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdsField)
    override val messageIds: List<MessageId>,
) : SimpleRequest<Boolean>, MessagesAction {
    override fun method(): String = "deleteMessages"

    init {
        require(messageIds.size in deleteMessagesLimit) {
            "Messages count for deleteMessages must be in $deleteMessagesLimit range"
        }
    }

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
