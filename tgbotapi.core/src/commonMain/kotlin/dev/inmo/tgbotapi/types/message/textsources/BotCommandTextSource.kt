package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.types.BotCommand
import dev.inmo.tgbotapi.types.Username
import dev.inmo.tgbotapi.types.usernameRegex
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

private val commandRegex = Regex("[/!][^@\\s]*")

/**
 * @see botCommand
 */
@Serializable
data class BotCommandTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String
) : TextSource {
    val command: String by lazy {
        commandRegex.find(source) ?.value ?.substring(1) ?: source.substring(1)// skip first symbol like "/" or "!"
    }
    val username: Username? by lazy {
        Username(usernameRegex.find(source) ?.value ?: return@lazy null)
    }

    override val markdown: String by lazy { source.commandMarkdown() }
    override val markdownV2: String by lazy { source.commandMarkdownV2() }
    override val html: String by lazy { source.commandHTML() }
}

/**
 * @param command Without leading "/"
 */
@Suppress("NOTHING_TO_INLINE")
inline fun botCommand(command: String) = BotCommandTextSource("/$command")

@Suppress("NOTHING_TO_INLINE")
inline fun botCommand(botCommand: BotCommand) = botCommand(botCommand.command)
