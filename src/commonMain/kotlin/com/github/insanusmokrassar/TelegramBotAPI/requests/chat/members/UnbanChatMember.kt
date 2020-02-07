package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.members

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.abstracts.ChatMemberRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class UnbanChatMember(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(userIdField)
    override val userId: UserId
) : ChatMemberRequest<Boolean> {
    override fun method(): String = "unbanChatMember"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = BooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

suspend fun RequestsExecutor.unbanChatMember(
    chatId: ChatIdentifier,
    userId: UserId
) = execute(UnbanChatMember(chatId, userId))

suspend fun RequestsExecutor.unbanChatMember(
    chat: PublicChat,
    userId: UserId
) = unbanChatMember(chat.id, userId)

suspend fun RequestsExecutor.unbanChatMember(
    chatId: ChatId,
    user: User
) = unbanChatMember(chatId, user.id)

suspend fun RequestsExecutor.unbanChatMember(
    chat: PublicChat,
    user: User
) = unbanChatMember(chat.id, user.id)

