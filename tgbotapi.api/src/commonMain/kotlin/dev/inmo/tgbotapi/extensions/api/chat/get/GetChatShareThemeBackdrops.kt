package dev.inmo.tgbotapi.extensions.api.chat.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.get.GetChatShareThemeBackdrops
import dev.inmo.tgbotapi.types.chat.ChatShareThemeBackdrops

public suspend fun TelegramBot.getChatShareThemeBackdrops(): ChatShareThemeBackdrops = execute(GetChatShareThemeBackdrops)
