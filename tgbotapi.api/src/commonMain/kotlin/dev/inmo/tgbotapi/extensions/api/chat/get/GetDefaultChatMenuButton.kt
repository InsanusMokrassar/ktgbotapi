package dev.inmo.tgbotapi.extensions.api.chat.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.get.GetDefaultChatMenuButton
import dev.inmo.tgbotapi.types.MenuButton

public suspend fun TelegramBot.getDefaultChatMenuButton(): MenuButton = execute(GetDefaultChatMenuButton)
