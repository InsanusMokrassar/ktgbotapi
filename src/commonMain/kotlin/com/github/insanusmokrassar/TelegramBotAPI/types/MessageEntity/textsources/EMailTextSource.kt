package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.emailHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.emailMarkdown

class EMailTextSource(
    sourceString: String
) : TextSource {
    override val asMarkdownSource: String = sourceString.emailMarkdown()
    override val asHtmlSource: String = sourceString.emailHTML()
}
