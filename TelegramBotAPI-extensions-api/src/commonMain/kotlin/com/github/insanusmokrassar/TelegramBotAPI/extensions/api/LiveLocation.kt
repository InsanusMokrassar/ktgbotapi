package com.github.insanusmokrassar.TelegramBotAPI.extensions.api

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.LiveLocation
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.SendLocation
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import kotlinx.coroutines.CoroutineScope
import kotlin.math.ceil

private val livePeriodDelayMillis = (livePeriodLimit.last - 60L) * 1000L

suspend fun RequestsExecutor.startLiveLocation(
    scope: CoroutineScope,
    chatId: ChatIdentifier,
    latitude: Double,
    longitude: Double,
    liveTimeMillis: Long = livePeriodDelayMillis,
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

suspend fun RequestsExecutor.startLiveLocation(
    scope: CoroutineScope,
    chat: Chat,
    latitude: Double,
    longitude: Double,
    liveTimeMillis: Long = livePeriodDelayMillis,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): LiveLocation = startLiveLocation(
    scope, chat.id, latitude, longitude, liveTimeMillis, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.startLiveLocation(
    scope: CoroutineScope,
    chatId: ChatId,
    location: Location,
    liveTimeMillis: Long = livePeriodDelayMillis,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): LiveLocation = startLiveLocation(
    scope, chatId, location.latitude, location.longitude, liveTimeMillis, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.startLiveLocation(
    scope: CoroutineScope,
    chat: Chat,
    location: Location,
    liveTimeMillis: Long = livePeriodDelayMillis,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): LiveLocation = startLiveLocation(
    scope, chat.id, location.latitude, location.longitude, liveTimeMillis, disableNotification, replyToMessageId, replyMarkup
)
