package com.github.insanusmokrassar.TelegramBotAPI.requests

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.MessageAction
import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.*
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

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.forwardMessage(
    fromChatId: ChatIdentifier,
    toChatId: ChatIdentifier,
    messageId: MessageIdentifier,
    disableNotification: Boolean = false
) = execute(
    ForwardMessage(fromChatId, toChatId, messageId, disableNotification)
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.forwardMessage(
    fromChat: Chat,
    toChatId: ChatIdentifier,
    messageId: MessageIdentifier,
    disableNotification: Boolean = false
) = forwardMessage(fromChat.id, toChatId, messageId, disableNotification)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.forwardMessage(
    fromChatId: ChatIdentifier,
    toChat: Chat,
    messageId: MessageIdentifier,
    disableNotification: Boolean = false
) = forwardMessage(fromChatId, toChat.id, messageId, disableNotification)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.forwardMessage(
    fromChat: Chat,
    toChat: Chat,
    messageId: MessageIdentifier,
    disableNotification: Boolean = false
) = forwardMessage(fromChat.id, toChat.id, messageId, disableNotification)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.forwardMessage(
    toChatId: ChatIdentifier,
    message: Message,
    disableNotification: Boolean = false
) = forwardMessage(message.chat, toChatId, message.messageId, disableNotification)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.forwardMessage(
    toChat: Chat,
    message: Message,
    disableNotification: Boolean = false
) = forwardMessage(message.chat, toChat, message.messageId, disableNotification)
