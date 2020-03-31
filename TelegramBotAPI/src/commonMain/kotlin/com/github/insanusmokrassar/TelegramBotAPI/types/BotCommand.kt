package com.github.insanusmokrassar.TelegramBotAPI.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BotCommand(
    @SerialName(botCommandField)
    val command: String,
    @SerialName(descriptionField)
    val description: String
)
