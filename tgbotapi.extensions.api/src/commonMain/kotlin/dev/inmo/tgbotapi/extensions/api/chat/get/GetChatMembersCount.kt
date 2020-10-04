package dev.inmo.tgbotapi.extensions.api.chat.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.get.GetChatMembersCount
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.abstracts.PublicChat

suspend fun TelegramBot.getChatMembersCount(
    chatId: ChatIdentifier
) = execute(GetChatMembersCount(chatId))

suspend fun TelegramBot.getChatMembersCount(
    chat: PublicChat
) = getChatMembersCount(chat.id)
