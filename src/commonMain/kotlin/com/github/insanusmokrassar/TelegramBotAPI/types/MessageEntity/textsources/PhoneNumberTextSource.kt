package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.phoneHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.phoneMarkdown

class PhoneNumberTextSource(
    override val rawSource: String
) : TextSource {
    override val asMarkdownSource: String
        get() = rawSource.phoneMarkdown()
    override val asHtmlSource: String
        get() = rawSource.phoneHTML()
}
