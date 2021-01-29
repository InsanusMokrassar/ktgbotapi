package dev.inmo.tgbotapi.types.passport

import dev.inmo.tgbotapi.types.credentialsField
import dev.inmo.tgbotapi.types.dataField
import dev.inmo.tgbotapi.types.passport.credentials.EncryptedCredentials
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PassportData(
    @SerialName(dataField)
    val data: List<EncryptedPassportElement>,
    @SerialName(credentialsField)
    val credentials: EncryptedCredentials
)
