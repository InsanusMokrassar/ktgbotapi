package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.TextMentionTextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.User
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PrivateChat
import com.github.insanusmokrassar.TelegramBotAPI.utils.mentionHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.mentionMarkdown

class TextMentionMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String,
    val privateChat: PrivateChat
) : MessageEntity, TextSource by TextMentionTextSource(sourceString, privateChat) {
    @Deprecated("Deprecated due to the fact that there is more common constructor")
    constructor(
        offset: Int,
        length: Int,
        sourceString: String,
        user: User
    ) : this(offset, length, sourceString, user as PrivateChat)
}
