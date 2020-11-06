package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*

class EMailTextSource(
    override val source: String,
    override val textSources: List<TextSource>
) : MultilevelTextSource {
    override val asMarkdownSource: String by lazy { source.emailMarkdown() }
    override val asMarkdownV2Source: String by lazy { emailMarkdownV2(source) }
    override val asHtmlSource: String by lazy { emailHTML(source) }
}
