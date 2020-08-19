package com.github.insanusmokrassar.TelegramBotAPI.types

import com.github.insanusmokrassar.TelegramBotAPI.utils.throwRangeError
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

val BotCommandNameRegex = Regex("^[a-z_0-9]{${botCommandLengthLimit.first},${botCommandLengthLimit.last}}$")

@Serializable
data class BotCommand(
    @SerialName(botCommandField)
    val command: String,
    @SerialName(descriptionField)
    val description: String
) {
    init {
        if (command.length !in botCommandLengthLimit) {
            throwRangeError("Command name size", botCommandLengthLimit, command.length)
        }
        if (!command.matches(BotCommandNameRegex)) {
            error("Bot command must contains only lowercase English letters, digits and underscores, but incoming command was $command")
        }
        if (description.length !in botCommandDescriptionLimit) {
            throwRangeError("Command description size", botCommandDescriptionLimit, description.length)
        }
    }
}
