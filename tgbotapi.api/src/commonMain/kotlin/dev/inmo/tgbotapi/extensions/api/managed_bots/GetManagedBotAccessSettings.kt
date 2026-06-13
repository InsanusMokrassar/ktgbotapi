package dev.inmo.tgbotapi.extensions.api.managed_bots

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.managed_bots.GetManagedBotAccessSettings
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.managed_bots.BotAccessSettings

public suspend fun TelegramBot.getManagedBotAccessSettings(
    userId: ChatId
): BotAccessSettings = execute(
    GetManagedBotAccessSettings(userId = userId)
)
