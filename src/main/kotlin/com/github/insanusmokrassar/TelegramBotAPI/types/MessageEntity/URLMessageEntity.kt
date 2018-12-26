package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

data class URLMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String
) : MessageEntity {
    val url: String
        get() = sourceString

    override val asMarkdownSource: String by lazy {
        "[$sourceString]($url)"
    }
}
