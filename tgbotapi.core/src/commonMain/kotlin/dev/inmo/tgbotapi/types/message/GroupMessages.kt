package dev.inmo.tgbotapi.types.message

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.ChannelChat
import dev.inmo.tgbotapi.types.chat.abstracts.GroupChat
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent

data class FromChannelGroupMessageImpl<T : MessageContent>(
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
) : FromChannelGroupMessage<T>

data class AnonymousGroupMessageImpl<T : MessageContent>(
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
) : AnonymousGroupMessage<T>

data class CommonGroupMessageImpl<T : MessageContent>(
    override val chat: GroupChat,
    override val messageId: MessageIdentifier,
    override val date: DateTime,
    override val forwardInfo: ForwardInfo?,
    override val editDate: DateTime?,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val content: T,
    override val senderBot: CommonBot?
) : CommonGroupMessage<T>
