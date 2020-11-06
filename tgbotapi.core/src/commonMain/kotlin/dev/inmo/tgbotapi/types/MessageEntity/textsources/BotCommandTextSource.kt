package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*

private val commandRegex = Regex("[/!][^@\\s]*")

class BotCommandTextSource(
    override val source: String,
    override val textSources: List<TextSource>
) : MultilevelTextSource {
    val command: String by lazy {
        commandRegex.find(source) ?.value ?.substring(1) ?: source.substring(1)// skip first symbol like "/" or "!"
    }

    override val asMarkdownSource: String by lazy { source.commandMarkdown() }
    override val asMarkdownV2Source: String by lazy { commandMarkdownV2() }
    override val asHtmlSource: String by lazy { commandHTML() }
}
