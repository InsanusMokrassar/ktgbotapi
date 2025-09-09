package dev.inmo.tgbotapi.types.message

import korlibs.time.DateTime
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.CommonBot
import dev.inmo.tgbotapi.types.chat.PreviewChannelChat
import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.content.MessageContent
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.SerialName

data class ChannelPaidPostImpl<T: MessageContent>(
    override val messageId: MessageId,
    override val chat: PreviewChannelChat,
    override val senderChat: PreviewChat,
    override val content: T,
    override val date: DateTime,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val forwardOrigin: MessageOrigin?,
    override val replyInfo: ReplyInfo?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val senderBot: CommonBot?,
    override val authorSignature: AuthorSignature?,
    override val mediaGroupId: MediaGroupId?,
    override val fromOffline: Boolean,
    @SerialName(paidMessageStarCountField)
    override val cost: Int? = null,
) : ChannelPaidPost<T> {
    @SerialName(isPaidPostField)
    @EncodeDefault
    override val isPaidPost: Boolean = true
    constructor(
        messageId: MessageId,
        chat: PreviewChannelChat,
        senderChat: PreviewChat,
        content: T,
        date: DateTime,
        editDate: DateTime?,
        hasProtectedContent: Boolean,
        forwardInfo: ForwardInfo,
        replyTo: AccessibleMessage?,
        replyMarkup: InlineKeyboardMarkup?,
        senderBot: CommonBot?,
        authorSignature: AuthorSignature?,
        mediaGroupId: MediaGroupId?,
        fromOffline: Boolean,
    ) : this(
        messageId, chat, senderChat, content, date, editDate, hasProtectedContent, forwardInfo.messageOrigin(), replyTo ?.let { ReplyInfo.Internal(it) }, replyMarkup, senderBot, authorSignature, mediaGroupId, fromOffline
    )
}
