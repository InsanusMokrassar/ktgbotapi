package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.UnpinChatMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.message.abstracts.Message

suspend fun TelegramBot.unpinChatMessage(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier? = null
) = execute(UnpinChatMessage(chatId, messageId))

suspend fun TelegramBot.unpinChatMessage(
    chat: Chat,
    messageId: MessageIdentifier? = null
) = unpinChatMessage(chat.id, messageId)

suspend fun TelegramBot.unpinChatMessage(
    message: Message
) = unpinChatMessage(message.chat.id, message.messageId)
