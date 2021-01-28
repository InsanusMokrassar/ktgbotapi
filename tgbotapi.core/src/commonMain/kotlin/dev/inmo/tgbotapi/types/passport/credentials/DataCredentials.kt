package dev.inmo.tgbotapi.types.passport.credentials

import dev.inmo.tgbotapi.types.dataHashField
import dev.inmo.tgbotapi.types.secretField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataCredentials(
    @SerialName(dataHashField)
    val dataHash: String,
    @SerialName(secretField)
    val secret: String
)
