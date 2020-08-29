package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.*

class URLTextSource(
    override val source: String
) : TextSource {
    override val asMarkdownSource: String by lazy { source.linkMarkdown(source) }
    override val asMarkdownV2Source: String by lazy { source.linkMarkdownV2(source) }
    override val asHtmlSource: String by lazy { source.linkHTML(source) }
}
