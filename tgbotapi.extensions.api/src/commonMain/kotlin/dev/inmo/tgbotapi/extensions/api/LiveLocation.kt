package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.edit.LiveLocation.editLiveLocation
import dev.inmo.tgbotapi.extensions.api.edit.LiveLocation.stopLiveLocation
import dev.inmo.tgbotapi.requests.send.SendLocation
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.content.LocationContent
import com.soywiz.klock.DateTime
import com.soywiz.klock.TimeSpan
import dev.inmo.tgbotapi.types.location.StaticLocation
import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.ceil

val defaultLivePeriodDelayMillis = (livePeriodLimit.last - 60L) * 1000L
class LiveLocation internal constructor(
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

    private var message: ContentMessage<LocationContent> = initMessage
    val lastLocation: StaticLocation
        get() = message.content.location

    suspend fun updateLocation(
        location: StaticLocation,
        replyMarkup: InlineKeyboardMarkup? = null
    ): StaticLocation {
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

suspend fun TelegramBot.startLiveLocation(
    scope: CoroutineScope,
    chatId: ChatIdentifier,
    latitude: Double,
    longitude: Double,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): LiveLocation {
    val liveTimeAsDouble = liveTimeMillis.toDouble()
    val locationMessage = execute(
        SendLocation(
            chatId,
            latitude,
            longitude,
            ceil(liveTimeAsDouble / 1000).toLong(),
            disableNotification,
            replyToMessageId,
            replyMarkup
        )
    )

    return LiveLocation(
        this,
        scope,
        liveTimeAsDouble,
        locationMessage
    )
}

suspend fun TelegramBot.startLiveLocation(
    scope: CoroutineScope,
    chat: Chat,
    latitude: Double,
    longitude: Double,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): LiveLocation = startLiveLocation(
    scope, chat.id, latitude, longitude, liveTimeMillis, disableNotification, replyToMessageId, replyMarkup
)

suspend fun TelegramBot.startLiveLocation(
    scope: CoroutineScope,
    chatId: ChatId,
    location: StaticLocation,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): LiveLocation = startLiveLocation(
    scope, chatId, location.latitude, location.longitude, liveTimeMillis, disableNotification, replyToMessageId, replyMarkup
)

suspend fun TelegramBot.startLiveLocation(
    scope: CoroutineScope,
    chat: Chat,
    location: StaticLocation,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): LiveLocation = startLiveLocation(
    scope, chat.id, location.latitude, location.longitude, liveTimeMillis, disableNotification, replyToMessageId, replyMarkup
)

suspend inline fun TelegramBot.replyWithLiveLocation(
    to: Message,
    scope: CoroutineScope,
    latitude: Double,
    longitude: Double,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    disableNotification: Boolean = false,
    replyMarkup: KeyboardMarkup? = null
) = startLiveLocation(scope, to.chat, latitude, longitude, liveTimeMillis, disableNotification, to.messageId, replyMarkup)

suspend inline fun TelegramBot.replyWithLiveLocation(
    to: Message,
    scope: CoroutineScope,
    location: StaticLocation,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    disableNotification: Boolean = false,
    replyMarkup: KeyboardMarkup? = null
) = startLiveLocation(scope, to.chat, location, liveTimeMillis, disableNotification, to.messageId, replyMarkup)
