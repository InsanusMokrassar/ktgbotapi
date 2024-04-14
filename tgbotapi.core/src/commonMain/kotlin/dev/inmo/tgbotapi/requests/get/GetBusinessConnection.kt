package dev.inmo.tgbotapi.requests.get

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.business_connection.BusinessConnection
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.files.PathedFile
import dev.inmo.tgbotapi.types.idField
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

@Serializable
data class GetBusinessConnection(
    @SerialName(idField)
    val id: BusinessConnectionId
) : SimpleRequest<BusinessConnection> {
    override fun method(): String {
        return "getBusinessConnection"
    }

    override val resultDeserializer: DeserializationStrategy<BusinessConnection>
        get() = BusinessConnection.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}