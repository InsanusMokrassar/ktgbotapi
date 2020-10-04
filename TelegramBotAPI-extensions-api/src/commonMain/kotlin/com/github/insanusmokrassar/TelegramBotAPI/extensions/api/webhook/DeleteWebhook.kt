package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.webhook

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.webhook.DeleteWebhook

suspend fun TelegramBot.deleteWebhook() = execute(DeleteWebhook())
