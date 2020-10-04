package dev.inmo.tgbotapi.extensions.api.webhook

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.webhook.SetWebhook

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
