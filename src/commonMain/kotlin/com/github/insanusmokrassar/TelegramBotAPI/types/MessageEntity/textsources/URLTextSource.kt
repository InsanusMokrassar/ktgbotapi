package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.linkHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.linkMarkdown

class URLTextSource(
    override val rawSource: String
) : TextSource{
    override val asMarkdownSource: String
        get() = rawSource.linkMarkdown(rawSource)
    override val asHtmlSource: String
        get() = rawSource.linkHTML(rawSource)
}
