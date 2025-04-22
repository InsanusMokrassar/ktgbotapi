package dev.inmo.tgbotapi.types.passport.credentials

import dev.inmo.micro_utils.crypto.SourceBytes
import dev.inmo.micro_utils.serialization.base64.Base64BytesToFromStringSerializer
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class EndDataCredentials {
    @Serializable(Base64BytesToFromStringSerializer::class)
    abstract val hash: SourceBytes

    @Serializable(Base64BytesToFromStringSerializer::class)
    abstract val secret: SourceBytes
}

@Serializable
data class DataCredentials(
    @SerialName(dataHashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: SourceBytes,
    @SerialName(secretField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val secret: SourceBytes,
) : EndDataCredentials()

@Serializable
data class FileCredentials(
    @SerialName(fileHashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: SourceBytes,
    @SerialName(secretField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val secret: SourceBytes,
) : EndDataCredentials()
