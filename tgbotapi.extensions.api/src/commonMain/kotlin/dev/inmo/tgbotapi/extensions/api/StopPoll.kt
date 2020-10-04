package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.StopPoll
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.message.abstracts.Message

suspend fun TelegramBot.stopPoll(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    StopPoll(chatId, messageId, replyMarkup)
)

suspend fun TelegramBot.stopPoll(
    chat: Chat,
    messageId: MessageIdentifier,
    replyMarkup: InlineKeyboardMarkup? = null
) = stopPoll(chat.id, messageId, replyMarkup)

suspend fun TelegramBot.stopPoll(
    chatId: ChatId,
    message: Message,
    replyMarkup: InlineKeyboardMarkup? = null
) = stopPoll(chatId, message.messageId, replyMarkup)

suspend fun TelegramBot.stopPoll(
    chat: Chat,
    message: Message,
    replyMarkup: InlineKeyboardMarkup? = null
) = stopPoll(chat.id, message.messageId, replyMarkup)
