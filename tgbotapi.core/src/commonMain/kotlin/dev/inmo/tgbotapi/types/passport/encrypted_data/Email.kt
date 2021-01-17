package dev.inmo.tgbotapi.types.passport.encrypted_data

import dev.inmo.micro_utils.serialization.base64.Base64StringSerializer
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.WithEmail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(EncryptedElementSerializer::class)
data class Email(
    @SerialName(emailField)
    override val email: String,
    @SerialName(hashField)
    @Serializable(Base64StringSerializer::class)
    override val hash: String
) : WithEmail {
}