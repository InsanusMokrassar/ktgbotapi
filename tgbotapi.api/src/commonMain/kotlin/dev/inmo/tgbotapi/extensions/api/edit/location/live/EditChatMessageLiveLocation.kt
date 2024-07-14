package dev.inmo.tgbotapi.extensions.api.edit.location.live

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.location.live.EditChatMessageLiveLocation
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.location.LiveLocation
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.LocationContent

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editLiveLocation(
    chatId: ChatIdentifier,
    messageId: MessageId,
    latitude: Double,
    longitude: Double,
    livePeriod: Seconds? = null,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<LocationContent> = execute(
    EditChatMessageLiveLocation(
        chatId, messageId, latitude, longitude, livePeriod, horizontalAccuracy, heading, proximityAlertRadius, businessConnectionId, replyMarkup
    )
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editLiveLocation(
    chat: Chat,
    messageId: MessageId,
    latitude: Double,
    longitude: Double,
    livePeriod: Seconds? = null,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<LocationContent> = editLiveLocation(chat.id, messageId, latitude, longitude, livePeriod, horizontalAccuracy, heading, proximityAlertRadius, businessConnectionId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editLiveLocation(
    message: ContentMessage<LocationContent>,
    latitude: Double,
    longitude: Double,
    livePeriod: Seconds? = null,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<LocationContent> = editLiveLocation(message.chat, message.messageId, latitude, longitude, livePeriod, horizontalAccuracy, heading, proximityAlertRadius, businessConnectionId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editLiveLocation(
    chatId: ChatIdentifier,
    messageId: MessageId,
    location: LiveLocation,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<LocationContent> = execute(
    EditChatMessageLiveLocation(
        chatId, messageId, location.latitude, location.longitude, location.livePeriod, location.horizontalAccuracy, location.heading, location.proximityAlertRadius, businessConnectionId, replyMarkup
    )
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editLiveLocation(
    chat: Chat,
    messageId: MessageId,
    location: LiveLocation,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<LocationContent> = editLiveLocation(chat.id, messageId, location.latitude, location.longitude, location.livePeriod, location.horizontalAccuracy, location.heading, location.proximityAlertRadius, businessConnectionId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editLiveLocation(
    message: ContentMessage<LocationContent>,
    location: LiveLocation,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<LocationContent> = editLiveLocation(message.chat, message.messageId, location.latitude, location.longitude, location.livePeriod, location.horizontalAccuracy, location.heading, location.proximityAlertRadius, businessConnectionId, replyMarkup)
