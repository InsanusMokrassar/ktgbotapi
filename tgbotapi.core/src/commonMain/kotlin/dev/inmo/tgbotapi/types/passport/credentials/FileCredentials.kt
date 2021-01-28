package dev.inmo.tgbotapi.types.passport.credentials

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FileCredentials(
    @SerialName(fileHashField)
    val fileHash: String,
    @SerialName(secretField)
    val secret: String
)
