package com.github.insanusmokrassar.TelegramBotAPI.requests.webhook

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
class DeleteWebhook : SimpleRequest<Boolean> {
    override fun method(): String = "deleteWebhook"

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
