package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.members

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.abstracts.ChatMemberRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

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
}