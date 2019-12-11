package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.MentionTextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.mentionHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.mentionMarkdown

class MentionMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String
) : MessageEntity, TextSource by MentionTextSource(sourceString)
