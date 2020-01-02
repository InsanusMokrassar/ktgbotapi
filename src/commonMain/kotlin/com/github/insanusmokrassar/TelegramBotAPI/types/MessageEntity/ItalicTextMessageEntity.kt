package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.ItalicTextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.italicHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.italicMarkdown

data class ItalicTextMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val rawSource: String
) : MessageEntity, TextSource by ItalicTextSource(rawSource)
