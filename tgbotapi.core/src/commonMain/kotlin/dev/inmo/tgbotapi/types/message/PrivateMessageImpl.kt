package dev.inmo.tgbotapi.types.message

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent

data class PrivateContentMessageImpl<T: MessageContent>(
    override val messageId: MessageIdentifier,
    override val from: User,
    override val chat: Chat,
    override val content: T,
    override val date: DateTime,
    override val editDate: DateTime?,
    override val forwardable: Boolean,
    override val forwardInfo: ForwardInfo?,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val senderBot: CommonBot?
) : PrivateContentMessage<T> {
    @Deprecated("Use the constructor with forwardable field")
    constructor(
        messageId: MessageIdentifier,
        from: User,
        chat: Chat,
        content: T,
        date: DateTime,
        editDate: DateTime?,
        forwardInfo: ForwardInfo?,
        replyTo: Message?,
        replyMarkup: InlineKeyboardMarkup?,
        senderBot: CommonBot?
    ) : this(messageId, from, chat, content, date, editDate, true, forwardInfo, replyTo, replyMarkup, senderBot)
}
