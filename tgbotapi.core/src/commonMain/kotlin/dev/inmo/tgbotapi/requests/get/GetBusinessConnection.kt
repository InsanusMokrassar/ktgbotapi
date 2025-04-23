package dev.inmo.tgbotapi.requests.get

import dev.inmo.tgbotapi.abstracts.types.WithBusinessConnectionId
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.businessConnectionIdField
import dev.inmo.tgbotapi.types.business_connection.BusinessConnection
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

@Serializable
data class GetBusinessConnection(
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId,
) : SimpleRequest<BusinessConnection>, WithBusinessConnectionId {
    override fun method(): String {
        return "getBusinessConnection"
    }

    override val resultDeserializer: DeserializationStrategy<BusinessConnection>
        get() = BusinessConnection.Companion
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
