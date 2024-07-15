package dev.inmo.tgbotapi.extensions.api.chat.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.get.GetChatMenuButton
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.MenuButton
import dev.inmo.tgbotapi.types.chat.PrivateChat

public suspend fun TelegramBot.getChatMenuButton(
    chatId: IdChatIdentifier
): MenuButton = execute(GetChatMenuButton(chatId))

public suspend fun TelegramBot.getChatMenuButton(
    chat: PrivateChat
): MenuButton = getChatMenuButton(chat.id)
