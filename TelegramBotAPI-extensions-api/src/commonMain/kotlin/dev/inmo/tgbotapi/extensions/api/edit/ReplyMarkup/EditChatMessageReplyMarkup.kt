package dev.inmo.tgbotapi.extensions.api.edit.ReplyMarkup

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.ReplyMarkup.EditChatMessageReplyMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message

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

