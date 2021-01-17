package dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts

import dev.inmo.micro_utils.serialization.base64.Base64StringSerializer
import dev.inmo.tgbotapi.types.passport.encrypted_data.EncryptedElementSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable(EncryptedElementSerializer::class)
interface EncryptedPassportElement {
    val hash: String
}

@Serializable(EncryptedElementSerializer::class)
data class UnknownEncryptedPassportElement(
    val rawJson: JsonObject,
    @Serializable(Base64StringSerializer::class)
    override val hash: String
) : EncryptedPassportElement
