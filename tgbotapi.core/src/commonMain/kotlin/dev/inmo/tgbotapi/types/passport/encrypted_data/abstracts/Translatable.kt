package dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts

import dev.inmo.tgbotapi.types.passport.encrypted_data.EncryptedElementSerializer
import dev.inmo.tgbotapi.types.passport.encrypted_data.PassportFile
import kotlinx.serialization.Serializable

@Serializable(EncryptedElementSerializer::class)
interface Translatable : EncryptedPassportElement {
    val translations: List<PassportFile>
}