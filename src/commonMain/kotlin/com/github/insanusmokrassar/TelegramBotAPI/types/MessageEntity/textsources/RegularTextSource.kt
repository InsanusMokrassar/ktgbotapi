package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.regularHtml
import com.github.insanusmokrassar.TelegramBotAPI.utils.regularMarkdown

class RegularTextSource(
    override val rawSource: String
) : TextSource {
    override val asMarkdownSource: String
        get() = rawSource.regularMarkdown()
    override val asHtmlSource: String
        get() = rawSource.regularHtml()
}
