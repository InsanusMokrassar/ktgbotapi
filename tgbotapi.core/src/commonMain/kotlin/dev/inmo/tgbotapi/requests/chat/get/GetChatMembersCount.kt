package dev.inmo.tgbotapi.requests.chat.get

import dev.inmo.tgbotapi.CommonAbstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chatIdField
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class GetChatMembersCount(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier
): ChatRequest, SimpleRequest<Int> {
    override fun method(): String = "getChatMembersCount"
    override val resultDeserializer: DeserializationStrategy<Int>
        get() = Int.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
