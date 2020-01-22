package com.github.insanusmokrassar.TelegramBotAPI.requests

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.MessageAction
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.PossiblyForwardedMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
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
