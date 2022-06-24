package dev.inmo.tgbotapi.extensions.api.edit.common

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.edit.location.live.editLiveLocation
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.location.LiveLocation
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.LocationContent

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    latitude: Double,
    longitude: Double,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(chatId, messageId, latitude, longitude, horizontalAccuracy, heading, proximityAlertRadius, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    chat: Chat,
    messageId: MessageIdentifier,
    latitude: Double,
    longitude: Double,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(chat, messageId, latitude, longitude, horizontalAccuracy, heading, proximityAlertRadius, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    message: ContentMessage<LocationContent>,
    latitude: Double,
    longitude: Double,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(message, latitude, longitude, horizontalAccuracy, heading, proximityAlertRadius, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    location: LiveLocation,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(
    chatId, messageId, location, replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    chat: Chat,
    messageId: MessageIdentifier,
    location: LiveLocation,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(chat, messageId, location, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    message: ContentMessage<LocationContent>,
    location: LiveLocation,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(message, location, replyMarkup)
