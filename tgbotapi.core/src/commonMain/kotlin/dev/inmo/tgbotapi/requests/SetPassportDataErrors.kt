package dev.inmo.tgbotapi.requests

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.passport.PassportElementError
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class SetPassportDataErrors(
    @SerialName(userIdField)
    val user: UserId,
    @SerialName(errorsField)
    val errors: List<PassportElementError>
) : Request<Boolean> {
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override fun method(): String = "setPassportDataErrors"
}
