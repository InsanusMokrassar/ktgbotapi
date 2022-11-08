package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendStaticLocation
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.location.Location

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendLocation(
    chatId: ChatIdentifier,
    latitude: Double,
    longitude: Double,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyToMessageId: MessageId? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendStaticLocation(
        chatId,
        latitude,
        longitude,
        threadId = threadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyToMessageId = replyToMessageId,
        replyMarkup = replyMarkup
    )
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendLocation(
    chatId: ChatIdentifier,
    location: Location,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyToMessageId: MessageId? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    chatId,
    location.latitude,
    location.longitude,
    threadId,
    disableNotification,
    protectContent,
    allowSendingWithoutReply,
    replyToMessageId,
    replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendLocation(
    chat: Chat,
    latitude: Double,
    longitude: Double,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyToMessageId: MessageId? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    chat.id,
    latitude,
    longitude,
    threadId,
    disableNotification,
    protectContent,
    allowSendingWithoutReply,
    replyToMessageId,
    replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendLocation(
    chat: Chat,
    location: Location,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyToMessageId: MessageId? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    chat.id,
    location.latitude,
    location.longitude,
    threadId,
    disableNotification,
    protectContent,
    allowSendingWithoutReply,
    replyToMessageId,
    replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendStaticLocation(
    chatId: ChatIdentifier,
    latitude: Double,
    longitude: Double,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyToMessageId: MessageId? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(chatId, latitude, longitude, threadId, disableNotification, protectContent, allowSendingWithoutReply, replyToMessageId, replyMarkup)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendStaticLocation(
    chatId: ChatIdentifier,
    location: Location,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyToMessageId: MessageId? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(chatId, location.latitude, location.longitude, threadId, disableNotification, protectContent, allowSendingWithoutReply, replyToMessageId, replyMarkup)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendStaticLocation(
    chat: Chat,
    latitude: Double,
    longitude: Double,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyToMessageId: MessageId? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(chat.id, latitude, longitude, threadId, disableNotification, protectContent, allowSendingWithoutReply, replyToMessageId, replyMarkup)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendStaticLocation(
    chat: Chat,
    location: Location,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyToMessageId: MessageId? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(chat.id, location.latitude, location.longitude, threadId, disableNotification, protectContent, allowSendingWithoutReply, replyToMessageId, replyMarkup)
