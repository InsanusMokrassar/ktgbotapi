package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*

class CashTagTextSource(
    override val source: String,
    override val textSources: List<TextSource>
) : MultilevelTextSource {
    override val asMarkdownSource: String by lazy { source.cashTagMarkdown() }
    override val asMarkdownV2Source: String by lazy { cashTagMarkdownV2() }
    override val asHtmlSource: String by lazy { cashTagHTML() }
}
