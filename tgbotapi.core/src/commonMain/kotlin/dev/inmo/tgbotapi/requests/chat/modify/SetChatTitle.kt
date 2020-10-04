package dev.inmo.tgbotapi.requests.chat.modify

import dev.inmo.tgbotapi.CommonAbstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class SetChatTitle (
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(titleField)
    val title: String
): ChatRequest, SimpleRequest<Boolean> {
    init {
        if (title.length !in chatTitleLength) {
            throw IllegalArgumentException("Chat title must be in $chatTitleLength range")
        }
    }

    override fun method(): String = "setChatTitle"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
