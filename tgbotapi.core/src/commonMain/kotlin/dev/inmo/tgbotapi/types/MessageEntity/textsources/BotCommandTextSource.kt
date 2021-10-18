package dev.inmo.tgbotapi.types.MessageEntity.textsources

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

    override val markdown: String by lazy { source.commandMarkdown() }
    override val markdownV2: String by lazy { source.commandMarkdownV2() }
    override val html: String by lazy { source.commandHTML() }
}

/**
 * @param command Without leading "/"
 */
@Suppress("NOTHING_TO_INLINE")
inline fun botCommand(command: String) = BotCommandTextSource("/$command")
