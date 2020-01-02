package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.preHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.preMarkdown

class PreTextSource(
    override val rawSource: String
) : TextSource {
    override val asMarkdownSource: String
        get() = rawSource.preMarkdown()
    override val asHtmlSource: String
        get() = rawSource.preHTML()
}
