package dev.inmo.tgbotapi.types.message

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.ChannelChat
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent

data class ConnectedChannelContentMessageImpl<T: MessageContent>(
    override val messageId: MessageIdentifier,
    override val chat: ChannelChat,
    override val content: T,
    override val date: DateTime,
    override val editDate: DateTime?,
    override val forwardable: Boolean,
    override val forwardInfo: ForwardInfo?,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?
) : ConnectedChannelContentMessage<T> {
    @Deprecated("Use the constructor with forwardable field")
    constructor(
        messageId: MessageIdentifier,
        chat: ChannelChat,
        content: T,
        date: DateTime,
        editDate: DateTime?,
        forwardInfo: ForwardInfo?,
        replyTo: Message?,
        replyMarkup: InlineKeyboardMarkup?,
        senderBot: CommonBot?,
        authorSignature: AuthorSignature?
    ) : this(messageId, chat, content, date, editDate, true, forwardInfo, replyTo, replyMarkup, senderBot, authorSignature)
}
