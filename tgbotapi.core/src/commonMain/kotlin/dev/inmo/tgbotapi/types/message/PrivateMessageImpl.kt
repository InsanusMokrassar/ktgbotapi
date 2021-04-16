package dev.inmo.tgbotapi.types.message

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent
import dev.inmo.tgbotapi.types.message.payments.SuccessfulPaymentInfo

data class PrivateContentMessageImpl<T: MessageContent>(
    override val messageId: MessageIdentifier,
    override val user: User,
    override val chat: Chat,
    override val content: T,
    override val date: DateTime,
    override val editDate: DateTime?,
    override val forwardInfo: ForwardInfo?,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val senderBot: CommonBot?,
    val paymentInfo: SuccessfulPaymentInfo?
) : PrivateContentMessage<T>
