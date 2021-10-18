package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.PinChatMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.message.abstracts.Message

suspend fun TelegramBot.pinChatMessage(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    disableNotification: Boolean = false
) = execute(PinChatMessage(chatId, messageId, disableNotification))

suspend fun TelegramBot.pinChatMessage(
    chat: Chat,
    messageId: MessageIdentifier,
    disableNotification: Boolean = false
) = pinChatMessage(chat.id, messageId, disableNotification)

suspend fun TelegramBot.pinChatMessage(
    message: Message,
    disableNotification: Boolean = false
) = pinChatMessage(message.chat.id, message.messageId, disableNotification)
