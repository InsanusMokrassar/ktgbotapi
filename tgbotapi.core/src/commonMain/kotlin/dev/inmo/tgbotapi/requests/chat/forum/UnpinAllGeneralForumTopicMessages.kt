package dev.inmo.tgbotapi.requests.chat.forum

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

@Serializable
data class UnpinAllGeneralForumTopicMessages (
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
): ModifyForumRequest {
    override fun method(): String = "unpinAllGeneralForumTopicMessages"
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
