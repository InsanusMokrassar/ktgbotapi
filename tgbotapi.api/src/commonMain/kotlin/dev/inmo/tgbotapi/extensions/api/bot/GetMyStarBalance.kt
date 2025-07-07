package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.GetMe
import dev.inmo.tgbotapi.requests.bot.GetMyStarBalance
import dev.inmo.tgbotapi.types.chat.ExtendedBot
import dev.inmo.tgbotapi.types.payments.stars.StarAmount

public suspend fun TelegramBot.getMyStarBalance(): StarAmount = execute(GetMyStarBalance)
