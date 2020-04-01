package com.github.insanusmokrassar.TelegramBotAPI.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BotCommand(
    @SerialName(botCommandField)
    val command: String,
    @SerialName(descriptionField)
    val description: String
) {
    init {
        if (command.length !in botCommandLengthLimit) {
            error("Command size must be in range $botCommandLengthLimit, but actually have length ${command.length}")
        }
        if (description.length !in botCommandDescriptionLimit) {
            error("Command description size must be in range $botCommandDescriptionLimit, but actually have length ${description.length}")
        }
    }
}
