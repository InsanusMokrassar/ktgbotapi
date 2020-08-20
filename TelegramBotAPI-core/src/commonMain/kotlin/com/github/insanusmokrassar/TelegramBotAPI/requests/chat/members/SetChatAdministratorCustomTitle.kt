package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.members

import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.abstracts.ChatMemberRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class SetChatAdministratorCustomTitle(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(customTitleField)
    val customTitle: String
) : ChatMemberRequest<Boolean> {
    override fun method(): String = "setChatAdministratorCustomTitle"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = RestrictChatMember.serializer()

    init {
        if (customTitle.length !in customTitleLength) {
            throw IllegalArgumentException("Custom title length must be in range $customTitleLength, but was ${customTitle.length}")
        }
    }
}
