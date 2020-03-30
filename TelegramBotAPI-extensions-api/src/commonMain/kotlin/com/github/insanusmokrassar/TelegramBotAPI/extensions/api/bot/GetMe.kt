package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.bot

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.bot.GetMe

suspend fun RequestsExecutor.getMe() = execute(GetMe)
