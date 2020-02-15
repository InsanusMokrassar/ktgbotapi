package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.members

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.UntilDate
import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.abstracts.ChatMemberRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class KickChatMember(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(untilDateField)
    override val untilDate: TelegramDate? = null
) : ChatMemberRequest<Boolean>, UntilDate {
    override fun method(): String = "kickChatMember"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = BooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.kickChatMember(
    chatId: ChatIdentifier,
    userId: UserId,
    untilDate: TelegramDate? = null
) = execute(KickChatMember(chatId, userId, untilDate))

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.kickChatMember(
    chat: PublicChat,
    userId: UserId,
    untilDate: TelegramDate? = null
) = kickChatMember(chat.id, userId, untilDate)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.kickChatMember(
    chatId: ChatId,
    user: User,
    untilDate: TelegramDate? = null
) = kickChatMember(chatId, user.id, untilDate)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.kickChatMember(
    chat: PublicChat,
    user: User,
    untilDate: TelegramDate? = null
) = kickChatMember(chat.id, user.id, untilDate)
