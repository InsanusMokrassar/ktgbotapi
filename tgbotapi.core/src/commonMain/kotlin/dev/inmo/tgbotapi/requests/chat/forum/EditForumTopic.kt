package dev.inmo.tgbotapi.requests.chat.forum

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

@Serializable
data class EditForumTopic(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageThreadIdField)
    val messageThreadId: MessageThreadId,
    @SerialName(nameField)
    val name: String? = null,
    @SerialName(iconCustomEmojiIdField)
    val iconEmojiId: CustomEmojiId? = null,
) : ModifyForumRequest {
    init {
        if (name != null && name.length !in threadNameLength) {
            throw IllegalArgumentException("Thread name must be in $threadNameLength range")
        }
    }

    override fun method(): String = "editForumTopic"

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
