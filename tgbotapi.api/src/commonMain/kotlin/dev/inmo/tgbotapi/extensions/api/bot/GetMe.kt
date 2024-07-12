package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.GetMe
import dev.inmo.tgbotapi.types.chat.ExtendedBot

public suspend fun TelegramBot.getMe(): ExtendedBot = execute(GetMe)
