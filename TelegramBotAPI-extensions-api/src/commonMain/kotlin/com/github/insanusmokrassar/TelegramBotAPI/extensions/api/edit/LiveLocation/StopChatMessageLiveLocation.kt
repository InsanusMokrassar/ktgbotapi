package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.edit.LiveLocation

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.LiveLocation.StopChatMessageLiveLocation
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.LocationContent

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
