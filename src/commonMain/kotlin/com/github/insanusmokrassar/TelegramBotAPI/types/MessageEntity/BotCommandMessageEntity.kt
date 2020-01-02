package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.BotCommandTextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.commandHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.commandMarkdown

data class BotCommandMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val rawSource: String,
    private val botCommandTextSource: BotCommandTextSource = BotCommandTextSource(rawSource)
) : MessageEntity, TextSource by botCommandTextSource {
    val command: String
        get() = botCommandTextSource.command
}
