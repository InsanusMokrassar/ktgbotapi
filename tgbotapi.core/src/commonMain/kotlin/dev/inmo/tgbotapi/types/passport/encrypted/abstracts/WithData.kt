package dev.inmo.tgbotapi.types.passport.encrypted.abstracts

import dev.inmo.tgbotapi.types.passport.credentials.EncryptedData
import dev.inmo.tgbotapi.types.passport.encrypted.EncryptedElementSerializer
import kotlinx.serialization.Serializable

@Serializable(EncryptedElementSerializer::class)
interface WithData : EncryptedPassportElement {
    val data: EncryptedData
}