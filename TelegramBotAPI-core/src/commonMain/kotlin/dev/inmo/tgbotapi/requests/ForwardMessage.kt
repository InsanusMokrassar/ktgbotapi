package dev.inmo.tgbotapi.requests

import dev.inmo.tgbotapi.CommonAbstracts.types.MessageAction
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.abstracts.PossiblyForwardedMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import kotlinx.serialization.*

private val AbleToBeForwardedMessageDeserializer = TelegramBotAPIMessageDeserializationStrategyClass<PossiblyForwardedMessage>()

@Serializable
data class ForwardMessage(
    @SerialName(fromChatIdField)
    val fromChatId: ChatIdentifier,
    @SerialName(chatIdField)
    val toChatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageIdentifier,
    @SerialName(disableNotificationField)
    val disableNotification: Boolean = false
): SimpleRequest<PossiblyForwardedMessage>, MessageAction {
    override val chatId: ChatIdentifier
        get() = fromChatId

    override fun method(): String = "forwardMessage"

    override val resultDeserializer: DeserializationStrategy<PossiblyForwardedMessage>
        get() = AbleToBeForwardedMessageDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
