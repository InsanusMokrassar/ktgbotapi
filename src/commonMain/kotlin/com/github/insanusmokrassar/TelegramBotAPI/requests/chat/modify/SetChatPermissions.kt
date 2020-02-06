package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.ChatPermissions
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class SetChatPermissions (
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(permissionsField)
    val permissions: ChatPermissions
): ChatRequest, SimpleRequest<Boolean> {
    override fun method(): String = "setChatPermissions"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = BooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

suspend fun RequestsExecutor.setChatPermissions(
    chatId: ChatIdentifier,
    permissions: ChatPermissions
) = execute(SetChatPermissions(chatId, permissions))

suspend fun RequestsExecutor.setChatPermissions(
    chat: Chat,
    permissions: ChatPermissions
) = setChatPermissions(chat.id, permissions)
