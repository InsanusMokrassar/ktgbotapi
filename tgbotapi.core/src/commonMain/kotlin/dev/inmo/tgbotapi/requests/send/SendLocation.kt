package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.abstracts.*
import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.location.LiveLocation
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.utils.throwRangeError
import kotlinx.serialization.*


private val commonResultDeserializer: DeserializationStrategy<ContentMessage<LocationContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

private val liveResultDeserializer: DeserializationStrategy<ContentMessage<LiveLocationContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

private val staticResultDeserializer: DeserializationStrategy<ContentMessage<StaticLocationContent>>
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
) = SendLocation.Static(
    chatId,
    latitude,
    longitude,
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
) = SendLocation.Static(
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
) = SendLocation.Live(
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
sealed interface SendLocation<T : LocationContent> : SendContentMessageRequest<ContentMessage<T>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<T>>,
    PositionedSendMessageRequest<ContentMessage<T>>,
    HorizontallyAccured,
    Livable,
    ProximityAlertable,
    Headed {
    override fun method(): String = "sendLocation"

    @Serializable
    data class Live internal constructor(
        @SerialName(chatIdField)
        override val chatId: ChatIdentifier,
        @SerialName(latitudeField)
        override val latitude: Double,
        @SerialName(longitudeField)
        override val longitude: Double,
        @SerialName(livePeriodField)
        override val livePeriod: Seconds = LiveLocation.INDEFINITE_LIVE_PERIOD,
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
    ) : SendLocation<LiveLocationContent> {
        override val resultDeserializer: DeserializationStrategy<ContentMessage<LiveLocationContent>>
            get() = liveResultDeserializer
        override val requestSerializer: SerializationStrategy<*>
            get() = Live.serializer()

        init {
            if (livePeriod !in livePeriodLimit) {
                error("Live period for sending location must be in $livePeriodLimit, but was $livePeriod")
            }
            if (horizontalAccuracy != null && horizontalAccuracy !in horizontalAccuracyLimit) {
                throwRangeError("horizontalAccuracy", horizontalAccuracyLimit, horizontalAccuracy)
            }
        }
    }

    @Serializable
    data class Static internal constructor(
        @SerialName(chatIdField)
        override val chatId: ChatIdentifier,
        @SerialName(latitudeField)
        override val latitude: Double,
        @SerialName(longitudeField)
        override val longitude: Double,
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
    ) : SendLocation<StaticLocationContent> {
        override val livePeriod: Seconds?
            get() = null
        override val horizontalAccuracy: Meters?
            get() = null
        override val heading: Degrees?
            get() = null
        override val proximityAlertRadius: Meters?
            get() = null
        override val resultDeserializer: DeserializationStrategy<ContentMessage<StaticLocationContent>>
            get() = staticResultDeserializer
        override val requestSerializer: SerializationStrategy<*>
            get() = serializer()
    }
}
