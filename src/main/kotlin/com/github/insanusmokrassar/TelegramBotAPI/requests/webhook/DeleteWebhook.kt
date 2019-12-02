package com.github.insanusmokrassar.TelegramBotAPI.requests.webhook

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
class DeleteWebhook : SimpleRequest<Boolean> {
    override fun method(): String = "deleteWebhook"

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = BooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
