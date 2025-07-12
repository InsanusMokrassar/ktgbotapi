package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.local.Close

public suspend inline fun TelegramBot.executeClose(): Boolean = execute(Close)
