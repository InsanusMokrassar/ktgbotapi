package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.CodeTextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.codeHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.codeMarkdown

data class CodeTextMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val rawSource: String
) : MessageEntity, TextSource by CodeTextSource(rawSource)
