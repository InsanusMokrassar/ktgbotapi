package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.TextLinkTextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.linkHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.linkMarkdown

data class TextLinkMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val rawSource: String,
    val url: String
) : MessageEntity, TextSource by TextLinkTextSource(rawSource, url)
