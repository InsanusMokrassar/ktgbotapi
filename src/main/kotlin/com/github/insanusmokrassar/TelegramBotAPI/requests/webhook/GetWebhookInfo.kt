package com.github.insanusmokrassar.TelegramBotAPI.requests.webhook

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.WebhookInfo
import kotlinx.serialization.*

@Serializable
class GetWebhookInfo : SimpleRequest<WebhookInfo> {
    override fun method(): String = "getWebhookInfo"

    override fun resultSerializer(): KSerializer<WebhookInfo> = WebhookInfo.serializer()
}
