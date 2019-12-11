package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.User
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PrivateChat
import com.github.insanusmokrassar.TelegramBotAPI.utils.mentionHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.mentionMarkdown

class TextMentionTextSource(
    sourceString: String,
    privateChat: PrivateChat
) : TextSource {
    @Deprecated("Deprecated due to the fact that there is more common constructor")
    constructor(
        sourceString: String,
        user: User
    ) : this(sourceString, user as PrivateChat)

    override val asMarkdownSource: String = sourceString.mentionMarkdown(privateChat.id)
    override val asHtmlSource: String = sourceString.mentionHTML(privateChat.id)
}
