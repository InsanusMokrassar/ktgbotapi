package dev.inmo.tgbotapi.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BotDescription(
    @SerialName(descriptionField)
    val description: String
)
