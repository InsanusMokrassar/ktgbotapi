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
    ipAddress: String? = null,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = null,
    dropPendingUpdates: Boolean? = null,
    secretToken: String? = null
) = execute(
    SetWebhook(
        url, ipAddress, maxAllowedConnections, allowedUpdates, dropPendingUpdates, secretToken
    )
)

/**
 * Use this method to send information about webhook (like [url] and [certificate])
 */
suspend fun TelegramBot.setWebhookInfo(
    url: String,
    certificate: FileId,
    ipAddress: String? = null,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = null,
    dropPendingUpdates: Boolean? = null,
    secretToken: String? = null
) = execute(
    SetWebhook(
        url, certificate, ipAddress, maxAllowedConnections, allowedUpdates, dropPendingUpdates, secretToken
    )
)

/**
 * Use this method to send information about webhook (like [url] and [certificate])
 */
suspend fun TelegramBot.setWebhookInfo(
    url: String,
    certificate: MultipartFile,
    ipAddress: String? = null,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = null,
    dropPendingUpdates: Boolean? = null,
    secretToken: String? = null
) = execute(
    SetWebhook(
        url, certificate, ipAddress, maxAllowedConnections, allowedUpdates, dropPendingUpdates, secretToken
    )
)
