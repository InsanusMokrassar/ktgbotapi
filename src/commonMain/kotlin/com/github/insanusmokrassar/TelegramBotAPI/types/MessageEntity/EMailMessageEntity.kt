package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.EMailTextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.emailHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.emailMarkdown

data class EMailMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val rawSource: String
) : MessageEntity, TextSource by EMailTextSource(rawSource)
