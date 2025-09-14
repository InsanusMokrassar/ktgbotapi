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
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


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
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendLocation.Static(
    chatId = chatId,
    latitude = latitude,
    longitude = longitude,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

fun SendStaticLocation(
    chatId: ChatIdentifier,
    latitude: Double,
    longitude: Double,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendLocation.Static(
    chatId = chatId,
    latitude = latitude,
    longitude = longitude,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
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
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
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
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

@Serializable(SendLocation.Companion::class)
sealed interface SendLocation<T : LocationContent> : SendContentMessageRequest<ContentMessage<T>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<T>>,
    PositionedSendMessageRequest<ContentMessage<T>>,
    HorizontallyAccured,
    Livable,
    ProximityAlertable,
    Headed {
    override fun method(): String = "sendLocation"

    @Serializable
    data class Live (
        @SerialName(chatIdField)
        override val chatId: ChatIdentifier,
        @SerialName(latitudeField)
        override val latitude: Double,
        @SerialName(longitudeField)
        override val longitude: Double,
        @SerialName(livePeriodField)
        @OptIn(ExperimentalSerializationApi::class)
        @EncodeDefault
        override val livePeriod: Seconds = LiveLocation.INDEFINITE_LIVE_PERIOD,
        @SerialName(horizontalAccuracyField)
        override val horizontalAccuracy: Meters? = null,
        @SerialName(headingField)
        override val heading: Degrees? = null,
        @SerialName(proximityAlertRadiusField)
        override val proximityAlertRadius: Meters? = null,
        @OptIn(ExperimentalSerializationApi::class)
        @SerialName(messageThreadIdField)
        @EncodeDefault
        override val threadId: MessageThreadId? = chatId.threadId,
        @OptIn(ExperimentalSerializationApi::class)
        @EncodeDefault
        @SerialName(directMessagesTopicIdField)
        override val directMessageThreadId: DirectMessageThreadId?,// = chatId.directMessageThreadId
        @SerialName(businessConnectionIdField)
        @OptIn(ExperimentalSerializationApi::class)
        @EncodeDefault
        override val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        @SerialName(disableNotificationField)
        override val disableNotification: Boolean = false,
        @SerialName(protectContentField)
        override val protectContent: Boolean = false,
        @SerialName(allowPaidBroadcastField)
        override val allowPaidBroadcast: Boolean = false,
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
            get() = serializer()

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
    data class Static (
        @SerialName(chatIdField)
        override val chatId: ChatIdentifier,
        @SerialName(latitudeField)
        override val latitude: Double,
        @SerialName(longitudeField)
        override val longitude: Double,
        @SerialName(messageThreadIdField)
        @OptIn(ExperimentalSerializationApi::class)
        @EncodeDefault
        override val threadId: MessageThreadId? = chatId.threadId,
        @OptIn(ExperimentalSerializationApi::class)
        @EncodeDefault
        @SerialName(directMessagesTopicIdField)
        override val directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
        @SerialName(businessConnectionIdField)
        @OptIn(ExperimentalSerializationApi::class)
        @EncodeDefault
        override val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        @SerialName(disableNotificationField)
        override val disableNotification: Boolean = false,
        @SerialName(protectContentField)
        override val protectContent: Boolean = false,
        @SerialName(allowPaidBroadcastField)
        override val allowPaidBroadcast: Boolean = false,
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

    companion object : KSerializer<SendLocation<*>> {
        @Serializable
        private class Surrogate(
            @SerialName(chatIdField)
            val chatId: ChatIdentifier,
            @SerialName(latitudeField)
            val latitude: Double,
            @SerialName(longitudeField)
            val longitude: Double,
            @SerialName(livePeriodField)
            val livePeriod: Seconds? = null,
            @SerialName(horizontalAccuracyField)
            val horizontalAccuracy: Meters? = null,
            @SerialName(headingField)
            val heading: Degrees? = null,
            @SerialName(proximityAlertRadiusField)
            val proximityAlertRadius: Meters? = null,
            @SerialName(messageThreadIdField)
            val threadId: MessageThreadId? = chatId.threadId,
            @SerialName(directMessagesTopicIdField)
            val directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
            @SerialName(businessConnectionIdField)
            val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
            @SerialName(disableNotificationField)
            val disableNotification: Boolean = false,
            @SerialName(protectContentField)
            val protectContent: Boolean = false,
            @SerialName(allowPaidBroadcastField)
            val allowPaidBroadcast: Boolean = false,
            @SerialName(messageEffectIdField)
            val effectId: EffectId? = null,
            @SerialName(replyParametersField)
            val replyParameters: ReplyParameters? = null,
            @SerialName(replyMarkupField)
            val replyMarkup: KeyboardMarkup? = null
        )

        override val descriptor: SerialDescriptor
            get() = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): SendLocation<*> {
            val surrogate = Surrogate.serializer().deserialize(decoder)

            return when (surrogate.livePeriod) {
                null -> Static(
                    chatId = surrogate.chatId,
                    latitude = surrogate.latitude,
                    longitude = surrogate.longitude,
                    threadId = surrogate.threadId,
                    directMessageThreadId = surrogate.directMessageThreadId,
                    businessConnectionId = surrogate.businessConnectionId,
                    disableNotification = surrogate.disableNotification,
                    protectContent = surrogate.protectContent,
                    allowPaidBroadcast = surrogate.allowPaidBroadcast,
                    effectId = surrogate.effectId,
                    replyParameters = surrogate.replyParameters,
                    replyMarkup = surrogate.replyMarkup
                )
                else -> Live(
                    chatId = surrogate.chatId,
                    latitude = surrogate.latitude,
                    longitude = surrogate.longitude,
                    livePeriod = surrogate.livePeriod,
                    horizontalAccuracy = surrogate.horizontalAccuracy,
                    heading = surrogate.heading,
                    proximityAlertRadius = surrogate.proximityAlertRadius,
                    threadId = surrogate.threadId,
                    directMessageThreadId = surrogate.directMessageThreadId,
                    businessConnectionId = surrogate.businessConnectionId,
                    disableNotification = surrogate.disableNotification,
                    protectContent = surrogate.protectContent,
                    allowPaidBroadcast = surrogate.allowPaidBroadcast,
                    effectId = surrogate.effectId,
                    replyParameters = surrogate.replyParameters,
                    replyMarkup = surrogate.replyMarkup
                )
            }
        }

        override fun serialize(encoder: Encoder, value: SendLocation<*>) {
            val surrogate = with(value) {
                Surrogate(
                    chatId = chatId,
                    latitude = latitude,
                    longitude = longitude,
                    livePeriod = livePeriod,
                    horizontalAccuracy = horizontalAccuracy,
                    heading = heading,
                    proximityAlertRadius = proximityAlertRadius,
                    threadId = threadId,
                    directMessageThreadId = directMessageThreadId,
                    businessConnectionId = businessConnectionId,
                    disableNotification = disableNotification,
                    protectContent = protectContent,
                    allowPaidBroadcast = allowPaidBroadcast,
                    effectId = effectId,
                    replyParameters = replyParameters,
                    replyMarkup = replyMarkup
                )
            }
            Surrogate.serializer().serialize(encoder, surrogate)
        }
    }
}
