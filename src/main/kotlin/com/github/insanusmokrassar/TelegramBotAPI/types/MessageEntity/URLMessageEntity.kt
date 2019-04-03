package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.utils.linkHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.linkMarkdown

data class URLMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String
) : MessageEntity{
    val url: String = sourceString

    override val asMarkdownSource: String = sourceString.linkMarkdown(url)
    override val asHtmlSource: String = sourceString.linkHTML(url)
}
