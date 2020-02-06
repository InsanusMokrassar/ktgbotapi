package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.members

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.abstracts.ChatMemberRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.get.getChatMembersCount
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember.abstracts.ChatMember
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember.abstracts.ChatMemberDeserializationStrategy
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
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

suspend fun RequestsExecutor.getChatMember(
    chatId: ChatIdentifier,
    userId: UserId
) = execute(GetChatMember(chatId, userId))

suspend fun RequestsExecutor.getChatMember(
    chat: Chat,
    userId: UserId
) = getChatMember(chat.id, userId)

suspend fun RequestsExecutor.getChatMember(
    chatId: ChatId,
    user: User
) = getChatMember(chatId, user.id)

suspend fun RequestsExecutor.getChatMember(
    chat: Chat,
    user: User
) = getChatMember(chat.id, user.id)
