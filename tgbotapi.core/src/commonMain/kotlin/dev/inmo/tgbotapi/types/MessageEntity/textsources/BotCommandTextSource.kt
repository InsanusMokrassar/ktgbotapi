package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*
import dev.inmo.tgbotapi.utils.internal.*
import dev.inmo.tgbotapi.utils.internal.commandMarkdown
import dev.inmo.tgbotapi.utils.internal.commandMarkdownV2

private val commandRegex = Regex("[/!][^@\\s]*")

/**
 * @see botCommand
 */
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
