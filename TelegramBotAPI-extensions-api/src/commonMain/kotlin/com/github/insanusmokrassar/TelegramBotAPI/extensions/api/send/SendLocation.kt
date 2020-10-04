package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.send

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.SendLocation
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat

suspend fun TelegramBot.sendLocation(
    chatId: ChatIdentifier,
    latitude: Double,
    longitude: Double,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendLocation(
        chatId,
        latitude,
        longitude,
        disableNotification = disableNotification,
        replyToMessageId = replyToMessageId,
        replyMarkup = replyMarkup
    )
)

suspend fun TelegramBot.sendLocation(
    chatId: ChatIdentifier,
    location: Location,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    chatId,
    location.latitude,
    location.longitude,
    disableNotification,
    replyToMessageId,
    replyMarkup
)

suspend fun TelegramBot.sendLocation(
    chat: Chat,
    latitude: Double,
    longitude: Double,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    chat.id,
    latitude,
    longitude,
    disableNotification,
    replyToMessageId,
    replyMarkup
)

suspend fun TelegramBot.sendLocation(
    chat: Chat,
    location: Location,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    chat.id,
    location.latitude,
    location.longitude,
    disableNotification,
    replyToMessageId,
    replyMarkup
)
