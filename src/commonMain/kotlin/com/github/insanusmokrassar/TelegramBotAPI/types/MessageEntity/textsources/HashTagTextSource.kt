package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.hashTagHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.hashTagMarkdown

class HashTagTextSource(
    sourceString: String
) : TextSource {
    override val asMarkdownSource: String = sourceString.hashTagMarkdown()
    override val asHtmlSource: String = sourceString.hashTagHTML()
}
