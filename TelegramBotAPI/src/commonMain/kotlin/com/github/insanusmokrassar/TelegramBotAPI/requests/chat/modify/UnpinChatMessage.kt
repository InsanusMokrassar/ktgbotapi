package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat
import com.github.insanusmokrassar.TelegramBotAPI.types.chatIdField
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class UnpinChatMessage(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier
): ChatRequest, SimpleRequest<Boolean> {
    override fun method(): String = "unpinChatMessage"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = BooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.unpinChatMessage(
    chatId: ChatIdentifier
) = execute(UnpinChatMessage(chatId))

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.unpinChatMessage(
    chat: PublicChat
) = unpinChatMessage(chat.id)
