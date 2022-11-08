package dev.inmo.tgbotapi.requests.chat.forum

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.RGBColor
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class EditForumTopic (
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageThreadIdField)
    val messageThreadId: MessageThreadId,
    @SerialName(nameField)
    val name: String,
    @SerialName(iconCustomEmojiIdField)
    val iconEmojiId: CustomEmojiId,
): ModifyForumRequest {
    init {
        if (name.length !in threadNameLength) {
            throw IllegalArgumentException("Thread name must be in $threadNameLength range")
        }
    }

    override fun method(): String = "editForumTopic"
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
