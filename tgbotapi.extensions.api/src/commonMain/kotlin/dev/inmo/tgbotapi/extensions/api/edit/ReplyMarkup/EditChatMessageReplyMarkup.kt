package dev.inmo.tgbotapi.extensions.api.edit.ReplyMarkup

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.ReplyMarkup.EditChatMessageReplyMarkup
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.message.abstracts.Message

suspend fun TelegramBot.editMessageReplyMarkup(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditChatMessageReplyMarkup(chatId, messageId, replyMarkup)
)

suspend fun TelegramBot.editMessageReplyMarkup(
    chat: Chat,
    messageId: MessageIdentifier,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageReplyMarkup(chat.id, messageId, replyMarkup)

suspend fun TelegramBot.editMessageReplyMarkup(
    message: Message,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageReplyMarkup(message.chat.id, message.messageId, replyMarkup)

