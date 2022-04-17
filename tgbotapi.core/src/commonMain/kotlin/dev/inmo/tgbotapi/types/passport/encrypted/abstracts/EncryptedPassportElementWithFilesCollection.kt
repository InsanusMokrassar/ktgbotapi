package dev.inmo.tgbotapi.types.passport.encrypted.abstracts

import dev.inmo.tgbotapi.types.passport.encrypted.EncryptedElementSerializer
import dev.inmo.tgbotapi.types.files.PassportFile
import kotlinx.serialization.Serializable

@Serializable(EncryptedElementSerializer::class)
interface EncryptedPassportElementWithFilesCollection : EncryptedPassportElement {
    val files: List<PassportFile>
}
