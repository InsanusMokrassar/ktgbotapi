package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.commandHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.commandMarkdown

private val commandRegex = Regex("[/!][^@\\s]*")

class BotCommandTextSource(
    override val rawSource: String
) : TextSource {
    override val asMarkdownSource: String
        get() = rawSource.commandMarkdown()
    override val asHtmlSource: String
        get() = rawSource.commandHTML()

    val command: String by lazy {
        commandRegex.find(rawSource) ?.value ?.substring(1) ?: rawSource.substring(1)// skip first symbol like "/" or "!"
    }
}
