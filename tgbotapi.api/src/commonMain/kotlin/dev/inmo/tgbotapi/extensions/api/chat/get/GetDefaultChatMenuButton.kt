package dev.inmo.tgbotapi.extensions.api.chat.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.get.GetDefaultChatMenuButton

suspend fun TelegramBot.getDefaultChatMenuButton() = execute(GetDefaultChatMenuButton)
