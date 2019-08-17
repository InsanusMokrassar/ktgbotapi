package com.github.insanusmokrassar.TelegramBotAPI.requests.webhook

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
class DeleteWebhook : SimpleRequest<Boolean> {
    override fun method(): String = "deleteWebhook"

    override fun resultDeserializer(): KSerializer<Boolean> = BooleanSerializer
}
