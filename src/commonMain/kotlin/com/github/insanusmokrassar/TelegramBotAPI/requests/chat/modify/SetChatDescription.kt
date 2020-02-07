package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class SetChatDescription (
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(descriptionField)
    val description: String
): ChatRequest, SimpleRequest<Boolean> {
    init {
        if (description.length !in chatDescriptionLength) {
            throw IllegalArgumentException("Chat description must be in $chatDescriptionLength range")
        }
    }

    override fun method(): String = "setChatDescription"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = BooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

suspend fun RequestsExecutor.setChatDescription(
    chatId: ChatIdentifier,
    description: String
) = execute(SetChatDescription(chatId, description))

suspend fun RequestsExecutor.setChatDescription(
    chat: PublicChat,
    description: String
) = setChatDescription(chat.id, description)
