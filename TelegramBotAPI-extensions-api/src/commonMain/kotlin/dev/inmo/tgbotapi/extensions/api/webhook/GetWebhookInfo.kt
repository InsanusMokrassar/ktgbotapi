package dev.inmo.tgbotapi.extensions.api.webhook

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.webhook.GetWebhookInfo

suspend fun TelegramBot.getWebhookInfo() = execute(GetWebhookInfo())
