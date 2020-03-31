package com.github.insanusmokrassar.TelegramBotAPI.requests.bot

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.ExtendedBot
import kotlinx.serialization.*

@Serializable
object GetMe : SimpleRequest<ExtendedBot> {
    override fun method(): String = "getMe"
    override val resultDeserializer: DeserializationStrategy<ExtendedBot>
        get() = ExtendedBot.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
