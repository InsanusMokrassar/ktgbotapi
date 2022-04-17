package dev.inmo.tgbotapi.extensions.api.chat.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.get.GetChatMenuButton
import dev.inmo.tgbotapi.requests.chat.modify.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.abstracts.PrivateChat

suspend fun TelegramBot.getChatMenuButton(
    chatId: ChatId
) = execute(GetChatMenuButton(chatId))

suspend fun TelegramBot.getChatMenuButton(
    chat: PrivateChat
) = getChatMenuButton(chat.id)
