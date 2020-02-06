package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.get

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat
import com.github.insanusmokrassar.TelegramBotAPI.types.chatIdField
import kotlinx.serialization.*
import kotlinx.serialization.internal.IntSerializer

@Serializable
data class GetChatMembersCount(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier
): ChatRequest, SimpleRequest<Int> {
    override fun method(): String = "getChatMembersCount"
    override val resultDeserializer: DeserializationStrategy<Int>
        get() = IntSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

suspend fun RequestsExecutor.getChatMembersCount(
    chatId: ChatIdentifier
) = execute(GetChatMembersCount(chatId))

suspend fun RequestsExecutor.getChatMembersCount(
    chat: PublicChat
) = getChatMembersCount(chat.id)
