package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.*

class PhoneNumberTextSource(
    source: String,
    textParts: List<TextPart>
) : MultilevelTextSource {
    override val textParts: List<TextPart> = source.fullListOfSubSource(textParts)
    override val asMarkdownSource: String by lazy { source.phoneMarkdown() }
    override val asMarkdownV2Source: String by lazy { phoneMarkdownV2() }
    override val asHtmlSource: String by lazy { phoneHTML() }
}
