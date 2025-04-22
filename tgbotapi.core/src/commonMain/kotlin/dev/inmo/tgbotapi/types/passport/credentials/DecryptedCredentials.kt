package dev.inmo.tgbotapi.types.passport.credentials

import dev.inmo.tgbotapi.types.nonceField
import dev.inmo.tgbotapi.types.passport.decrypted.SecureData
import dev.inmo.tgbotapi.types.secureDataField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DecryptedCredentials(
    @SerialName(secureDataField)
    val secureData: SecureData,
    @SerialName(nonceField)
    val nonce: String,
)
