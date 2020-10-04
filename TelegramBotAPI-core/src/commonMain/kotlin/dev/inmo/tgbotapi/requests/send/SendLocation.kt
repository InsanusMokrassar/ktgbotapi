package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.LocationContent
import kotlinx.serialization.*


private val commonResultDeserializer: DeserializationStrategy<ContentMessage<LocationContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@Serializable
data class SendLocation(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(latitudeField)
    override val latitude: Double,
    @SerialName(longitudeField)
    override val longitude: Double,
    @SerialName(livePeriodField)
    val livePeriod: Long? = null,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendMessageRequest<ContentMessage<LocationContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<LocationContent>>,
    PositionedSendMessageRequest<ContentMessage<LocationContent>>
{
    override fun method(): String = "sendLocation"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<LocationContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    init {
        if (livePeriod != null && livePeriod !in livePeriodLimit) {
            error("Live period for sending location must be in $livePeriodLimit, but was $livePeriod")
        }
    }
}
