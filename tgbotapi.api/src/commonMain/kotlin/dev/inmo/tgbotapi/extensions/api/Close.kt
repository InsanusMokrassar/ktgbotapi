package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.local.Close

@Suppress("unused")
suspend inline fun TelegramBot.close() = execute(Close)
