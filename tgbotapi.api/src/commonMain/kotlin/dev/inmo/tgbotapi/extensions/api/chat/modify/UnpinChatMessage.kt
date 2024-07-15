package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.UnpinChatMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage

public suspend fun TelegramBot.unpinChatMessage(
    chatId: ChatIdentifier,
    messageId: MessageId? = null
): Boolean = execute(UnpinChatMessage(chatId, messageId))

public suspend fun TelegramBot.unpinChatMessage(
    chat: Chat,
    messageId: MessageId? = null
): Boolean = unpinChatMessage(chat.id, messageId)

public suspend fun TelegramBot.unpinChatMessage(
    message: AccessibleMessage
): Boolean = unpinChatMessage(message.chat.id, message.messageId)
