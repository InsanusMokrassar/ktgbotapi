package com.github.insanusmokrassar.TelegramBotAPI.requests

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.MessageAction
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.ForwardedMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategy
import kotlinx.serialization.*

private val AbleToBeForwardedMessageDeserializer = TelegramBotAPIMessageDeserializationStrategyClass<AbleToBeForwardedMessage>()

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
): SimpleRequest<AbleToBeForwardedMessage>, MessageAction {
    override val chatId: ChatIdentifier
        get() = fromChatId

    override fun method(): String = "forwardMessage"

    override val resultDeserializer: DeserializationStrategy<AbleToBeForwardedMessage>
        get() = AbleToBeForwardedMessageDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
