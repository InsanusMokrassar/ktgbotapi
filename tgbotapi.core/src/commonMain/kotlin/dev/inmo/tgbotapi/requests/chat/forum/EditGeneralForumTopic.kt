package dev.inmo.tgbotapi.requests.chat.forum

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

@Serializable
data class EditGeneralForumTopic(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(nameField)
    val name: String,
) : ModifyForumRequest, GeneralForumRequest<Boolean> {
    init {
        if (name.length !in threadNameLength) {
            throw IllegalArgumentException("Thread name must be in $threadNameLength range")
        }
    }

    override fun method(): String = "editGeneralForumTopic"

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
