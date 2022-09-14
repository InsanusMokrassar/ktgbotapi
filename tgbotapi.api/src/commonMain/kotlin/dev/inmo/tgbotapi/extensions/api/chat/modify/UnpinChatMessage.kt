package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.UnpinChatMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.Message

suspend fun TelegramBot.unpinChatMessage(
    chatId: ChatIdentifier,
    messageId: MessageId? = null
) = execute(UnpinChatMessage(chatId, messageId))

suspend fun TelegramBot.unpinChatMessage(
    chat: Chat,
    messageId: MessageId? = null
) = unpinChatMessage(chat.id, messageId)

suspend fun TelegramBot.unpinChatMessage(
    message: Message
) = unpinChatMessage(message.chat.id, message.messageId)
