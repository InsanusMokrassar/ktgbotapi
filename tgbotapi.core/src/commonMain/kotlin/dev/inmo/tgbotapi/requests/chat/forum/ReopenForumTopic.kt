package dev.inmo.tgbotapi.requests.chat.forum

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class ReopenForumTopic(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageThreadIdField)
    val messageThreadId: MessageThreadId,
) : ModifyForumRequest {
    override fun method(): String = "reopenForumTopic"

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
