package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.members

import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.abstracts.ChatMemberRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember.RawChatMember
import kotlinx.serialization.*

@Serializable
data class GetChatMember(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(userIdField)
    override val userId: UserId
) : ChatMemberRequest<RawChatMember> {
    override fun method(): String = "getChatMember"
    override fun resultSerializer(): KSerializer<RawChatMember> = RawChatMember.serializer()
}
