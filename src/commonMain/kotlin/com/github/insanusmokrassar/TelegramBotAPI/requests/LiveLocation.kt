package com.github.insanusmokrassar.TelegramBotAPI.requests

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.LiveLocation.EditChatMessageLiveLocation
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.LiveLocation.StopChatMessageLiveLocation
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.SendLocation
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.soywiz.klock.DateTime
import com.soywiz.klock.TimeSpan
import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.*

private val livePeriodDelayDouble = ((livePeriodLimit.last - 60L) * 1000L).toDouble()
class LiveLocation internal constructor(
    private val scope: CoroutineScope,
    private val requestsExecutor: RequestsExecutor,
    private val chatId: ChatIdentifier,
    private val messageId: MessageIdentifier,
    location: Location
) : Closeable {
    var isClosed: Boolean = false
        private set
    private var autoCloseTime = DateTime.now() + TimeSpan(livePeriodDelayDouble)
    val leftUntilCloseMillis: TimeSpan
        get() = autoCloseTime - DateTime.now()
    private var updateJob: Job? = null
    var lastLocation: Location = location
        private set(value) {
            field = value
            updateJob ?.cancel()
            updateJob = scope.launch {
                autoCloseTime = DateTime.now() + TimeSpan(livePeriodDelayDouble)
                delay(leftUntilCloseMillis.millisecondsLong)
                updateJob = null
                close()
            }
        }

    init {
        this.lastLocation = location // required to init updateJob
    }

    suspend fun updateLocation(
        location: Location,
        replyMarkup: InlineKeyboardMarkup? = null
    ): Location {
        if (!isClosed) {
            lastLocation = requestsExecutor.execute(
                EditChatMessageLiveLocation(
                    chatId,
                    messageId,
                    location.latitude,
                    location.longitude,
                    replyMarkup
                )
            ).content.location
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
            requestsExecutor.execute(
                StopChatMessageLiveLocation(
                    chatId,
                    messageId
                )
            )
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
        chatId,
        locationMessage.messageId,
        locationMessage.content.location
    )
}
