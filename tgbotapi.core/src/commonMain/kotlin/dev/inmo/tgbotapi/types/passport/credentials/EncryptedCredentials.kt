package dev.inmo.tgbotapi.types.passport.credentials

import dev.inmo.micro_utils.crypto.SourceBytes
import dev.inmo.micro_utils.serialization.base64.Base64BytesToFromStringSerializer
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias EncryptedByBotPublicKeyData = SourceBytes
typealias EncryptedData = SourceBytes

@Serializable
data class EncryptedCredentials(
    @SerialName(dataField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    val data: EncryptedData,
    @SerialName(hashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    val hash: SourceBytes,
    @SerialName(secretField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    val secret: EncryptedByBotPublicKeyData
)
