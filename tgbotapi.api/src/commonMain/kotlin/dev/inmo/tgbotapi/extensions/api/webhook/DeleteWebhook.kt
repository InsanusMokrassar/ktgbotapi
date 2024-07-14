package dev.inmo.tgbotapi.extensions.api.webhook

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.webhook.DeleteWebhook

public suspend fun TelegramBot.deleteWebhook(): Boolean = execute(DeleteWebhook())
