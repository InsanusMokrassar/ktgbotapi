package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.PinChatMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.chat.abstracts.PublicChat
import dev.inmo.tgbotapi.types.message.abstracts.Message

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
