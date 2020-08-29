package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.MultilevelTextSource
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextPart
import com.github.insanusmokrassar.TelegramBotAPI.types.User
import com.github.insanusmokrassar.TelegramBotAPI.utils.*

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
