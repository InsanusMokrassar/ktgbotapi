package dev.inmo.tgbotapi.extensions.api.edit.LiveLocation

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.LiveLocation.EditChatMessageLiveLocation
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.location.StaticLocation
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.LocationContent

suspend fun TelegramBot.editLiveLocation(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    latitude: Double,
    longitude: Double,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditChatMessageLiveLocation(
        chatId, messageId, latitude, longitude, replyMarkup
    )
)

suspend fun TelegramBot.editLiveLocation(
    chat: Chat,
    messageId: MessageIdentifier,
    latitude: Double,
    longitude: Double,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(chat.id, messageId, latitude, longitude, replyMarkup)

suspend fun TelegramBot.editLiveLocation(
    message: ContentMessage<LocationContent>,
    latitude: Double,
    longitude: Double,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(message.chat, message.messageId, latitude, longitude, replyMarkup)

suspend fun TelegramBot.editLiveLocation(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    location: StaticLocation,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditChatMessageLiveLocation(
        chatId, messageId, location.latitude, location.longitude, replyMarkup
    )
)

suspend fun TelegramBot.editLiveLocation(
    chat: Chat,
    messageId: MessageIdentifier,
    location: StaticLocation,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(chat.id, messageId, location.latitude, location.longitude, replyMarkup)

suspend fun TelegramBot.editLiveLocation(
    message: ContentMessage<LocationContent>,
    location: StaticLocation,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(message.chat, message.messageId, location.latitude, location.longitude, replyMarkup)
