package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.StopPoll
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.stopPoll(
    chatId: ChatIdentifier,
    messageId: MessageId,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    StopPoll(chatId, messageId, replyMarkup)
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.stopPoll(
    chat: Chat,
    messageId: MessageId,
    replyMarkup: InlineKeyboardMarkup? = null
) = stopPoll(chat.id, messageId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.stopPoll(
    chatId: IdChatIdentifier,
    message: AccessibleMessage,
    replyMarkup: InlineKeyboardMarkup? = null
) = stopPoll(chatId, message.messageId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.stopPoll(
    chat: Chat,
    message: AccessibleMessage,
    replyMarkup: InlineKeyboardMarkup? = null
) = stopPoll(chat.id, message.messageId, replyMarkup)
