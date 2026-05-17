package dev.inmo.tgbotapi.types.message

import korlibs.time.DateTime
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.CommonBot
import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.GuestContentMessage
import dev.inmo.tgbotapi.types.message.content.MessageContent
import kotlinx.serialization.SerialName

data class GuestContentMessageImpl<T: MessageContent>(
    override val messageId: MessageId,
    override val from: User,
    override val chat: PreviewChat,
    override val guestQueryId: GuestQueryId,
    override val content: T,
    override val date: DateTime,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val forwardOrigin: MessageOrigin?,
    override val replyInfo: ReplyInfo?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val senderBot: CommonBot?,
    override val mediaGroupId: MediaGroupId?,
    override val guestBotCallerUser: User,
    override val guestBotCallerChat: PreviewChat,
    override val fromOffline: Boolean,
    @SerialName(paidMessageStarCountField)
    override val cost: Int? = null,
) : GuestContentMessage<T>
