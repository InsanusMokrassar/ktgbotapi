package dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts

import dev.inmo.tgbotapi.types.passport.Base64EncodedData
import dev.inmo.tgbotapi.types.passport.encrypted_data.EncryptedElementSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable(EncryptedElementSerializer::class)
interface EncryptedPassportElement {
    val hash: Base64EncodedData
}

@Serializable(EncryptedElementSerializer::class)
data class UnknownEncryptedPassportElement(
    val rawJson: JsonObject,
    override val hash: Base64EncodedData
) : EncryptedPassportElement
