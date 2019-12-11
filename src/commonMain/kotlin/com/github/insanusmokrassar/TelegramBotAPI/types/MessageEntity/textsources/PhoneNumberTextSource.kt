package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.phoneHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.phoneMarkdown

class PhoneNumberTextSource(
    sourceString: String
) : TextSource {
    override val asMarkdownSource: String = sourceString.phoneMarkdown()
    override val asHtmlSource: String = sourceString.phoneHTML()
}
