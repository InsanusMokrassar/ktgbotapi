package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

class EMailMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String
) : LinkMessageEntity(
    sourceString,
    "mailto://$sourceString"
)
