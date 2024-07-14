package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.local.Close

@Suppress("unused")
public suspend inline fun TelegramBot.close(): Boolean = execute(Close)
