package dev.inmo.tgbotapi.types.passport.encrypted.abstracts

import dev.inmo.tgbotapi.types.passport.encrypted.EncryptedElementSerializer
import kotlinx.serialization.Serializable

@Serializable(EncryptedElementSerializer::class)
interface WithPhoneNumber : EncryptedPassportElement {
    val phoneNumber: String
}