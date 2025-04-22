package dev.inmo.tgbotapi.requests.gifts

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.gifts.Gifts
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

@Serializable
data object GetAvailableGifts : SimpleRequest<Gifts> {
    override fun method(): String = "getAvailableGifts"

    override val resultDeserializer: DeserializationStrategy<Gifts>
        get() = Gifts.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
