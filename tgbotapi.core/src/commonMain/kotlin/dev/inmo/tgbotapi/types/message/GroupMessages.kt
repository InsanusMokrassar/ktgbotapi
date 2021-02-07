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
@Deprecated("Renamed due to ambiguity of naming", ReplaceWith("FromChannelGroupContentMessageImpl", "dev.inmo.tgbotapi.types.message.FromChannelGroupContentMessageImpl"))
typealias FromChannelGroupMessageImpl<T> = FromChannelGroupContentMessageImpl<T>

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
@Deprecated("Renamed due to ambiguity of naming", ReplaceWith("AnonymousGroupContentMessageImpl", "dev.inmo.tgbotapi.types.message.AnonymousGroupContentMessageImpl"))
typealias AnonymousGroupMessageImpl<T> = AnonymousGroupContentMessageImpl<T>

data class CommonGroupContentMessageImpl<T : MessageContent>(
    override val chat: GroupChat,
    override val messageId: MessageIdentifier,
    override val user: User,
    override val date: DateTime,
    override val forwardInfo: ForwardInfo?,
    override val editDate: DateTime?,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?
) : CommonGroupContentMessage<T>
@Deprecated("Renamed due to ambiguity of naming", ReplaceWith("CommonGroupContentMessageImpl", "dev.inmo.tgbotapi.types.message.CommonGroupContentMessageImpl"))
typealias CommonGroupMessageImpl<T> = CommonGroupContentMessageImpl<T>
