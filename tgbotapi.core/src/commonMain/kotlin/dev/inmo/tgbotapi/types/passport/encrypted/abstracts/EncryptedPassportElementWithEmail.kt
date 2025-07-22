package dev.inmo.tgbotapi.types.passport.encrypted.abstracts

import dev.inmo.tgbotapi.types.passport.encrypted.EncryptedElementSerializer
import kotlinx.serialization.Serializable

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(EncryptedElementSerializer::class)
interface EncryptedPassportElementWithEmail : EncryptedPassportElement {
    val email: String
}