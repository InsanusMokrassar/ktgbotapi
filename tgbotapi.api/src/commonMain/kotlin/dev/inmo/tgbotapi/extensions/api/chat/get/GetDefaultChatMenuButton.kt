package dev.inmo.tgbotapi.extensions.api.chat.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.get.GetDefaultChatMenuButton
import dev.inmo.tgbotapi.requests.chat.modify.SetDefaultChatMenuButton
import dev.inmo.tgbotapi.types.MenuButton

suspend fun TelegramBot.getDefaultChatMenuButton() = execute(GetDefaultChatMenuButton)
