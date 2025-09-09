package dev.inmo.tgbotapi.requests.suggested

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.ChatIdentifierSerializer
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.chatIdField
import dev.inmo.tgbotapi.types.commentField
import dev.inmo.tgbotapi.types.messageIdField
import dev.inmo.tgbotapi.types.sendDateField
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.serializer

@Serializable
data class DeclineSuggestedPost(
    @SerialName(chatIdField)
    @Serializable(ChatIdentifierSerializer::class)
    override val chatId: IdChatIdentifier,
    @SerialName(messageIdField)
    val messageId: MessageId,
    @SerialName(commentField)
    val comment: String? = null,
) : SimpleRequest<Boolean>, ChatRequest {
    override val requestSerializer: SerializationStrategy<DeclineSuggestedPost>
        get() = serializer()

    override fun method(): String = "declineSuggestedPost"

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
}