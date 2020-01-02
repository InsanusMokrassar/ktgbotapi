package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.hashTagHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.hashTagMarkdown

class HashTagTextSource(
    override val rawSource: String
) : TextSource {
    override val asMarkdownSource: String
        get() = rawSource.hashTagMarkdown()
    override val asHtmlSource: String
        get() = rawSource.hashTagHTML()
}
