package dev.inmo.tgbotapi.types.message

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.chat.CommonBot
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.BusinessContentMessage
import dev.inmo.tgbotapi.types.message.content.MessageContent
import korlibs.time.DateTime
import kotlinx.serialization.SerialName

data class BusinessContentMessageImpl<T : MessageContent>(
    override val messageId: MessageId,
    override val from: User,
    override val chat: PreviewBusinessChat,
    override val businessConnectionId: BusinessConnectionId,
    override val content: T,
    override val date: DateTime,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val forwardOrigin: MessageOrigin?,
    override val replyInfo: ReplyInfo?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val senderBot: CommonBot?,
    override val mediaGroupId: MediaGroupId?,
    override val senderBusinessBot: PreviewBot?,
    override val fromOffline: Boolean,
    @SerialName(paidMessageStarCountField)
    override val cost: Int? = null,
) : BusinessContentMessage<T> {
    constructor(
        messageId: MessageId,
        from: User,
        chat: PreviewBusinessChat,
        businessConnectionId: BusinessConnectionId,
        content: T,
        date: DateTime,
        editDate: DateTime?,
        hasProtectedContent: Boolean,
        forwardInfo: ForwardInfo,
        replyTo: AccessibleMessage?,
        replyMarkup: InlineKeyboardMarkup?,
        senderBot: CommonBot?,
        mediaGroupId: MediaGroupId?,
        senderBusinessBot: PreviewBot?,
        fromOffline: Boolean,
    ) : this(
        messageId = messageId,
        from = from,
        chat = chat,
        businessConnectionId = businessConnectionId,
        content = content,
        date = date,
        editDate = editDate,
        hasProtectedContent = hasProtectedContent,
        forwardOrigin = forwardInfo.messageOrigin(),
        replyInfo = replyTo ?.let { ReplyInfo.Internal(it) },
        replyMarkup = replyMarkup,
        senderBot = senderBot,
        mediaGroupId = mediaGroupId,
        senderBusinessBot = senderBusinessBot,
        fromOffline = fromOffline,
    )
}
