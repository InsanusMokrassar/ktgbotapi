package dev.inmo.tgbotapi.extensions.api.webhook

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.webhook.GetWebhookInfo
import dev.inmo.tgbotapi.types.WebhookInfo

public suspend fun TelegramBot.getWebhookInfo(): WebhookInfo = execute(GetWebhookInfo())
