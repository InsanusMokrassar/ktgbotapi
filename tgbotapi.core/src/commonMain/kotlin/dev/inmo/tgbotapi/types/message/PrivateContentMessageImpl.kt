package dev.inmo.tgbotapi.types.message

import korlibs.time.DateTime
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.chat.CommonBot
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.message.content.MessageContent

data class PrivateContentMessageImpl<T: MessageContent>(
    override val messageId: MessageId,
    override val from: User,
    override val chat: PreviewPrivateChat,
    override val content: T,
    override val date: DateTime,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val forwardInfo: ForwardInfo?,
    override val replyTo: AccessibleMessage?,
    override val replyMarkup: InlineKeyboardMarkup?,
    override val senderBot: CommonBot?,
    override val mediaGroupId: MediaGroupIdentifier?,
) : PrivateContentMessage<T>
