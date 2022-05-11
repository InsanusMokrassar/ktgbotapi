package dev.inmo.tgbotapi.types.MessageEntity.textsources

private val commandRegex = Regex("[/!][^@\\s]*")

/**
 * @see botCommand
 */
@Deprecated("Replaced", ReplaceWith("BotCommandTextSource", "dev.inmo.tgbotapi.types.message.textsources.BotCommandTextSource"))
typealias BotCommandTextSource = dev.inmo.tgbotapi.types.message.textsources.BotCommandTextSource

/**
 * @param command Without leading "/"
 */
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("botCommand", "dev.inmo.tgbotapi.types.message.textsources.botCommand"))
inline fun botCommand(command: String) = dev.inmo.tgbotapi.types.message.textsources.botCommand(command)
