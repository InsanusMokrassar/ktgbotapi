package dev.inmo.tgbotapi.types.passport

import dev.inmo.micro_utils.serialization.base64.Base64StringSerializer
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias EncryptedAndBase64EncodedData = String
typealias EncryptedByBotPublicKeyData = String
typealias EncryptedData = String

@Serializable
data class EncryptedCredentials(
    @SerialName(dataField)
    @Serializable(Base64StringSerializer::class)
    val data: EncryptedData,
    @SerialName(hashField)
    @Serializable(Base64StringSerializer::class)
    val hash: String,
    @SerialName(secretField)
    @Serializable(Base64StringSerializer::class)
    val secret: EncryptedByBotPublicKeyData
)
