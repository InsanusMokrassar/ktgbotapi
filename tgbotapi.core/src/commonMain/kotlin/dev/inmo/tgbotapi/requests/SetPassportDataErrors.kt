package dev.inmo.tgbotapi.requests

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.passport.PassportElementError
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class SetPassportDataErrors(
    @SerialName(userIdField)
    val user: UserId,
    @SerialName(errorsField)
    val errors: List<PassportElementError>,
) : SimpleRequest<Boolean> {
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()

    override fun method(): String = "setPassportDataErrors"

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
