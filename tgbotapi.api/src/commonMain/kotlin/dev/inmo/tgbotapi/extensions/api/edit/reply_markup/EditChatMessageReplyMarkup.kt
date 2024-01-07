package dev.inmo.tgbotapi.extensions.api.edit.reply_markup

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.reply_markup.EditChatMessageReplyMarkup
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageReplyMarkup(
    chatId: ChatIdentifier,
    messageId: MessageId,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditChatMessageReplyMarkup(chatId, messageId, replyMarkup)
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageReplyMarkup(
    chat: Chat,
    messageId: MessageId,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageReplyMarkup(chat.id, messageId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageReplyMarkup(
    message: AccessibleMessage,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageReplyMarkup(message.chat.id, message.messageId, replyMarkup)

