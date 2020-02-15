package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.webhook

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.webhook.DeleteWebhook

suspend fun RequestsExecutor.deleteWebhook() = execute(DeleteWebhook())
