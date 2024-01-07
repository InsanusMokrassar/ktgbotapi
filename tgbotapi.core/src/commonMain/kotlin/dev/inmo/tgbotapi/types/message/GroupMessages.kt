package dev.inmo.tgbotapi.types.message

import korlibs.time.DateTime
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.chat.CommonBot
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.content.MessageContent

data class ConnectedFromChannelGroupContentMessageImpl<T : MessageContent>(
    override val chat: PreviewGroupChat,
    override val channel: PreviewChannelChat,
    override val messageId: MessageId,
    override val date: DateTime,
    override val forwardOrigin: MessageOrigin?,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val replyTo: AccessibleMessage?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?,
    override val mediaGroupId: MediaGroupIdentifier?,
) : ConnectedFromChannelGroupContentMessage<T> {

    constructor(
        chat: PreviewGroupChat,
        channel: PreviewChannelChat,
        messageId: MessageId,
        date: DateTime,
        forwardInfo: ForwardInfo,
        editDate: DateTime?,
        hasProtectedContent: Boolean,
        replyTo: AccessibleMessage?,
        replyMarkup: InlineKeyboardMarkup?,
        content: T,
        senderBot: CommonBot?,
        authorSignature: AuthorSignature?,
        mediaGroupId: MediaGroupIdentifier?,
    ) : this(
        chat, channel, messageId, date, forwardInfo.messageOrigin(), editDate, hasProtectedContent, replyTo, replyMarkup, content, senderBot, authorSignature, mediaGroupId
    )
}

data class UnconnectedFromChannelGroupContentMessageImpl<T: MessageContent>(
    override val chat: PreviewGroupChat,
    override val channel: PreviewChannelChat,
    override val messageId: MessageId,
    override val date: DateTime,
    override val forwardOrigin: MessageOrigin?,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val replyTo: AccessibleMessage?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?,
    override val mediaGroupId: MediaGroupIdentifier?,
) : UnconnectedFromChannelGroupContentMessage<T> {
    constructor(
        chat: PreviewGroupChat,
        channel: PreviewChannelChat,
        messageId: MessageId,
        date: DateTime,
        forwardInfo: ForwardInfo,
        editDate: DateTime?,
        hasProtectedContent: Boolean,
        replyTo: AccessibleMessage?,
        replyMarkup: InlineKeyboardMarkup?,
        content: T,
        senderBot: CommonBot?,
        authorSignature: AuthorSignature?,
        mediaGroupId: MediaGroupIdentifier?,
    ) : this(
        chat, channel, messageId, date, forwardInfo.messageOrigin(), editDate, hasProtectedContent, replyTo, replyMarkup, content, senderBot, authorSignature, mediaGroupId
    )
}

data class AnonymousGroupContentMessageImpl<T : MessageContent>(
    override val chat: PreviewGroupChat,
    override val messageId: MessageId,
    override val date: DateTime,
    override val forwardOrigin: MessageOrigin?,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val replyTo: AccessibleMessage?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?,
    override val mediaGroupId: MediaGroupIdentifier?,
) : AnonymousGroupContentMessage<T> {
    constructor(
        chat: PreviewGroupChat,
        messageId: MessageId,
        date: DateTime,
        forwardInfo: ForwardInfo,
        editDate: DateTime?,
        hasProtectedContent: Boolean,
        replyTo: AccessibleMessage?,
        replyMarkup: InlineKeyboardMarkup?,
        content: T,
        senderBot: CommonBot?,
        authorSignature: AuthorSignature?,
        mediaGroupId: MediaGroupIdentifier?,
    ) : this(
        chat, messageId, date, forwardInfo.messageOrigin(), editDate, hasProtectedContent, replyTo, replyMarkup, content, senderBot, authorSignature, mediaGroupId
    )
}

data class CommonGroupContentMessageImpl<T : MessageContent>(
    override val chat: PreviewGroupChat,
    override val messageId: MessageId,
    override val from: User,
    override val date: DateTime,
    override val forwardOrigin: MessageOrigin?,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val replyTo: AccessibleMessage?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val mediaGroupId: MediaGroupIdentifier?,
) : CommonGroupContentMessage<T> {
    constructor(
        chat: PreviewGroupChat,
        messageId: MessageId,
        from: User,
        date: DateTime,
        forwardInfo: ForwardInfo,
        editDate: DateTime?,
        hasProtectedContent: Boolean,
        replyTo: AccessibleMessage?,
        replyMarkup: InlineKeyboardMarkup?,
        content: T,
        senderBot: CommonBot?,
        mediaGroupId: MediaGroupIdentifier?,
    ) : this(
        chat, messageId, from, date, forwardInfo.messageOrigin(), editDate, hasProtectedContent, replyTo, replyMarkup, content, senderBot, mediaGroupId
    )
}

data class FromChannelForumContentMessageImpl<T: MessageContent>(
    override val chat: PreviewForumChat,
    override val channel: PreviewChannelChat,
    override val messageId: MessageId,
    override val threadId: MessageThreadId,
    override val date: DateTime,
    override val forwardOrigin: MessageOrigin?,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val replyTo: AccessibleMessage?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?,
    override val mediaGroupId: MediaGroupIdentifier?,
) : FromChannelForumContentMessage<T> {
    constructor(
        chat: PreviewForumChat,
        channel: PreviewChannelChat,
        messageId: MessageId,
        threadId: MessageThreadId,
        date: DateTime,
        forwardInfo: ForwardInfo,
        editDate: DateTime?,
        hasProtectedContent: Boolean,
        replyTo: AccessibleMessage?,
        replyMarkup: InlineKeyboardMarkup?,
        content: T,
        senderBot: CommonBot?,
        authorSignature: AuthorSignature?,
        mediaGroupId: MediaGroupIdentifier?,
    ) : this(
        chat, channel, messageId, threadId, date, forwardInfo.messageOrigin(), editDate, hasProtectedContent, replyTo, replyMarkup, content, senderBot, authorSignature, mediaGroupId
    )
}

data class AnonymousForumContentMessageImpl<T : MessageContent>(
    override val chat: PreviewForumChat,
    override val messageId: MessageId,
    override val threadId: MessageThreadId,
    override val date: DateTime,
    override val forwardOrigin: MessageOrigin?,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val replyTo: AccessibleMessage?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?,
    override val mediaGroupId: MediaGroupIdentifier?,
) : AnonymousForumContentMessage<T> {
    constructor(
        chat: PreviewForumChat,
        messageId: MessageId,
        threadId: MessageThreadId,
        date: DateTime,
        forwardInfo: ForwardInfo,
        editDate: DateTime?,
        hasProtectedContent: Boolean,
        replyTo: AccessibleMessage?,
        replyMarkup: InlineKeyboardMarkup?,
        content: T,
        senderBot: CommonBot?,
        authorSignature: AuthorSignature?,
        mediaGroupId: MediaGroupIdentifier?,
    ) : this(
        chat, messageId, threadId, date, forwardInfo.messageOrigin(), editDate, hasProtectedContent, replyTo, replyMarkup, content, senderBot, authorSignature, mediaGroupId
    )
}

data class CommonForumContentMessageImpl<T : MessageContent>(
    override val chat: PreviewForumChat,
    override val messageId: MessageId,
    override val threadId: MessageThreadId,
    override val from: User,
    override val date: DateTime,
    override val forwardOrigin: MessageOrigin?,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val replyTo: AccessibleMessage?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val mediaGroupId: MediaGroupIdentifier?,
) : CommonForumContentMessage<T> {
    constructor(
        chat: PreviewForumChat,
        messageId: MessageId,
        threadId: MessageThreadId,
        from: User,
        date: DateTime,
        forwardInfo: ForwardInfo,
        editDate: DateTime?,
        hasProtectedContent: Boolean,
        replyTo: AccessibleMessage?,
        replyMarkup: InlineKeyboardMarkup?,
        content: T,
        senderBot: CommonBot?,
        mediaGroupId: MediaGroupIdentifier?,
    ) : this(
        chat, messageId, threadId, from, date, forwardInfo.messageOrigin(), editDate, hasProtectedContent, replyTo, replyMarkup, content, senderBot, mediaGroupId
    )
}
