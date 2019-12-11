package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.commandHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.commandMarkdown

private val commandRegex = Regex("[/!][^@\\s]*")

class BotCommandTextSource(
    sourceString: String
) : TextSource {
    override val asMarkdownSource: String = sourceString.commandMarkdown()
    override val asHtmlSource: String = sourceString.commandHTML()

    val command: String by lazy {
        commandRegex.find(sourceString) ?.value ?.substring(1) ?: sourceString.substring(1)// skip first symbol like "/" or "!"
    }
}
