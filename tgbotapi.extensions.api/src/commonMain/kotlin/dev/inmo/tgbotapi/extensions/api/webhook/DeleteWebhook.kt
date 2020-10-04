package dev.inmo.tgbotapi.extensions.api.webhook

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.webhook.DeleteWebhook

suspend fun TelegramBot.deleteWebhook() = execute(DeleteWebhook())
