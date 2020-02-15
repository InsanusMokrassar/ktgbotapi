package com.github.insanusmokrassar.TelegramBotAPI.extensions.api

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.StopPoll
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message

suspend fun RequestsExecutor.stopPoll(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    StopPoll(chatId, messageId, replyMarkup)
)

suspend fun RequestsExecutor.stopPoll(
    chat: Chat,
    messageId: MessageIdentifier,
    replyMarkup: InlineKeyboardMarkup? = null
) = stopPoll(chat.id, messageId, replyMarkup)

suspend fun RequestsExecutor.stopPoll(
    chatId: ChatId,
    message: Message,
    replyMarkup: InlineKeyboardMarkup? = null
) = stopPoll(chatId, message.messageId, replyMarkup)

suspend fun RequestsExecutor.stopPoll(
    chat: Chat,
    message: Message,
    replyMarkup: InlineKeyboardMarkup? = null
) = stopPoll(chat.id, message.messageId, replyMarkup)
