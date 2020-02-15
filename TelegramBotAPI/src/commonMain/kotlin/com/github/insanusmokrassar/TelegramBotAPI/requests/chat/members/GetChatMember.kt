package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.members

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.abstracts.ChatMemberRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember.abstracts.ChatMember
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember.abstracts.ChatMemberDeserializationStrategy
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat
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

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.getChatMember(
    chatId: ChatIdentifier,
    userId: UserId
) = execute(GetChatMember(chatId, userId))

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.getChatMember(
    chat: PublicChat,
    userId: UserId
) = getChatMember(chat.id, userId)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.getChatMember(
    chatId: ChatId,
    user: User
) = getChatMember(chatId, user.id)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.getChatMember(
    chat: PublicChat,
    user: User
) = getChatMember(chat.id, user.id)
