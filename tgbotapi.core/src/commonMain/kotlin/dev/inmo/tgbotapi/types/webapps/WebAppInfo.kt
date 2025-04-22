package dev.inmo.tgbotapi.types.webapps

import dev.inmo.tgbotapi.types.urlField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WebAppInfo(
    @SerialName(urlField)
    val url: String,
)
