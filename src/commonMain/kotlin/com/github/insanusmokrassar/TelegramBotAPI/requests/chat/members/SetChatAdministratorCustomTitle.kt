package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.members

import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.abstracts.ChatMemberRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

/**
 * Representation of https://core.telegram.org/bots/api#setchatadministratorcustomtitle
 *
 * Please, remember about restrictions for characters in custom title
 */
@Serializable
data class SetChatAdministratorCustomTitle(
    @SerialName(chatIdField)
    override val chatId: ChatId,
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(customTitleField)
    val customTitle: String
) : ChatMemberRequest<Boolean> {
    override fun method(): String = "setChatAdministratorCustomTitle"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = BooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = RestrictChatMember.serializer()

    init {
        if (customTitle.length !in customTitleLength) {
            throw IllegalArgumentException("Custom title length must be in range $customTitleLength, but was ${customTitle.length}")
        }
    }
}