package dev.inmo.tgbotapi.extensions.api.webhook

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.webhook.SetWebhook

/**
 * Use this method to send information about webhook (like [url])
 */
suspend fun TelegramBot.setWebhookInfo(
    url: String,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = null
) = execute(
    SetWebhook(
        url, maxAllowedConnections, allowedUpdates
    )
)

/**
 * Use this method to send information about webhook (like [url] and [certificate])
 */
suspend fun TelegramBot.setWebhookInfo(
    url: String,
    certificate: FileId,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = null
) = execute(
    SetWebhook(
        url, certificate, maxAllowedConnections, allowedUpdates
    )
)

/**
 * Use this method to send information about webhook (like [url] and [certificate])
 */
suspend fun TelegramBot.setWebhookInfo(
    url: String,
    certificate: MultipartFile,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = null
) = execute(
    SetWebhook(
        url, certificate, maxAllowedConnections, allowedUpdates
    )
)
