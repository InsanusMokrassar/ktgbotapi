package dev.inmo.tgbotapi.requests.send.games

import dev.inmo.tgbotapi.CommonAbstracts.types.ReplyMarkup
import dev.inmo.tgbotapi.requests.send.abstracts.SendMessageRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.GameContent
import kotlinx.serialization.*

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<GameContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@Serializable
data class SendGame (
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(gameShortNameField)
    val gameShortName: String,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(allowSendingWithoutReplyField)
    override val allowSendingWithoutReply: Boolean? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendMessageRequest<ContentMessage<GameContent>>,
    ReplyMarkup {
    override fun method(): String = "sendGame"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<GameContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
