package dev.inmo.tgbotapi.requests.chat

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chatIdField
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class ExportChatInviteLink(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier
): ChatRequest, SimpleRequest<String> {
    override fun method(): String = "exportChatInviteLink"
    override val resultDeserializer: DeserializationStrategy<String>
        get() = String.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
