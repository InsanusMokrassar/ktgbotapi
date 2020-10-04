package dev.inmo.tgbotapi.extensions.api

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.DeleteMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message

suspend fun TelegramBot.deleteMessage(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier
) = execute(
    DeleteMessage(chatId, messageId)
)

suspend fun TelegramBot.deleteMessage(
    chat: Chat,
    messageId: MessageIdentifier
) = deleteMessage(chat.id, messageId)

suspend fun TelegramBot.deleteMessage(
    message: Message
) = deleteMessage(message.chat, message.messageId)

suspend fun Message.delete(
    requestsExecutor: TelegramBot
) = requestsExecutor.deleteMessage(this)
