package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

abstract class TextMessageEntity : MessageEntity {
    protected abstract val markdownFormatSymbol: String
    protected abstract val htmlFormatTagname: String

    override val asMarkdownSource: String by lazy {
        "$markdownFormatSymbol$sourceString$markdownFormatSymbol"
    }

    override val asHtmlSource: String by lazy {
        "<$htmlFormatTagname>$sourceString</$htmlFormatTagname>"
    }
}
