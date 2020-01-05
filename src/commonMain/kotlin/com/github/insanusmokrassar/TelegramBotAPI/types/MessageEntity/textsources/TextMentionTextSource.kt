package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PrivateChat
import com.github.insanusmokrassar.TelegramBotAPI.utils.*

class TextMentionTextSource(
    source: String,
    privateChat: PrivateChat,
    textParts: List<TextPart>
) : MultilevelTextSource {
    override val textParts: List<TextPart> by lazy { source.fullListOfSubSource(textParts) }
    override val asMarkdownSource: String by lazy { source.textMentionMarkdown(privateChat.id) }
    override val asMarkdownV2Source: String by lazy { textMentionMarkdownV2(privateChat.id) }
    override val asHtmlSource: String by lazy { textMentionHTML(privateChat.id) }
}
