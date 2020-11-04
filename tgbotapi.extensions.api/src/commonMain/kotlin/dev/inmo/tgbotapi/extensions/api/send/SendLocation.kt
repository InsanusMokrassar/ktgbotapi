package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendLocation
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.location.StaticLocation
import dev.inmo.tgbotapi.types.message.abstracts.Message

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
    location: StaticLocation,
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
    location: StaticLocation,
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

suspend inline fun TelegramBot.reply(
    to: Message,
    latitude: Double,
    longitude: Double,
    disableNotification: Boolean = false,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    to.chat,
    latitude,
    longitude,
    disableNotification,
    to.messageId,
    replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: Message,
    location: StaticLocation,
    disableNotification: Boolean = false,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    to.chat,
    location,
    disableNotification,
    to.messageId,
    replyMarkup
)
