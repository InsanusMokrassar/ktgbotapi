package dev.inmo.tgbotapi.types.passport.encrypted_data

import dev.inmo.micro_utils.serialization.base64.Base64BytesToFromStringSerializer
import dev.inmo.tgbotapi.types.hashField
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.PassportElementHash
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.WithPhoneNumber
import dev.inmo.tgbotapi.types.phoneNumberField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhoneNumber(
    @SerialName(phoneNumberField)
    override val phoneNumber: String,
    @SerialName(hashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: PassportElementHash
) : WithPhoneNumber {
}