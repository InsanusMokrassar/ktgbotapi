package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.abstracts.*
import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.utils.throwRangeError
import kotlinx.serialization.*


private val commonResultDeserializer: DeserializationStrategy<ContentMessage<LocationContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

fun SendLocation(
    chatId: ChatIdentifier,
    latitude: Double,
    longitude: Double,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendLocation(
    chatId,
    latitude,
    longitude,
    null,
    null,
    null,
    null,
    threadId,
    businessConnectionId,
    disableNotification,
    protectContent,
    effectId,
    replyParameters,
    replyMarkup
)

fun SendStaticLocation(
    chatId: ChatIdentifier,
    latitude: Double,
    longitude: Double,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendLocation(
    chatId = chatId,
    latitude = latitude,
    longitude = longitude,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

fun SendLiveLocation(
    chatId: ChatIdentifier,
    latitude: Double,
    longitude: Double,
    livePeriod: Seconds,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendLocation(
    chatId = chatId,
    latitude = latitude,
    longitude = longitude,
    livePeriod = livePeriod,
    horizontalAccuracy = horizontalAccuracy,
    heading = heading,
    proximityAlertRadius = proximityAlertRadius,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

@Serializable
data class SendLocation internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(latitudeField)
    override val latitude: Double,
    @SerialName(longitudeField)
    override val longitude: Double,
    @SerialName(livePeriodField)
    override val livePeriod: Seconds? = null,
    @SerialName(horizontalAccuracyField)
    override val horizontalAccuracy: Meters? = null,
    @SerialName(headingField)
    override val heading: Degrees? = null,
    @SerialName(proximityAlertRadiusField)
    override val proximityAlertRadius: Meters? = null,
    @SerialName(messageThreadIdField)
    override val threadId: MessageThreadId? = chatId.threadId,
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(messageEffectIdField)
    override val effectId: EffectId? = null,
    @SerialName(replyParametersField)
    override val replyParameters: ReplyParameters? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendContentMessageRequest<ContentMessage<LocationContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<LocationContent>>,
    PositionedSendMessageRequest<ContentMessage<LocationContent>>,
    HorizontallyAccured,
    Livable,
    ProximityAlertable,
    Headed
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
        if (horizontalAccuracy != null && horizontalAccuracy !in horizontalAccuracyLimit) {
            throwRangeError("horizontalAccuracy", horizontalAccuracyLimit, horizontalAccuracy)
        }
    }
}
