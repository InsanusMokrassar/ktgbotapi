package dev.inmo.tgbotapi.types.passport.encrypted.abstracts

import dev.inmo.tgbotapi.types.passport.encrypted.EncryptedElementSerializer
import dev.inmo.tgbotapi.types.passport.encrypted.PassportFile
import kotlinx.serialization.Serializable

@Serializable(EncryptedElementSerializer::class)
interface EncryptedPassportElementTranslatable : EncryptedPassportElement {
    val translations: List<PassportFile>
}