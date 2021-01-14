package dev.inmo.tgbotapi.types.passport.encrypted_data

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.passport.Base64EncodedData
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.WithEmail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(EncryptedElementSerializer::class)
data class Email(
    @SerialName(emailField)
    override val email: String,
    @SerialName(hashField)
    override val hash: Base64EncodedData
) : WithEmail {
}