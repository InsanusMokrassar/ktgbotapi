package dev.inmo.tgbotapi.types.message

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.chat.CommonBot
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.message.content.MessageContent
import korlibs.time.DateTime
import kotlinx.serialization.SerialName

data class PrivateContentMessageImpl<T : MessageContent>(
    override val messageId: MessageId,
    override val from: User,
    override val chat: PreviewPrivateChat,
    override val content: T,
    override val date: DateTime,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val forwardOrigin: MessageOrigin?,
    override val replyInfo: ReplyInfo?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val senderBot: CommonBot?,
    override val mediaGroupId: MediaGroupId?,
    override val fromOffline: Boolean,
    override val effectId: EffectId?,
    @SerialName(paidStarCountField)
    override val cost: Int? = null,
) : PrivateContentMessage<T> {
    constructor(
        messageId: MessageId,
        from: User,
        chat: PreviewPrivateChat,
        content: T,
        date: DateTime,
        editDate: DateTime?,
        hasProtectedContent: Boolean,
        forwardInfo: ForwardInfo,
        replyTo: AccessibleMessage?,
        replyMarkup: InlineKeyboardMarkup?,
        senderBot: CommonBot?,
        mediaGroupId: MediaGroupId?,
        fromOffline: Boolean,
        effectId: EffectId,
    ) : this(
        messageId, from, chat, content, date, editDate, hasProtectedContent, forwardInfo.messageOrigin(),
        replyTo ?.let {
            ReplyInfo.Internal(it)
        },
        replyMarkup, senderBot, mediaGroupId, fromOffline, effectId,
    )
}
