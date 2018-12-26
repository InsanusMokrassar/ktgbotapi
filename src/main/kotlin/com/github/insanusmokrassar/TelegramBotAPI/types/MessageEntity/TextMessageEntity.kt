package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

abstract class TextMessageEntity : MessageEntity {
    protected abstract val formatSymbol: String

    override val asMarkdownSource: String by lazy {
        "$formatSymbol$sourceString$formatSymbol"
    }
}
