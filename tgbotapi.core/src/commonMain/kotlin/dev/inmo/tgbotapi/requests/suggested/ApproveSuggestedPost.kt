package dev.inmo.tgbotapi.requests.suggested

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ChatIdentifierSerializer
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.chatIdField
import dev.inmo.tgbotapi.types.messageIdField
import dev.inmo.tgbotapi.types.sendDateField
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.serializer

@Serializable
data class ApproveSuggestedPost(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(chatIdField)
    @Serializable(ChatIdentifierSerializer::class)
    override val chatId: IdChatIdentifier,
    @SerialName(messageIdField)
    val messageId: MessageId,
    @SerialName(sendDateField)
    val sendDate: TelegramDate? = null,
) : SimpleRequest<Boolean>, ChatRequest {
    override val requestSerializer: SerializationStrategy<ApproveSuggestedPost>
        get() = serializer()

    override fun method(): String = "approveSuggestedPost"

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
}