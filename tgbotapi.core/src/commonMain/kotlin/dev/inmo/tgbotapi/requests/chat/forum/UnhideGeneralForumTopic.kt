package dev.inmo.tgbotapi.requests.chat.forum

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class UnhideGeneralForumTopic(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
) : ModifyForumRequest, GeneralForumRequest<Boolean> {
    override fun method(): String = "unhideGeneralForumTopic"

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
