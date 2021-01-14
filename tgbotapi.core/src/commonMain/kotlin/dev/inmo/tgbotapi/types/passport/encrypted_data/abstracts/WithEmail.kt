package dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts

import dev.inmo.tgbotapi.types.passport.encrypted_data.EncryptedElementSerializer
import kotlinx.serialization.Serializable

@Serializable(EncryptedElementSerializer::class)
interface WithEmail : EncryptedPassportElement {
    val email: String
}