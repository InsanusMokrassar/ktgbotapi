package dev.inmo.tgbotapi.extensions.api

import korlibs.time.DateTime
import korlibs.time.TimeSpan
import dev.inmo.micro_utils.coroutines.LinkedSupervisorJob
import dev.inmo.micro_utils.coroutines.launchSafelyWithoutExceptions
import dev.inmo.tgbotapi.abstracts.types.WithReplyMarkup
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.edit.edit
import dev.inmo.tgbotapi.extensions.api.edit.location.live.editLiveLocation
import dev.inmo.tgbotapi.extensions.api.edit.location.live.stopLiveLocation
import dev.inmo.tgbotapi.extensions.api.send.send
import dev.inmo.tgbotapi.extensions.api.send.sendLiveLocation
import dev.inmo.tgbotapi.requests.send.SendLiveLocation
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.location.LiveLocation
import dev.inmo.tgbotapi.types.location.Location
import dev.inmo.tgbotapi.types.location.StaticLocation
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.content.LocationContent
import dev.inmo.tgbotapi.utils.extensions.threadIdOrNull
import io.ktor.utils.io.core.Closeable
import korlibs.time.millisecondsLong
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.js.JsName
import kotlin.jvm.JvmName
import kotlin.math.ceil

val defaultLivePeriodDelayMillis = (livePeriodLimit.last - 60L) * 1000L

/**
 * @see startLiveLocation
 */
class LiveLocationProvider internal constructor(
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
    val leftUntilCloseMillis: TimeSpan
        get() = autoCloseTime - DateTime.now()

    var isClosed: Boolean = false
        private set
        get() = field || leftUntilCloseMillis.millisecondsLong < 0L

    var message: ContentMessage<LocationContent> = initMessage
        private set
    val lastLocation: LiveLocation
        get() = message.content.location as LiveLocation

    /**
     * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
     * as a builder for that
     */
    suspend fun updateLocation(
        location: LiveLocation,
        replyMarkup: InlineKeyboardMarkup? = null
    ): LiveLocation {
        if (!isClosed) {
            message = requestsExecutor.editLiveLocation(
                message,
                location,
                replyMarkup
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
suspend fun TelegramBot.startLiveLocation(
    scope: CoroutineScope,
    chatId: ChatIdentifier,
    latitude: Double,
    longitude: Double,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    initHorizontalAccuracy: Meters? = null,
    initHeading: Degrees? = null,
    initProximityAlertRadius: Meters? = null,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): LiveLocationProvider {
    val liveTimeAsDouble = liveTimeMillis.toDouble()
    val locationMessage = execute(
        SendLiveLocation(
            chatId,
            latitude,
            longitude,
            ceil(liveTimeAsDouble / 1000).toInt(),
            initHorizontalAccuracy,
            initHeading,
            initProximityAlertRadius,
            threadId,
            disableNotification,
            protectContent,
            replyToMessageId,
            allowSendingWithoutReply,
            replyMarkup
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
suspend fun TelegramBot.startLiveLocation(
    scope: CoroutineScope,
    chat: Chat,
    latitude: Double,
    longitude: Double,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    initHorizontalAccuracy: Meters? = null,
    initHeading: Degrees? = null,
    initProximityAlertRadius: Meters? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): LiveLocationProvider = startLiveLocation(
    scope,
    chat.id,
    latitude,
    longitude,
    liveTimeMillis,
    initHorizontalAccuracy,
    initHeading,
    initProximityAlertRadius,
    threadId,
    disableNotification,
    protectContent,
    replyToMessageId,
    allowSendingWithoutReply,
    replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.startLiveLocation(
    scope: CoroutineScope,
    chatId: IdChatIdentifier,
    location: StaticLocation,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    initHorizontalAccuracy: Meters? = null,
    initHeading: Degrees? = null,
    initProximityAlertRadius: Meters? = null,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): LiveLocationProvider = startLiveLocation(
    scope,
    chatId,
    location.latitude,
    location.longitude,
    liveTimeMillis,
    initHorizontalAccuracy,
    initHeading,
    initProximityAlertRadius,
    threadId,
    disableNotification,
    protectContent,
    replyToMessageId,
    allowSendingWithoutReply,
    replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.startLiveLocation(
    scope: CoroutineScope,
    chat: Chat,
    location: StaticLocation,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    initHorizontalAccuracy: Meters? = null,
    initHeading: Degrees? = null,
    initProximityAlertRadius: Meters? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): LiveLocationProvider = startLiveLocation(
    scope,
    chat.id,
    location.latitude,
    location.longitude,
    liveTimeMillis,
    initHorizontalAccuracy,
    initHeading,
    initProximityAlertRadius,
    threadId,
    disableNotification,
    protectContent,
    replyToMessageId,
    allowSendingWithoutReply,
    replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.replyWithLiveLocation(
    to: Message,
    scope: CoroutineScope,
    latitude: Double,
    longitude: Double,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    initHorizontalAccuracy: Meters? = null,
    initHeading: Degrees? = null,
    initProximityAlertRadius: Meters? = null,
    threadId: MessageThreadId? = to.threadIdOrNull,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = startLiveLocation(
    scope,
    to.chat,
    latitude,
    longitude,
    liveTimeMillis,
    initHorizontalAccuracy,
    initHeading,
    initProximityAlertRadius,
    threadId,
    disableNotification,
    protectContent,
    to.messageId,
    allowSendingWithoutReply,
    replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.replyWithLiveLocation(
    to: Message,
    scope: CoroutineScope,
    location: StaticLocation,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    initHorizontalAccuracy: Meters? = null,
    initHeading: Degrees? = null,
    initProximityAlertRadius: Meters? = null,
    threadId: MessageThreadId? = to.threadIdOrNull,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = startLiveLocation(
    scope,
    to.chat,
    location,
    liveTimeMillis,
    initHorizontalAccuracy,
    initHeading,
    initProximityAlertRadius,
    threadId,
    disableNotification,
    protectContent,
    to.messageId,
    allowSendingWithoutReply,
    replyMarkup
)
