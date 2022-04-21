package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.SetChatDescription
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.PublicChat

suspend fun TelegramBot.setChatDescription(
    chatId: ChatIdentifier,
    description: String
) = execute(SetChatDescription(chatId, description))

suspend fun TelegramBot.setChatDescription(
    chat: PublicChat,
    description: String
) = setChatDescription(chat.id, description)
