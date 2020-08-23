package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.*

class PreTextSource(
    override val source: String,
    val language: String? = null
) : TextSource {
    override val asMarkdownSource: String by lazy { source.preMarkdown(language) }
    override val asMarkdownV2Source: String by lazy { source.preMarkdownV2(language) }
    override val asHtmlSource: String by lazy { source.preHTML(language) }
}
