package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

data class BotCommandMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String
) : MessageEntity {
    val command: String by lazy {
        sourceString.substring(1)// skip first symbol like "/" or "!"
    }
}
