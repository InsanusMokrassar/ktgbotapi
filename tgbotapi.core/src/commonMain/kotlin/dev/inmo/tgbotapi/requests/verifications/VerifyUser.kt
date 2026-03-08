package dev.inmo.tgbotapi.requests.verifications

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.serializer


@Serializable
data class VerifyUser(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userIdField)
    val userId: UserId,
    @SerialName(customDescriptionField)
    val description: String? = null
): SimpleRequest<Unit> {
    override fun method(): String = "verifyUser"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
