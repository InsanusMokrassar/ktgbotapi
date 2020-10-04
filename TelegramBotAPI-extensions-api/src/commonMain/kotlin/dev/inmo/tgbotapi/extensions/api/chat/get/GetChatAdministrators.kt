package dev.inmo.tgbotapi.extensions.api.chat.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.get.GetChatAdministrators
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.abstracts.PublicChat

suspend fun TelegramBot.getChatAdministrators(
    chatId: ChatIdentifier
) = execute(GetChatAdministrators(chatId))

suspend fun TelegramBot.getChatAdministrators(
    chat: PublicChat
) = getChatAdministrators(chat.id)
