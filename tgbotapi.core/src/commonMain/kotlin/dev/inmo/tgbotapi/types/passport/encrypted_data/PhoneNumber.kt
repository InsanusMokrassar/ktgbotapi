package dev.inmo.tgbotapi.types.passport.encrypted_data

import dev.inmo.micro_utils.serialization.base64.Base64StringSerializer
import dev.inmo.tgbotapi.types.hashField
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.WithPhoneNumber
import dev.inmo.tgbotapi.types.phoneNumberField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(EncryptedElementSerializer::class)
data class PhoneNumber(
    @SerialName(phoneNumberField)
    override val phoneNumber: String,
    @SerialName(hashField)
    @Serializable(Base64StringSerializer::class)
    override val hash: String
) : WithPhoneNumber {
}