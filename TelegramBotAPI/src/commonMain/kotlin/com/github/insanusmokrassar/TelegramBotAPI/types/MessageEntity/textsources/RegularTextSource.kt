package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.*

class RegularTextSource(
    override val source: String
) : TextSource {
    override val asMarkdownSource: String by lazy { source.regularMarkdown() }
    override val asMarkdownV2Source: String by lazy { source.regularMarkdownV2() }
    override val asHtmlSource: String by lazy { source.regularHtml() }
}
