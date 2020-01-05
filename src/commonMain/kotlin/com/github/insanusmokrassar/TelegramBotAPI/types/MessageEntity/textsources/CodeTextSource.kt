package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.escapeMarkdownV2PreAndCode

class CodeTextSource(
    source: String
) : TextSource {
    override val asMarkdownSource: String by lazy { source.codeMarkdown() }
    override val asMarkdownV2Source: String by lazy { source.codeMarkdownV2() }
    override val asHtmlSource: String by lazy { source.codeHTML() }
}
