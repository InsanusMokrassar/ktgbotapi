package dev.inmo.tgbotapi.extensions.api.managed_bots

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.managed_bots.GetManagedBotToken
import dev.inmo.tgbotapi.types.BotToken
import dev.inmo.tgbotapi.types.ChatId

public suspend fun TelegramBot.getManagedBotToken(
    userId: ChatId
): BotToken = execute(
    GetManagedBotToken(userId = userId)
)
