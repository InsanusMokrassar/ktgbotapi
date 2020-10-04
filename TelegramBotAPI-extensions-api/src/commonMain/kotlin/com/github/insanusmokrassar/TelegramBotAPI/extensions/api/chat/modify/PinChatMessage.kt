package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify.PinChatMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message

suspend fun TelegramBot.pinChatMessage(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    disableNotification: Boolean = false
) = execute(PinChatMessage(chatId, messageId, disableNotification))

suspend fun TelegramBot.pinChatMessage(
    chat: PublicChat,
    messageId: MessageIdentifier,
    disableNotification: Boolean = false
) = pinChatMessage(chat.id, messageId, disableNotification)

suspend fun TelegramBot.pinChatMessage(
    message: Message,
    disableNotification: Boolean = false
) = if (message.chat is PublicChat) {
    pinChatMessage(message.chat.id, message.messageId, disableNotification)
} else {
    error("It is possible to pin messages only in non one-to-one chats")
}
