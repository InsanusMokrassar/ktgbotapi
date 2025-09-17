@file:Suppress("KDocUnresolvedReference")

package dev.inmo.tgbotapi.extensions.api

import korlibs.time.DateTime
import korlibs.time.TimeSpan
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.edit.location.live.editLiveLocation
import dev.inmo.tgbotapi.extensions.api.edit.location.live.stopLiveLocation
import dev.inmo.tgbotapi.requests.send.SendLiveLocation
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.SuggestedPostParameters
import dev.inmo.tgbotapi.types.location.LiveLocation
import dev.inmo.tgbotapi.types.location.StaticLocation
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.content.LocationContent
import dev.inmo.tgbotapi.utils.extensions.directMessageThreadIdOrNull
import dev.inmo.tgbotapi.utils.extensions.threadIdOrNull
import io.ktor.utils.io.core.Closeable
import korlibs.time.millisecondsLong
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.ceil

public const val indefiniteLivePeriodDelayMillis: Long = LiveLocation.INDEFINITE_LIVE_PERIOD * 1000L
public const val defaultLivePeriodDelayMillis: Long = indefiniteLivePeriodDelayMillis

/**
 * @see startLiveLocation
 */
public class LiveLocationProvider internal constructor(
    private val requestsExecutor: TelegramBot,
    scope: CoroutineScope,
    autoCloseTimeDelay: Double,
    initMessage: ContentMessage<LocationContent>
) : Closeable {
    private val doWhenClose = {
        scope.launch {
            requestsExecutor.stopLiveLocation(message)
        }
    }
    private val autoCloseTime = DateTime.now() + TimeSpan(autoCloseTimeDelay)
    public val leftUntilCloseMillis: TimeSpan
        get() = autoCloseTime - DateTime.now()

    public var isClosed: Boolean = false
        private set
        get() = field || leftUntilCloseMillis.millisecondsLong < 0L

    public var message: ContentMessage<LocationContent> = initMessage
        private set
    public val lastLocation: LiveLocation
        get() = message.content.location as LiveLocation

    /**
     * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
     * as a builder for that
     */
    public suspend fun updateLocation(
        location: LiveLocation,
        replyMarkup: InlineKeyboardMarkup? = null
    ): LiveLocation {
        if (!isClosed) {
            message = requestsExecutor.editLiveLocation(
                message,
                location,
                replyMarkup = replyMarkup
            )
            return lastLocation
        } else {
            error("LiveLocation is closed")
        }
    }

    override fun close() {
        if (isClosed) {
            return
        }
        isClosed = true
        doWhenClose()
    }
}

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.startLiveLocation(
    scope: CoroutineScope,
    chatId: ChatIdentifier,
    latitude: Double,
    longitude: Double,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    initHorizontalAccuracy: Meters? = null,
    initHeading: Degrees? = null,
    initProximityAlertRadius: Meters? = null,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters?,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): LiveLocationProvider {
    val liveTimeAsDouble = liveTimeMillis.toDouble()
    val locationMessage = execute(
        SendLiveLocation(
            chatId = chatId,
            latitude = latitude,
            longitude = longitude,
            livePeriod = ceil(liveTimeAsDouble / 1000).toInt(),
            horizontalAccuracy = initHorizontalAccuracy,
            heading = initHeading,
            proximityAlertRadius = initProximityAlertRadius,
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
    )

    return LiveLocationProvider(
        this,
        scope,
        liveTimeAsDouble,
        locationMessage
    )
}

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.startLiveLocation(
    scope: CoroutineScope,
    chat: Chat,
    latitude: Double,
    longitude: Double,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    initHorizontalAccuracy: Meters? = null,
    initHeading: Degrees? = null,
    initProximityAlertRadius: Meters? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters?,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): LiveLocationProvider = startLiveLocation(
    scope = scope,
    chatId = chat.id,
    latitude = latitude,
    longitude = longitude,
    liveTimeMillis = liveTimeMillis,
    initHorizontalAccuracy = initHorizontalAccuracy,
    initHeading = initHeading,
    initProximityAlertRadius = initProximityAlertRadius,
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
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.startLiveLocation(
    scope: CoroutineScope,
    chatId: IdChatIdentifier,
    location: StaticLocation,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    initHorizontalAccuracy: Meters? = null,
    initHeading: Degrees? = null,
    initProximityAlertRadius: Meters? = null,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters?,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): LiveLocationProvider = startLiveLocation(
    scope = scope,
    chatId = chatId,
    latitude = location.latitude,
    longitude = location.longitude,
    liveTimeMillis = liveTimeMillis,
    initHorizontalAccuracy = initHorizontalAccuracy,
    initHeading = initHeading,
    initProximityAlertRadius = initProximityAlertRadius,
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
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.startLiveLocation(
    scope: CoroutineScope,
    chat: Chat,
    location: StaticLocation,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    initHorizontalAccuracy: Meters? = null,
    initHeading: Degrees? = null,
    initProximityAlertRadius: Meters? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters?,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): LiveLocationProvider = startLiveLocation(
    scope = scope,
    chatId = chat.id,
    latitude = location.latitude,
    longitude = location.longitude,
    liveTimeMillis = liveTimeMillis,
    initHorizontalAccuracy = initHorizontalAccuracy,
    initHeading = initHeading,
    initProximityAlertRadius = initProximityAlertRadius,
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
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.replyWithLiveLocation(
    to: AccessibleMessage,
    scope: CoroutineScope,
    latitude: Double,
    longitude: Double,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    initHorizontalAccuracy: Meters? = null,
    initHeading: Degrees? = null,
    initProximityAlertRadius: Meters? = null,
    threadId: MessageThreadId? = to.threadIdOrNull,
    directMessageThreadId: DirectMessageThreadId? = to.directMessageThreadIdOrNull,
    businessConnectionId: BusinessConnectionId? = to.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): LiveLocationProvider = startLiveLocation(
    scope = scope,
    chat = to.chat,
    latitude = latitude,
    longitude = longitude,
    liveTimeMillis = liveTimeMillis,
    initHorizontalAccuracy = initHorizontalAccuracy,
    initHeading = initHeading,
    initProximityAlertRadius = initProximityAlertRadius,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.replyWithLiveLocation(
    to: AccessibleMessage,
    scope: CoroutineScope,
    location: StaticLocation,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    initHorizontalAccuracy: Meters? = null,
    initHeading: Degrees? = null,
    initProximityAlertRadius: Meters? = null,
    threadId: MessageThreadId? = to.threadIdOrNull,
    directMessageThreadId: DirectMessageThreadId? = to.directMessageThreadIdOrNull,
    businessConnectionId: BusinessConnectionId? = to.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): LiveLocationProvider = startLiveLocation(
    scope = scope,
    chat = to.chat,
    location = location,
    liveTimeMillis = liveTimeMillis,
    initHorizontalAccuracy = initHorizontalAccuracy,
    initHeading = initHeading,
    initProximityAlertRadius = initProximityAlertRadius,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)
