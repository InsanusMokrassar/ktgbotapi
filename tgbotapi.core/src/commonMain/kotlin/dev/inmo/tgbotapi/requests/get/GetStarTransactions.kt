package dev.inmo.tgbotapi.requests.get

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.payments.stars.StarTransactions
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

@Serializable
data class GetStarTransactions(
    @SerialName(offsetField)
    val offset: Int? = null,
    @SerialName(limitField)
    val limit: Int? = null,
) : SimpleRequest<StarTransactions> {
    override fun method(): String = "getStarTransactions"
    override val resultDeserializer: DeserializationStrategy<StarTransactions>
    get() = StarTransactions.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}