package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.utils.phoneHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.phoneMarkdown

data class PhoneNumberMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String
) : MessageEntity {
    override val asMarkdownSource: String = sourceString.phoneMarkdown()
    override val asHtmlSource: String = sourceString.phoneHTML()
}
