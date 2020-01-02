package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.User
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PrivateChat
import com.github.insanusmokrassar.TelegramBotAPI.utils.mentionHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.mentionMarkdown

class TextMentionTextSource(
    override val rawSource: String,
    privateChat: PrivateChat
) : TextSource {
    override val asMarkdownSource: String = rawSource.mentionMarkdown(privateChat.id)
    override val asHtmlSource: String = rawSource.mentionHTML(privateChat.id)
}
