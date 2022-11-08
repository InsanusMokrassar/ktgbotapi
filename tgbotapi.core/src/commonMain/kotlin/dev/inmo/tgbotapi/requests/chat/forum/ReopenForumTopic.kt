package dev.inmo.tgbotapi.requests.chat.forum

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.RGBColor
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class ReopenForumTopic (
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageThreadIdField)
    val messageThreadId: MessageThreadId
): ModifyForumRequest {
    override fun method(): String = "reopenForumTopic"
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
