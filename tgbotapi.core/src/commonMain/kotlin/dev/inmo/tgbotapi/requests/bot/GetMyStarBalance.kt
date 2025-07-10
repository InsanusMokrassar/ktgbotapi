package dev.inmo.tgbotapi.requests.bot

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.chat.ExtendedBot
import dev.inmo.tgbotapi.types.payments.stars.StarAmount
import kotlinx.serialization.*

@Serializable
object GetMyStarBalance : SimpleRequest<StarAmount> {
    override fun method(): String = "getMyStarBalance"
    override val resultDeserializer: DeserializationStrategy<StarAmount>
        get() = StarAmount.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
