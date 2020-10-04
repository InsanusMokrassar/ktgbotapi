package dev.inmo.tgbotapi.extensions.api.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify.SetChatTitle
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun TelegramBot.setChatTitle(
    chatId: ChatIdentifier,
    title: String
) = execute(SetChatTitle(chatId, title))

suspend fun TelegramBot.setChatTitle(
    chat: PublicChat,
    title: String
) = setChatTitle(chat.id, title)
