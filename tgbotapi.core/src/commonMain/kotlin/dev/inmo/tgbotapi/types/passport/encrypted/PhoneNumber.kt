package dev.inmo.tgbotapi.types.passport.encrypted

import dev.inmo.micro_utils.serialization.base64.Base64BytesToFromStringSerializer
import dev.inmo.tgbotapi.types.hashField
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithPhoneNumber
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.PassportElementHash
import dev.inmo.tgbotapi.types.phoneNumberField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhoneNumber(
    @SerialName(phoneNumberField)
    override val phoneNumber: String,
    @SerialName(hashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: PassportElementHash,
) : EncryptedPassportElementWithPhoneNumber
