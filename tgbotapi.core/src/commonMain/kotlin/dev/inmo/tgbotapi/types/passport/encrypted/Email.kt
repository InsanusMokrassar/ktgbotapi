package dev.inmo.tgbotapi.types.passport.encrypted

import dev.inmo.micro_utils.serialization.base64.Base64BytesToFromStringSerializer
import dev.inmo.tgbotapi.types.emailField
import dev.inmo.tgbotapi.types.hashField
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithEmail
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.PassportElementHash
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Email(
    @SerialName(emailField)
    override val email: String,
    @SerialName(hashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: PassportElementHash
) : EncryptedPassportElementWithEmail
