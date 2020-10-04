package dev.inmo.tgbotapi.extensions.api.chat.get

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.get.GetChatMembersCount
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun TelegramBot.getChatMembersCount(
    chatId: ChatIdentifier
) = execute(GetChatMembersCount(chatId))

suspend fun TelegramBot.getChatMembersCount(
    chat: PublicChat
) = getChatMembersCount(chat.id)
