package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

data class HashTagMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String
) : MessageEntity
