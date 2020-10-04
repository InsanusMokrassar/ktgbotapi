package dev.inmo.tgbotapi.extensions.api.edit.LiveLocation

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.LiveLocation.EditChatMessageLiveLocation
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.LocationContent

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
    location: Location,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditChatMessageLiveLocation(
        chatId, messageId, location.latitude, location.longitude, replyMarkup
    )
)

suspend fun TelegramBot.editLiveLocation(
    chat: Chat,
    messageId: MessageIdentifier,
    location: Location,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(chat.id, messageId, location.latitude, location.longitude, replyMarkup)

suspend fun TelegramBot.editLiveLocation(
    message: ContentMessage<LocationContent>,
    location: Location,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(message.chat, message.messageId, location.latitude, location.longitude, replyMarkup)
