package com.github.insanusmokrassar.TelegramBotAPI.requests

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.LiveLocation.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.SendLocation
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.LocationContent
import com.soywiz.klock.DateTime
import com.soywiz.klock.TimeSpan
import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.*

private val livePeriodDelayDouble = ((livePeriodLimit.last - 60L) * 1000L).toDouble()
class LiveLocation internal constructor(
    private val scope: CoroutineScope,
    private val requestsExecutor: RequestsExecutor,
    initMessage: ContentMessage<LocationContent>
) : Closeable {
    var isClosed: Boolean = false
        private set
    private var autoCloseTime = DateTime.now() + TimeSpan(livePeriodDelayDouble)
    val leftUntilCloseMillis: TimeSpan
        get() = autoCloseTime - DateTime.now()
    private var updateJob: Job? = null
    private var message: ContentMessage<LocationContent> = initMessage
        set(value) {
            field = value
            updateJob ?.cancel()
            updateJob = scope.launch {
                autoCloseTime = DateTime.now() + TimeSpan(livePeriodDelayDouble)
                delay(leftUntilCloseMillis.millisecondsLong)
                updateJob = null
                close()
            }
        }
    val lastLocation: Location
        get() = message.content.location

    init {
        message = initMessage // required to init updateJob
    }

    suspend fun updateLocation(
        location: Location,
        replyMarkup: InlineKeyboardMarkup? = null
    ): Location {
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
        updateJob ?.cancel()
        scope.launch {
            requestsExecutor.stopLiveLocation(message)
        }
    }
}

suspend fun RequestsExecutor.startLiveLocation(
    scope: CoroutineScope,
    chatId: ChatIdentifier,
    latitude: Double,
    longitude: Double,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): LiveLocation {
    val locationMessage = execute(
        SendLocation(
            chatId,
            latitude,
            longitude,
            livePeriodLimit.last.toLong(),
            disableNotification,
            replyToMessageId,
            replyMarkup
        )
    )

    return LiveLocation(
        scope,
        this,
        locationMessage
    )
}
