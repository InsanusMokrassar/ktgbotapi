package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.BoldTextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.boldHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.boldMarkdown

data class BoldTextMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val rawSource: String
) : MessageEntity, TextSource by BoldTextSource(rawSource)
