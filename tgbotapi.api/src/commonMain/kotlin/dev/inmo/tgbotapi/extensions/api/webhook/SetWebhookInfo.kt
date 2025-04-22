package dev.inmo.tgbotapi.extensions.api.webhook

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.webhook.SetWebhook
import dev.inmo.tgbotapi.types.ALL_UPDATES_LIST

/**
 * Use this method to send information about webhook (like [url])
 */
public suspend fun TelegramBot.setWebhookInfo(
    url: String,
    ipAddress: String? = null,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = ALL_UPDATES_LIST,
    dropPendingUpdates: Boolean? = null,
    secretToken: String? = null,
): Boolean =
    execute(
        SetWebhook(
            url,
            ipAddress,
            maxAllowedConnections,
            allowedUpdates,
            dropPendingUpdates,
            secretToken,
        ),
    )

/**
 * Use this method to send information about webhook (like [url] and [certificate])
 */
public suspend fun TelegramBot.setWebhookInfo(
    url: String,
    certificate: FileId,
    ipAddress: String? = null,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = ALL_UPDATES_LIST,
    dropPendingUpdates: Boolean? = null,
    secretToken: String? = null,
): Boolean =
    execute(
        SetWebhook(
            url,
            certificate,
            ipAddress,
            maxAllowedConnections,
            allowedUpdates,
            dropPendingUpdates,
            secretToken,
        ),
    )

/**
 * Use this method to send information about webhook (like [url] and [certificate])
 */
public suspend fun TelegramBot.setWebhookInfo(
    url: String,
    certificate: MultipartFile,
    ipAddress: String? = null,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = ALL_UPDATES_LIST,
    dropPendingUpdates: Boolean? = null,
    secretToken: String? = null,
): Boolean =
    execute(
        SetWebhook(
            url,
            certificate,
            ipAddress,
            maxAllowedConnections,
            allowedUpdates,
            dropPendingUpdates,
            secretToken,
        ),
    )
