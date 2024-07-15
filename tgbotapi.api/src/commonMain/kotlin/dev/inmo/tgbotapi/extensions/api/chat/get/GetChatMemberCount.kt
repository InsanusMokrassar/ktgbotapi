package dev.inmo.tgbotapi.extensions.api.chat.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.get.GetChatMemberCount
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.PublicChat

public suspend fun TelegramBot.getChatMemberCount(
    chatId: ChatIdentifier
): Int = execute(GetChatMemberCount(chatId))

public suspend fun TelegramBot.getChatMemberCount(
    chat: PublicChat
): Int = getChatMemberCount(chat.id)
