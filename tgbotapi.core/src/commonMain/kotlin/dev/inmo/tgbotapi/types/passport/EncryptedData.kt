package dev.inmo.tgbotapi.types.passport

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias Base64EncodedData = String
typealias EncryptedAndBase64EncodedData = String
typealias EncryptedByBotRSAAndBase64EncodedData = String
typealias EncryptedData = String

@Serializable
data class EncryptedCredentials(
    @SerialName(dataField)
    val data: EncryptedAndBase64EncodedData,
    @SerialName(hashField)
    val hash: Base64EncodedData,
    @SerialName(secretField)
    val secret: EncryptedByBotRSAAndBase64EncodedData
)
