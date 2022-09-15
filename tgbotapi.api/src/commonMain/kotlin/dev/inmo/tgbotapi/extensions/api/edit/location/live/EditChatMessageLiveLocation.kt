package dev.inmo.tgbotapi.extensions.api.edit.location.live

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.location.live.EditChatMessageLiveLocation
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
suspend fun TelegramBot.editLiveLocation(
    chatId: ChatIdentifier,
    messageId: MessageId,
    latitude: Double,
    longitude: Double,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditChatMessageLiveLocation(
        chatId, messageId, latitude, longitude, horizontalAccuracy, heading, proximityAlertRadius, replyMarkup
    )
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editLiveLocation(
    chat: Chat,
    messageId: MessageId,
    latitude: Double,
    longitude: Double,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(chat.id, messageId, latitude, longitude, horizontalAccuracy, heading, proximityAlertRadius, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editLiveLocation(
    message: ContentMessage<LocationContent>,
    latitude: Double,
    longitude: Double,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(message.chat, message.messageId, latitude, longitude, horizontalAccuracy, heading, proximityAlertRadius, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editLiveLocation(
    chatId: ChatIdentifier,
    messageId: MessageId,
    location: LiveLocation,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditChatMessageLiveLocation(
        chatId, messageId, location.latitude, location.longitude, location.horizontalAccuracy, location.heading, location.proximityAlertRadius, replyMarkup
    )
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editLiveLocation(
    chat: Chat,
    messageId: MessageId,
    location: LiveLocation,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(chat.id, messageId, location.latitude, location.longitude, location.horizontalAccuracy, location.heading, location.proximityAlertRadius, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editLiveLocation(
    message: ContentMessage<LocationContent>,
    location: LiveLocation,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(message.chat, message.messageId, location.latitude, location.longitude, location.horizontalAccuracy, location.heading, location.proximityAlertRadius, replyMarkup)
