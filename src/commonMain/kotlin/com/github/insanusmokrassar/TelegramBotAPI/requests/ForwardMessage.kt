package com.github.insanusmokrassar.TelegramBotAPI.requests

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import kotlinx.serialization.*

@Serializable
data class ForwardMessage(
    @SerialName(fromChatIdField)
    val fromChatId: ChatIdentifier,
    @SerialName(chatIdField)
    val toChatId: ChatIdentifier,
    @SerialName(messageIdField)
    val messageId: MessageIdentifier,
    @SerialName(disableNotificationField)
    @Optional
    val disableNotification: Boolean = false
): SimpleRequest<RawMessage> {
    override fun method(): String = "forwardMessage"

    override fun resultSerializer(): KSerializer<RawMessage> = RawMessage.serializer()
}
