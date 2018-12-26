package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

data class TextLinkMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String,
    val url: String
) : MessageEntity {
    override val asMarkdownSource: String by lazy {
        "[$sourceString]($url)"
    }
}
