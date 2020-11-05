package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.local.LogOut

suspend inline fun TelegramBot.logOut() = execute(LogOut)
