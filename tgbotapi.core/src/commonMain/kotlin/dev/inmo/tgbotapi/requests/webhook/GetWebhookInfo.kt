package dev.inmo.tgbotapi.requests.webhook

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.WebhookInfo
import kotlinx.serialization.*

@Serializable
class GetWebhookInfo : SimpleRequest<WebhookInfo> {
    override fun method(): String = "getWebhookInfo"

    override val resultDeserializer: DeserializationStrategy<WebhookInfo>
        get() = WebhookInfo.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
