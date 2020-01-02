package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.UnderlineTextSource

class UnderlineMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val rawSource: String
) : MessageEntity, TextSource by UnderlineTextSource(rawSource)
