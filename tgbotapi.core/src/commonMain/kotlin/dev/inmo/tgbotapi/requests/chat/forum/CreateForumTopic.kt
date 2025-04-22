package dev.inmo.tgbotapi.requests.chat.forum

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.RGBColor
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class CreateForumTopic(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(nameField)
    val name: String,
    @SerialName(iconColorField)
    val color: RGBColor,
    @SerialName(iconCustomEmojiIdField)
    val iconEmojiId: CustomEmojiId? = null,
) : ForumRequest<ForumTopic> {
    init {
        if (name.length !in threadNameLength) {
            throw IllegalArgumentException("Thread name must be in $threadNameLength range")
        }
    }

    override fun method(): String = "createForumTopic"

    override val resultDeserializer: DeserializationStrategy<ForumTopic>
        get() = ForumTopic.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
