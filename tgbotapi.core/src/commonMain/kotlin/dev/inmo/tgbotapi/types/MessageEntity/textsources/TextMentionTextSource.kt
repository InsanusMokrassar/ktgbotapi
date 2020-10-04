package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.MultilevelTextSource
import dev.inmo.tgbotapi.CommonAbstracts.TextPart
import dev.inmo.tgbotapi.types.User
import dev.inmo.tgbotapi.utils.*

class TextMentionTextSource(
    override val source: String,
    val user: User,
    textParts: List<TextPart>
) : MultilevelTextSource {
    override val textParts: List<TextPart> by lazy { source.fullListOfSubSource(textParts) }
    override val asMarkdownSource: String by lazy { source.textMentionMarkdown(user.id) }
    override val asMarkdownV2Source: String by lazy { textMentionMarkdownV2(user.id) }
    override val asHtmlSource: String by lazy { textMentionHTML(user.id) }
}
