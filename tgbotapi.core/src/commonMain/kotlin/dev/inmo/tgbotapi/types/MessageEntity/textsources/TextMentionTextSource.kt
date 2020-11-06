package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.types.User
import dev.inmo.tgbotapi.utils.*

class TextMentionTextSource(
    override val source: String,
    val user: User,
    override val textSources: List<TextSource>
) : MultilevelTextSource {
    override val asMarkdownSource: String by lazy { source.textMentionMarkdown(user.id) }
    override val asMarkdownV2Source: String by lazy { textMentionMarkdownV2(user.id) }
    override val asHtmlSource: String by lazy { textMentionHTML(user.id) }
}
