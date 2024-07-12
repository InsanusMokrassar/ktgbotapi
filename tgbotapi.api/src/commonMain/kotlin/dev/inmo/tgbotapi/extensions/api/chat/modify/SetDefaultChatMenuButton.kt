package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.SetDefaultChatMenuButton
import dev.inmo.tgbotapi.types.MenuButton

public suspend fun TelegramBot.setDefaultChatMenuButton(
    menuButton: MenuButton
): Boolean = execute(SetDefaultChatMenuButton(menuButton))
