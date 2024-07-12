package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.PinChatMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage

public suspend fun TelegramBot.pinChatMessage(
    chatId: ChatIdentifier,
    messageId: MessageId,
    disableNotification: Boolean = false
): Boolean = execute(PinChatMessage(chatId, messageId, disableNotification))

public suspend fun TelegramBot.pinChatMessage(
    chat: Chat,
    messageId: MessageId,
    disableNotification: Boolean = false
): Boolean = pinChatMessage(chat.id, messageId, disableNotification)

public suspend fun TelegramBot.pinChatMessage(
    message: AccessibleMessage,
    disableNotification: Boolean = false
): Boolean = pinChatMessage(message.chat.id, message.messageId, disableNotification)
