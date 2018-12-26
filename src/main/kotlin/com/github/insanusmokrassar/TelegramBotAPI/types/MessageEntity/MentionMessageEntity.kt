package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

class MentionMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String
) : MessageEntity {
    override val asMarkdownSource: String
        get() = sourceString
}