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
    override val replyInfo: ReplyInfo?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?,
    override val mediaGroupId: MediaGroupId?,
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
        mediaGroupId: MediaGroupId?,
    ) : this(
        chat, channel, messageId, date, forwardInfo.messageOrigin(), editDate, hasProtectedContent, replyTo ?.let { ReplyInfo.Internal(it) }, replyMarkup, content, senderBot, authorSignature, mediaGroupId
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
    override val replyInfo: ReplyInfo?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?,
    override val mediaGroupId: MediaGroupId?,
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
        mediaGroupId: MediaGroupId?,
    ) : this(
        chat, channel, messageId, date, forwardInfo.messageOrigin(), editDate, hasProtectedContent, replyTo ?.let { ReplyInfo.Internal(it) }, replyMarkup, content, senderBot, authorSignature, mediaGroupId
    )
}

data class AnonymousGroupContentMessageImpl<T : MessageContent>(
    override val chat: PreviewGroupChat,
    override val messageId: MessageId,
    override val date: DateTime,
    override val forwardOrigin: MessageOrigin?,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val replyInfo: ReplyInfo?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?,
    override val mediaGroupId: MediaGroupId?,
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
        mediaGroupId: MediaGroupId?,
    ) : this(
        chat, messageId, date, forwardInfo.messageOrigin(), editDate, hasProtectedContent, replyTo ?.let { ReplyInfo.Internal(it) }, replyMarkup, content, senderBot, authorSignature, mediaGroupId
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
    override val replyInfo: ReplyInfo?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val mediaGroupId: MediaGroupId?,
    override val senderBoostsCount: Int?
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
        mediaGroupId: MediaGroupId?,
        senderBoostsCount: Int?,
    ) : this(
        chat, messageId, from, date, forwardInfo.messageOrigin(), editDate, hasProtectedContent, replyTo ?.let { ReplyInfo.Internal(it) }, replyMarkup, content, senderBot, mediaGroupId, senderBoostsCount
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
    override val replyInfo: ReplyInfo?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?,
    override val mediaGroupId: MediaGroupId?,
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
        mediaGroupId: MediaGroupId?,
    ) : this(
        chat, channel, messageId, threadId, date, forwardInfo.messageOrigin(), editDate, hasProtectedContent, replyTo ?.let { ReplyInfo.Internal(it) }, replyMarkup, content, senderBot, authorSignature, mediaGroupId
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
    override val replyInfo: ReplyInfo?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?,
    override val mediaGroupId: MediaGroupId?,
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
        mediaGroupId: MediaGroupId?,
    ) : this(
        chat, messageId, threadId, date, forwardInfo.messageOrigin(), editDate, hasProtectedContent, replyTo ?.let { ReplyInfo.Internal(it) }, replyMarkup, content, senderBot, authorSignature, mediaGroupId
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
    override val replyInfo: ReplyInfo?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val mediaGroupId: MediaGroupId?,
    override val senderBoostsCount: Int?,
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
        mediaGroupId: MediaGroupId?,
        senderBoostsCount: Int?,
    ) : this(
        chat, messageId, threadId, from, date, forwardInfo.messageOrigin(), editDate, hasProtectedContent, replyTo ?.let { ReplyInfo.Internal(it) }, replyMarkup, content, senderBot, mediaGroupId, senderBoostsCount
    )
}
