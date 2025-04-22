package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.SetChatTitle
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.PublicChat

public suspend fun TelegramBot.setChatTitle(
    chatId: ChatIdentifier,
    title: String,
): Boolean = execute(SetChatTitle(chatId, title))

public suspend fun TelegramBot.setChatTitle(
    chat: PublicChat,
    title: String,
): Boolean = setChatTitle(chat.id, title)
