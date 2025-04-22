package dev.inmo.tgbotapi.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BotName(
    @SerialName(nameField)
    val name: String,
)
