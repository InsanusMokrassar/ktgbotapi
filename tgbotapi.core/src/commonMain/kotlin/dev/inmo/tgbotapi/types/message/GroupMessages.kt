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
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?
) : FromChannelGroupContentMessage<T>

data class AnonymousGroupContentMessageImpl<T : MessageContent>(
    override val chat: GroupChat,
    override val messageId: MessageIdentifier,
    override val date: DateTime,
    override val forwardInfo: ForwardInfo?,
    override val editDate: DateTime?,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?
) : AnonymousGroupContentMessage<T>

data class CommonGroupContentMessageImpl<T : MessageContent>(
    override val chat: GroupChat,
    override val messageId: MessageIdentifier,
    override val from: User,
    override val date: DateTime,
    override val forwardInfo: ForwardInfo?,
    override val editDate: DateTime?,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?
) : CommonGroupContentMessage<T>
