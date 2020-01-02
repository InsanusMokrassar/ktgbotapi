package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.HashTagTextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.hashTagHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.hashTagMarkdown

data class HashTagMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val rawSource: String
) : MessageEntity, TextSource by HashTagTextSource(rawSource)
