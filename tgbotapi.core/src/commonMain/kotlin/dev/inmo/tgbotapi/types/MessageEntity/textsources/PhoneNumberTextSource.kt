package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*

class PhoneNumberTextSource(
    override val source: String,
    override val textSources: List<TextSource>
) : MultilevelTextSource {
    override val asMarkdownSource: String by lazy { source.phoneMarkdown() }
    override val asMarkdownV2Source: String by lazy { phoneMarkdownV2() }
    override val asHtmlSource: String by lazy { phoneHTML() }
}
