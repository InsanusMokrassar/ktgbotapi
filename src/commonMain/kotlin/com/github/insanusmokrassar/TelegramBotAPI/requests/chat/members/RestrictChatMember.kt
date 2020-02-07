package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.members

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.UntilDate
import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.abstracts.ChatMemberRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.ChatPermissions
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class RestrictChatMember(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(untilDateField)
    override val untilDate: TelegramDate? = null,
    @SerialName(permissionsField)
    val permissions: ChatPermissions = ChatPermissions()
) : ChatMemberRequest<Boolean>, UntilDate {
    override fun method(): String = "restrictChatMember"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = BooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

suspend fun RequestsExecutor.restrictChatMember(
    chatId: ChatIdentifier,
    userId: UserId,
    untilDate: TelegramDate? = null,
    permissions: ChatPermissions = ChatPermissions()
) = execute(RestrictChatMember(chatId, userId, untilDate, permissions))

suspend fun RequestsExecutor.restrictChatMember(
    chat: PublicChat,
    userId: UserId,
    untilDate: TelegramDate? = null,
    permissions: ChatPermissions = ChatPermissions()
) = restrictChatMember(chat.id, userId, untilDate, permissions)

suspend fun RequestsExecutor.restrictChatMember(
    chatId: ChatId,
    user: User,
    untilDate: TelegramDate? = null,
    permissions: ChatPermissions = ChatPermissions()
) = restrictChatMember(chatId, user.id, untilDate, permissions)

suspend fun RequestsExecutor.restrictChatMember(
    chat: PublicChat,
    user: User,
    untilDate: TelegramDate? = null,
    permissions: ChatPermissions = ChatPermissions()
) = restrictChatMember(chat.id, user.id, untilDate, permissions)

