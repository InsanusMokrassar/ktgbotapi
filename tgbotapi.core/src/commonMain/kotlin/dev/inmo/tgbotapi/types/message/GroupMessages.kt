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
    override val chat: GroupChat,
    override val channel: ChannelChat,
    override val messageId: MessageId,
    override val date: DateTime,
    override val forwardInfo: ForwardInfo?,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?,
    override val mediaGroupId: MediaGroupIdentifier?,
) : ConnectedFromChannelGroupContentMessage<T>

data class UnconnectedFromChannelGroupContentMessageImpl<T: MessageContent>(
    override val chat: GroupChat,
    override val channel: ChannelChat,
    override val messageId: MessageId,
    override val date: DateTime,
    override val forwardInfo: ForwardInfo?,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?,
    override val mediaGroupId: MediaGroupIdentifier?,
) : UnconnectedFromChannelGroupContentMessage<T>

data class AnonymousGroupContentMessageImpl<T : MessageContent>(
    override val chat: GroupChat,
    override val messageId: MessageId,
    override val date: DateTime,
    override val forwardInfo: ForwardInfo?,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?,
    override val mediaGroupId: MediaGroupIdentifier?,
) : AnonymousGroupContentMessage<T>

data class CommonGroupContentMessageImpl<T : MessageContent>(
    override val chat: GroupChat,
    override val messageId: MessageId,
    override val from: User,
    override val date: DateTime,
    override val forwardInfo: ForwardInfo?,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val mediaGroupId: MediaGroupIdentifier?,
) : CommonGroupContentMessage<T>

data class FromChannelForumContentMessageImpl<T: MessageContent>(
    override val chat: ForumChat,
    override val channel: ChannelChat,
    override val messageId: MessageId,
    override val threadId: MessageThreadId,
    override val date: DateTime,
    override val forwardInfo: ForwardInfo?,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?,
    override val mediaGroupId: MediaGroupIdentifier?,
) : FromChannelForumContentMessage<T>

data class AnonymousForumContentMessageImpl<T : MessageContent>(
    override val chat: ForumChat,
    override val messageId: MessageId,
    override val threadId: MessageThreadId,
    override val date: DateTime,
    override val forwardInfo: ForwardInfo?,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?,
    override val mediaGroupId: MediaGroupIdentifier?,
) : AnonymousForumContentMessage<T>

data class CommonForumContentMessageImpl<T : MessageContent>(
    override val chat: ForumChat,
    override val messageId: MessageId,
    override val threadId: MessageThreadId,
    override val from: User,
    override val date: DateTime,
    override val forwardInfo: ForwardInfo?,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val mediaGroupId: MediaGroupIdentifier?,
) : CommonForumContentMessage<T>
