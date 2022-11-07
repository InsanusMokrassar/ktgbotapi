package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.abstracts.types.DisableNotification
import dev.inmo.tgbotapi.abstracts.types.ReplyMessageId
import dev.inmo.tgbotapi.requests.send.abstracts.ReplyingMarkupSendMessageRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.dice.DiceAnimationType
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.DiceContent
import kotlinx.serialization.*

internal val DiceContentMessageResultDeserializer: DeserializationStrategy<ContentMessage<DiceContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@Serializable
data class SendDice(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(emojiField)
    val animationType: DiceAnimationType? = null,
    @SerialName(messageThreadIdField)
    override val threadId: MessageThreadId?,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageId? = null,
    @SerialName(allowSendingWithoutReplyField)
    override val allowSendingWithoutReply: Boolean? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : ReplyingMarkupSendMessageRequest<ContentMessage<DiceContent>>, ReplyMessageId, DisableNotification {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "sendDice"

    override val resultDeserializer: DeserializationStrategy<ContentMessage<DiceContent>>
        get() = DiceContentMessageResultDeserializer
}
