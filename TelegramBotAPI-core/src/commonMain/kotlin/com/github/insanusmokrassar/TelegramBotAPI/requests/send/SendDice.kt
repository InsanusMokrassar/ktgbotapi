package com.github.insanusmokrassar.TelegramBotAPI.requests.send

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.DisableNotification
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ReplyMessageId
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.ReplyingMarkupSendMessageRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.dice.DiceAnimationType
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.DiceContent
import kotlinx.serialization.*

internal val DiceContentMessageResultDeserializer: DeserializationStrategy<ContentMessage<DiceContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@Serializable
data class SendDice(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(emojiField)
    val animationType: DiceAnimationType? = null,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : ReplyingMarkupSendMessageRequest<ContentMessage<DiceContent>>, ReplyMessageId, DisableNotification {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "sendDice"

    override val resultDeserializer: DeserializationStrategy<ContentMessage<DiceContent>>
        get() = DiceContentMessageResultDeserializer
}