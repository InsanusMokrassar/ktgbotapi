package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

class EMailMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String
) : MessageEntity {
    override val asMarkdownSource: String by lazy {
        "[$sourceString](mailto://$sourceString)"
    }
}