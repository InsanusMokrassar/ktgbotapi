package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.utils.*

class PreTextSource(
    override val source: String,
    val language: String? = null
) : TextSource {
    override val asMarkdownSource: String by lazy { source.preMarkdown(language) }
    override val asMarkdownV2Source: String by lazy { source.preMarkdownV2(language) }
    override val asHtmlSource: String by lazy { source.preHTML(language) }
}
