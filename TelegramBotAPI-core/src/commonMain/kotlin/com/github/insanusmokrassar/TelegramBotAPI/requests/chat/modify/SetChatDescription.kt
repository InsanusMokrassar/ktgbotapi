package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

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
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
