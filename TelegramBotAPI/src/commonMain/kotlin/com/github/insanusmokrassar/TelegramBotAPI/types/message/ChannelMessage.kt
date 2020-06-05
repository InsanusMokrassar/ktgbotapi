package com.github.insanusmokrassar.TelegramBotAPI.types.message

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MessageContent
import com.soywiz.klock.DateTime

data class ChannelMessage<T: MessageContent>(
    override val messageId: MessageIdentifier,
    override val chat: Chat,
    override val content: T,
    override val date: DateTime,
    override val editDate: DateTime?,
    override val forwardInfo: ForwardInfo?,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val senderBot: CommonBot?,
    val authorSignature: AuthorSignature?
) : CommonMessage<T>, PossiblySentViaBot
