package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

data class BoldTextMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String
) : TextMessageEntity() {
    override val markdownFormatSymbol: String = "*"
    override val htmlFormatTagname: String = "b"
}
