package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.members

import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.abstracts.ChatMemberRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember.abstracts.ChatMember
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember.abstracts.ChatMemberDeserializationStrategy
import kotlinx.serialization.*

@Serializable
data class GetChatMember(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(userIdField)
    override val userId: UserId
) : ChatMemberRequest<ChatMember> {
    override fun method(): String = "getChatMember"
    override val resultDeserializer: DeserializationStrategy<ChatMember>
        get() = ChatMemberDeserializationStrategy
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
