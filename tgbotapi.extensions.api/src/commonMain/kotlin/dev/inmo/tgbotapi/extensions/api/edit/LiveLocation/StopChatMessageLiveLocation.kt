package dev.inmo.tgbotapi.extensions.api.edit.LiveLocation

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.LiveLocation.StopChatMessageLiveLocation
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.LocationContent

suspend fun TelegramBot.stopLiveLocation(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    StopChatMessageLiveLocation(
        chatId, messageId, replyMarkup
    )
)

suspend fun TelegramBot.stopLiveLocation(
    chat: Chat,
    messageId: MessageIdentifier,
    replyMarkup: InlineKeyboardMarkup? = null
) = stopLiveLocation(chat.id, messageId, replyMarkup)

suspend fun TelegramBot.stopLiveLocation(
    message: ContentMessage<LocationContent>,
    replyMarkup: InlineKeyboardMarkup? = null
) = stopLiveLocation(message.chat, message.messageId, replyMarkup)
