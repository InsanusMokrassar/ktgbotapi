package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.SetChatDescription
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.PublicChat

public suspend fun TelegramBot.setChatDescription(
    chatId: ChatIdentifier,
    description: String
): Boolean = execute(SetChatDescription(chatId, description))

public suspend fun TelegramBot.setChatDescription(
    chat: PublicChat,
    description: String
): Boolean = setChatDescription(chat.id, description)
