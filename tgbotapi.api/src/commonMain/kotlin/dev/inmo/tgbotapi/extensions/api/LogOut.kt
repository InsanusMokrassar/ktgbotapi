package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.local.LogOut

public suspend inline fun TelegramBot.logOut(): Boolean = execute(LogOut)
