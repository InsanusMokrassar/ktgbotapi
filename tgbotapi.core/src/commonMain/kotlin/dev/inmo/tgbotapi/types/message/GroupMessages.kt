package dev.inmo.tgbotapi.types.message

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.ChannelChat
import dev.inmo.tgbotapi.types.chat.abstracts.GroupChat
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent

data class FromChannelGroupContentMessageImpl<T : MessageContent>(
    override val chat: GroupChat,
    override val channel: ChannelChat,
    override val messageId: MessageIdentifier,
    override val date: DateTime,
    override val forwardInfo: ForwardInfo?,
    override val editDate: DateTime?,
    override val forwardable: Boolean,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?
) : FromChannelGroupContentMessage<T> {
    @Deprecated("Use the constructor with forwardable field")
    constructor(
        chat: GroupChat,
        channel: ChannelChat,
        messageId: MessageIdentifier,
        date: DateTime,
        forwardInfo: ForwardInfo?,
        editDate: DateTime?,
        replyTo: Message?,
        replyMarkup: InlineKeyboardMarkup?,
        content: T,
        senderBot: CommonBot?,
        authorSignature: AuthorSignature?
    ) : this(chat, channel, messageId, date, forwardInfo, editDate, true, replyTo, replyMarkup, content, senderBot, authorSignature)
}

data class AnonymousGroupContentMessageImpl<T : MessageContent>(
    override val chat: GroupChat,
    override val messageId: MessageIdentifier,
    override val date: DateTime,
    override val forwardInfo: ForwardInfo?,
    override val editDate: DateTime?,
    override val forwardable: Boolean,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?
) : AnonymousGroupContentMessage<T> {
    @Deprecated("Use the constructor with forwardable field")
    constructor(
        chat: GroupChat,
        messageId: MessageIdentifier,
        date: DateTime,
        forwardInfo: ForwardInfo?,
        editDate: DateTime?,
        replyTo: Message?,
        replyMarkup: InlineKeyboardMarkup?,
        content: T,
        senderBot: CommonBot?,
        authorSignature: AuthorSignature?
    ) : this(chat, messageId, date, forwardInfo, editDate, true, replyTo, replyMarkup, content, senderBot, authorSignature)
}

data class CommonGroupContentMessageImpl<T : MessageContent>(
    override val chat: GroupChat,
    override val messageId: MessageIdentifier,
    override val from: User,
    override val date: DateTime,
    override val forwardInfo: ForwardInfo?,
    override val editDate: DateTime?,
    override val forwardable: Boolean,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?
) : CommonGroupContentMessage<T> {
    @Deprecated("Use the constructor with forwardable field")
    constructor(
        chat: GroupChat,
        messageId: MessageIdentifier,
        from: User,
        date: DateTime,
        forwardInfo: ForwardInfo?,
        editDate: DateTime?,
        replyTo: Message?,
        replyMarkup: InlineKeyboardMarkup?,
        content: T,
        senderBot: CommonBot?
    ) : this(chat, messageId, from, date, forwardInfo, editDate, true, replyTo, replyMarkup, content, senderBot)
}
