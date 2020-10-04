package dev.inmo.tgbotapi.extensions.api.webhook

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.webhook.GetWebhookInfo

suspend fun TelegramBot.getWebhookInfo() = execute(GetWebhookInfo())
