package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.abstracts.types.DisableNotification
import dev.inmo.tgbotapi.abstracts.types.WithReplyParameters
import dev.inmo.tgbotapi.requests.send.abstracts.ReplyingMarkupSendMessageRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
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
    override val threadId: MessageThreadId? = chatId.threadId,
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(replyParametersField)
    override val replyParameters: ReplyParameters? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : ReplyingMarkupSendMessageRequest<ContentMessage<DiceContent>>, WithReplyParameters, DisableNotification {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "sendDice"

    override val resultDeserializer: DeserializationStrategy<ContentMessage<DiceContent>>
        get() = DiceContentMessageResultDeserializer
}
