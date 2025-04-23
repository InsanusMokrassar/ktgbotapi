package dev.inmo.tgbotapi.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BotShortDescription(
    @SerialName(shortDescriptionField)
    val shortDescription: String,
)
