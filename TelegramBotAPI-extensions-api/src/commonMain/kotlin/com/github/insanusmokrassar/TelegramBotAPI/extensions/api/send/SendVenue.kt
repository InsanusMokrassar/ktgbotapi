package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.send

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.SendVenue
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.venue.Venue

suspend fun RequestsExecutor.sendVenue(
    chatId: ChatIdentifier,
    latitude: Double,
    longitude: Double,
    title: String,
    address: String,
    foursquareId: String? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendVenue(
        chatId, latitude, longitude, title, address, foursquareId, disableNotification, replyToMessageId, replyMarkup
    )
)

suspend fun RequestsExecutor.sendVenue(
    chat: Chat,
    latitude: Double,
    longitude: Double,
    title: String,
    address: String,
    foursquareId: String? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(
    chat.id, latitude, longitude, title, address, foursquareId, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.sendVenue(
    chatId: ChatIdentifier,
    venue: Venue,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendVenue(
        chatId, venue, disableNotification, replyToMessageId, replyMarkup
    )
)

suspend fun RequestsExecutor.sendVenue(
    chat: Chat,
    venue: Venue,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(
    chat.id, venue, disableNotification, replyToMessageId, replyMarkup
)
