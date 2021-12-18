package dev.inmo.tgbotapi.types.message

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.ChannelChat
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent

data class UnconnectedChannelContentMessageImpl<T: MessageContent>(
    override val messageId: MessageIdentifier,
    override val chat: ChannelChat,
    override val content: T,
    override val date: DateTime,
    override val editDate: DateTime?,
    override val forwardInfo: ForwardInfo?,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?
) : UnconnectedChannelContentMessage<T>

@Deprecated("Renamed to UnconnectedChannelContentMessage", ReplaceWith("UnconnectedChannelContentMessage", "dev.inmo.tgbotapi.types.message.UnconnectedChannelContentMessageImpl"))
typealias ChannelContentMessageImpl<T> = UnconnectedChannelContentMessage<T>
