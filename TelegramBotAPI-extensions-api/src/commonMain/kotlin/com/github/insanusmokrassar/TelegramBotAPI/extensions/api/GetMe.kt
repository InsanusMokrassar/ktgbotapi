package com.github.insanusmokrassar.TelegramBotAPI.extensions.api

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.extensions.api.bot.getMe

@Deprecated(
    "Replaced",
    ReplaceWith("getMe", "com.github.insanusmokrassar.TelegramBotAPI.extensions.api.bot.GetMeKt.getMe")
)
suspend fun RequestsExecutor.getMe() = getMe()