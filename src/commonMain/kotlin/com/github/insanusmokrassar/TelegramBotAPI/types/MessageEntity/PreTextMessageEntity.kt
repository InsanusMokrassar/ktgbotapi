package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.PreTextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.preHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.preMarkdown

data class PreTextMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String
) : MessageEntity, TextSource by PreTextSource(sourceString)
