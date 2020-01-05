package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.MultilevelTextSource
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextPart
import com.github.insanusmokrassar.TelegramBotAPI.utils.*

class ItalicTextSource(
    source: String,
    textParts: List<TextPart>
) : MultilevelTextSource {
    override val textParts: List<TextPart> = source.fullListOfSubSource(textParts)
    override val asMarkdownSource: String by lazy { source.italicMarkdown() }
    override val asMarkdownV2Source: String by lazy { italicMarkdownV2() }
    override val asHtmlSource: String by lazy { italicHTML() }
}
