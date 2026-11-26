package dev.inmo.tgbotapi.requests.send

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.abstracts.*
import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.location.LiveLocation
import dev.inmo.tgbotapi.types.message.SuggestedPostParameters
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.utils.throwRangeError
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


private val commonResultDeserializer: DeserializationStrategy<ChatContentMessage<LocationContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

private val liveResultDeserializer: DeserializationStrategy<ChatContentMessage<LiveLocationContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

private val staticResultDeserializer: DeserializationStrategy<ChatContentMessage<StaticLocationContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

fun SendLocation(
    chatId: ChatIdentifier,
    latitude: Double,
    longitude: Double,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendLocation.Static(
    chatId = chatId,
    latitude = latitude,
    longitude = longitude,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    receiverUserId = receiverUserId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/** Sends a location as an ephemeral reply to the callback query in [ephemeralMessageParameters]. */
fun SendLocation(
    ephemeralMessageParameters: EphemeralMessageParameters,
    latitude: Double,
    longitude: Double,
    threadId: MessageThreadId? = null,
    directMessageThreadId: DirectMessageThreadId? = null,
    businessConnectionId: BusinessConnectionId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendLocation.Static(
    ephemeralMessageParameters = ephemeralMessageParameters,
    latitude = latitude,
    longitude = longitude,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
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
    receiverUserId: UserId? = chatId.receiverUser,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendLocation.Static(
    chatId = chatId,
    latitude = latitude,
    longitude = longitude,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    receiverUserId = receiverUserId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/** Sends a location as an ephemeral reply to the callback query in [ephemeralMessageParameters]. */
fun SendStaticLocation(
    ephemeralMessageParameters: EphemeralMessageParameters,
    latitude: Double,
    longitude: Double,
    threadId: MessageThreadId? = null,
    directMessageThreadId: DirectMessageThreadId? = null,
    businessConnectionId: BusinessConnectionId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendLocation.Static(
    ephemeralMessageParameters = ephemeralMessageParameters,
    latitude = latitude,
    longitude = longitude,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/**
 * Sends a location with an active [livePeriod]. Use [SendStaticLocation] for an ephemeral location,
 * including a location sent directly to a callback query.
 */
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
    suggestedPostParameters: SuggestedPostParameters? = null,
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
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

@Serializable(SendLocation.Companion::class)
sealed interface SendLocation<T : LocationContent> : SendContentMessageRequest<ChatContentMessage<T>>,
    ReplyingMarkupSendMessageRequest<ChatContentMessage<T>>,
    PositionedSendMessageRequest<ChatContentMessage<T>>,
    HorizontallyAccured,
    Livable,
    ProximityAlertable,
    Headed,
    OptionallyEphemeralSendRequest {
    override fun method(): String = "sendLocation"

    /**
     * A location with active updates for [livePeriod] seconds. Use [Static] for an ephemeral location.
     */
    @Serializable
    data class Live @Warning("Direct use of the primary constructor is discouraged. Use the chat overload.") constructor(
        @SerialName(chatIdField)
        override val chatId: ChatIdentifier? = null,
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
        override val directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
        @SerialName(businessConnectionIdField)
        @OptIn(ExperimentalSerializationApi::class)
        @EncodeDefault
        override val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        @SerialName(ephemeralMessageParametersField)
        override val ephemeralMessageParameters: EphemeralMessageParameters? = chatId.receiverUser ?.let(::EphemeralMessageParameters),
        @SerialName(disableNotificationField)
        override val disableNotification: Boolean = false,
        @SerialName(protectContentField)
        override val protectContent: Boolean = false,
        @SerialName(allowPaidBroadcastField)
        override val allowPaidBroadcast: Boolean = false,
        @SerialName(messageEffectIdField)
        override val effectId: EffectId? = null,
        @SerialName(suggestedPostParametersField)
        override val suggestedPostParameters: SuggestedPostParameters? = null,
        @SerialName(replyParametersField)
        override val replyParameters: ReplyParameters? = null,
        @SerialName(replyMarkupField)
        override val replyMarkup: KeyboardMarkup? = null
    ) : SendLocation<LiveLocationContent> {
        /** Sends a live location to [chatId]. */
        @OptIn(Warning::class)
        constructor(
            chatId: ChatIdentifier,
            latitude: Double,
            longitude: Double,
            livePeriod: Seconds = LiveLocation.INDEFINITE_LIVE_PERIOD,
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
            suggestedPostParameters: SuggestedPostParameters? = null,
            replyParameters: ReplyParameters? = null,
            replyMarkup: KeyboardMarkup? = null
        ) : this(
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
            ephemeralMessageParameters = chatId.receiverUser?.let(::EphemeralMessageParameters),
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup
        )

        override val resultDeserializer: DeserializationStrategy<ChatContentMessage<LiveLocationContent>>
            get() = liveResultDeserializer
        override val requestSerializer: SerializationStrategy<*>
            get() = serializer()

        init {
            requireSendMessageTarget(chatId, ephemeralMessageParameters)
            require(ephemeralMessageParameters == null) { "An ephemeral location must be sent as a static location" }
            if (livePeriod !in livePeriodLimit) {
                error("Live period for sending location must be in $livePeriodLimit, but was $livePeriod")
            }
            if (horizontalAccuracy != null && horizontalAccuracy !in horizontalAccuracyLimit) {
                throwRangeError("horizontalAccuracy", horizontalAccuracyLimit, horizontalAccuracy)
            }
        }
    }

    @Serializable
    data class Static @Warning(sendMessagePrimaryConstructorWarning) constructor(
        @SerialName(chatIdField)
        override val chatId: ChatIdentifier? = null,
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
        @SerialName(ephemeralMessageParametersField)
        override val ephemeralMessageParameters: EphemeralMessageParameters? = chatId.receiverUser ?.let(::EphemeralMessageParameters),
        @SerialName(disableNotificationField)
        override val disableNotification: Boolean = false,
        @SerialName(protectContentField)
        override val protectContent: Boolean = false,
        @SerialName(allowPaidBroadcastField)
        override val allowPaidBroadcast: Boolean = false,
        @SerialName(messageEffectIdField)
        override val effectId: EffectId? = null,
        @SerialName(suggestedPostParametersField)
        override val suggestedPostParameters: SuggestedPostParameters? = null,
        @SerialName(replyParametersField)
        override val replyParameters: ReplyParameters? = null,
        @SerialName(replyMarkupField)
        override val replyMarkup: KeyboardMarkup? = null
    ) : SendLocation<StaticLocationContent> {
        /** Sends to [chatId]. Set [receiverUserId] to deliver an ephemeral message to one user in the chat. */
        @OptIn(Warning::class)
        constructor(
            chatId: ChatIdentifier,
            latitude: Double,
            longitude: Double,
            threadId: MessageThreadId? = chatId.threadId,
            directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
            businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
            receiverUserId: UserId? = chatId.receiverUser,
            disableNotification: Boolean = false,
            protectContent: Boolean = false,
            allowPaidBroadcast: Boolean = false,
            effectId: EffectId? = null,
            suggestedPostParameters: SuggestedPostParameters? = null,
            replyParameters: ReplyParameters? = null,
            replyMarkup: KeyboardMarkup? = null
        ) : this(
            chatId = chatId,
            latitude = latitude,
            longitude = longitude,
            threadId = threadId,
            directMessageThreadId = directMessageThreadId,
            businessConnectionId = businessConnectionId,
            ephemeralMessageParameters = receiverUserId?.let(::EphemeralMessageParameters),
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup
        )

        /** Creates an ephemeral reply to the callback query in [ephemeralMessageParameters]. */
        @OptIn(Warning::class)
        constructor(
            ephemeralMessageParameters: EphemeralMessageParameters,
            latitude: Double,
            longitude: Double,
            threadId: MessageThreadId? = null,
            directMessageThreadId: DirectMessageThreadId? = null,
            businessConnectionId: BusinessConnectionId? = null,
            disableNotification: Boolean = false,
            protectContent: Boolean = false,
            allowPaidBroadcast: Boolean = false,
            effectId: EffectId? = null,
            suggestedPostParameters: SuggestedPostParameters? = null,
            replyParameters: ReplyParameters? = null,
            replyMarkup: KeyboardMarkup? = null
        ) : this(
            chatId = null,
            latitude = latitude,
            longitude = longitude,
            threadId = threadId,
            directMessageThreadId = directMessageThreadId,
            businessConnectionId = businessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup
        )

        init {
            requireSendMessageTarget(chatId, ephemeralMessageParameters)
        }

        override val livePeriod: Seconds?
            get() = null
        override val horizontalAccuracy: Meters?
            get() = null
        override val heading: Degrees?
            get() = null
        override val proximityAlertRadius: Meters?
            get() = null
        override val resultDeserializer: DeserializationStrategy<ChatContentMessage<StaticLocationContent>>
            get() = staticResultDeserializer
        override val requestSerializer: SerializationStrategy<*>
            get() = serializer()
    }

    companion object : KSerializer<SendLocation<*>> {
        @Serializable
        private class Surrogate(
            @SerialName(chatIdField)
            val chatId: ChatIdentifier? = null,
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
            @SerialName(ephemeralMessageParametersField)
            val ephemeralMessageParameters: EphemeralMessageParameters? = chatId.receiverUser ?.let(::EphemeralMessageParameters),
            @SerialName(disableNotificationField)
            val disableNotification: Boolean = false,
            @SerialName(protectContentField)
            val protectContent: Boolean = false,
            @SerialName(allowPaidBroadcastField)
            val allowPaidBroadcast: Boolean = false,
            @SerialName(messageEffectIdField)
            val effectId: EffectId? = null,
            @SerialName(suggestedPostParametersField)
            val suggestedPostParameters: SuggestedPostParameters? = null,
            @SerialName(replyParametersField)
            val replyParameters: ReplyParameters? = null,
            @SerialName(replyMarkupField)
            val replyMarkup: KeyboardMarkup? = null
        )

        override val descriptor: SerialDescriptor
            get() = Surrogate.serializer().descriptor

        @OptIn(Warning::class)
        override fun deserialize(decoder: Decoder): SendLocation<*> {
            val surrogate = Surrogate.serializer().deserialize(decoder)

            return when (surrogate.livePeriod) {
                null, 0 -> Static(
                    chatId = surrogate.chatId,
                    latitude = surrogate.latitude,
                    longitude = surrogate.longitude,
                    threadId = surrogate.threadId,
                    directMessageThreadId = surrogate.directMessageThreadId,
                    businessConnectionId = surrogate.businessConnectionId,
                    ephemeralMessageParameters = surrogate.ephemeralMessageParameters,
                    disableNotification = surrogate.disableNotification,
                    protectContent = surrogate.protectContent,
                    allowPaidBroadcast = surrogate.allowPaidBroadcast,
                    effectId = surrogate.effectId,
                    suggestedPostParameters = surrogate.suggestedPostParameters,
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
                    ephemeralMessageParameters = surrogate.ephemeralMessageParameters,
                    disableNotification = surrogate.disableNotification,
                    protectContent = surrogate.protectContent,
                    allowPaidBroadcast = surrogate.allowPaidBroadcast,
                    effectId = surrogate.effectId,
                    suggestedPostParameters = surrogate.suggestedPostParameters,
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
                    ephemeralMessageParameters = ephemeralMessageParameters,
                    disableNotification = disableNotification,
                    protectContent = protectContent,
                    allowPaidBroadcast = allowPaidBroadcast,
                    effectId = effectId,
                    suggestedPostParameters = suggestedPostParameters,
                    replyParameters = replyParameters,
                    replyMarkup = replyMarkup
                )
            }
            Surrogate.serializer().serialize(encoder, surrogate)
        }
    }
}
