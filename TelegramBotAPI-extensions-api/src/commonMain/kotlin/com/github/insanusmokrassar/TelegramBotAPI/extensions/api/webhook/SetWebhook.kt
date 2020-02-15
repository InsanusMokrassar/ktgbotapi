package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.webhook

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.webhook.SetWebhook

suspend fun RequestsExecutor.setWebhookInfo(
    url: String,
    certificate: FileId,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = null
) = execute(
    SetWebhook(
        url, certificate, maxAllowedConnections, allowedUpdates
    )
)

suspend fun RequestsExecutor.setWebhookInfo(
    url: String,
    certificate: MultipartFile,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = null
) = execute(
    SetWebhook(
        url, certificate, maxAllowedConnections, allowedUpdates
    )
)
