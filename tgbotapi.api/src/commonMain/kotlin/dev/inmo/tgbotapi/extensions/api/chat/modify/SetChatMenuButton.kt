package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.SetChatMenuButton
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.MenuButton
import dev.inmo.tgbotapi.types.chat.PrivateChat

public suspend fun TelegramBot.setChatMenuButton(
    chatId: IdChatIdentifier,
    menuButton: MenuButton,
): Boolean = execute(SetChatMenuButton(chatId, menuButton))

public suspend fun TelegramBot.setChatMenuButton(
    chat: PrivateChat,
    menuButton: MenuButton,
): Boolean = setChatMenuButton(chat.id, menuButton)
