package dev.inmo.tgbotapi.requests.bot

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.chat.ExtendedBot
import kotlinx.serialization.*

@Serializable
object GetMe : SimpleRequest<ExtendedBot> {
    override fun method(): String = "getMe"
    override val resultDeserializer: DeserializationStrategy<ExtendedBot>
        get() = ExtendedBot.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
