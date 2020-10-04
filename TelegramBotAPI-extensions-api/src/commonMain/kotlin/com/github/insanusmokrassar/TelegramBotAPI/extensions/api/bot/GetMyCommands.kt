package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.bot

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.bot.GetMyCommands

suspend fun TelegramBot.getMyCommands() = execute(GetMyCommands)
