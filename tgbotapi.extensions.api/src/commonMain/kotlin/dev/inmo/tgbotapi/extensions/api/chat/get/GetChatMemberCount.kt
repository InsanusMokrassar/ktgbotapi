package dev.inmo.tgbotapi.extensions.api.chat.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.get.GetChatMemberCount
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.abstracts.PublicChat

suspend fun TelegramBot.getChatMemberCount(
    chatId: ChatIdentifier
) = execute(GetChatMemberCount(chatId))

suspend fun TelegramBot.getChatMemberCount(
    chat: PublicChat
) = getChatMemberCount(chat.id)
