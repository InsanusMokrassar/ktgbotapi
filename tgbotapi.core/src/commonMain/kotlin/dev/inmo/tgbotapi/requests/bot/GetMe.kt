package dev.inmo.tgbotapi.requests.bot

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.chat.BotFullInfo
import kotlinx.serialization.*

@Serializable
object GetMe : SimpleRequest<BotFullInfo> {
    override fun method(): String = "getMe"
    override val resultDeserializer: DeserializationStrategy<BotFullInfo>
        get() = BotFullInfo.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
