package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.MultilevelTextSource
import dev.inmo.tgbotapi.CommonAbstracts.TextPart
import dev.inmo.tgbotapi.utils.*

private val commandRegex = Regex("[/!][^@\\s]*")

class BotCommandTextSource(
    override val source: String,
    textParts: List<TextPart>
) : MultilevelTextSource {
    val command: String by lazy {
        commandRegex.find(source) ?.value ?.substring(1) ?: source.substring(1)// skip first symbol like "/" or "!"
    }

    override val textParts: List<TextPart> by lazy {
        command.fullListOfSubSource(
            textParts.shiftSourcesToTheLeft(1)
        )
    }
    override val asMarkdownSource: String by lazy { source.commandMarkdown() }
    override val asMarkdownV2Source: String by lazy { commandMarkdownV2() }
    override val asHtmlSource: String by lazy { commandHTML() }
}
