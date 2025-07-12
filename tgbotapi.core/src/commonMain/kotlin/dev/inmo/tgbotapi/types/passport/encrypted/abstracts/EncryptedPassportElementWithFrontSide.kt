package dev.inmo.tgbotapi.types.passport.encrypted.abstracts

import dev.inmo.tgbotapi.types.passport.encrypted.EncryptedElementSerializer
import dev.inmo.tgbotapi.types.files.PassportFile
import kotlinx.serialization.Serializable

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(EncryptedElementSerializer::class)
interface EncryptedPassportElementWithFrontSide : EncryptedPassportElement {
    val frontSide: PassportFile?
}
