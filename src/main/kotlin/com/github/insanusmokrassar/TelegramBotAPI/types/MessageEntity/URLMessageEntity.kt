package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

data class URLMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String
) : LinkMessageEntity(
    sourceString,
    sourceString
) {
    val url: String = link
}
