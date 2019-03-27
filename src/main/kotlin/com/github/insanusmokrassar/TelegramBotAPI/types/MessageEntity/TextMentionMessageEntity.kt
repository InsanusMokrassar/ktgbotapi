package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.types.User

class TextMentionMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String,
    val user: User
) : LinkMessageEntity(
    sourceString,
    "tg://user?id=${user.id})"
)
