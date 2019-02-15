package com.github.insanusmokrassar.TelegramBotAPI.requests.send.games

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ReplyMarkup
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.SendMessageRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import kotlinx.serialization.*

@Serializable
data class SendGame (
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(gameShortNameField)
    val gameShortName: String,
    @SerialName(disableNotificationField)
    @Optional
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    @Optional
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    @Optional
    override val replyMarkup: KeyboardMarkup? = null
) : SendMessageRequest<RawMessage>,
    ReplyMarkup {
    override fun method(): String = "sendGame"
    override fun resultSerializer(): KSerializer<RawMessage> = RawMessage.serializer()
}