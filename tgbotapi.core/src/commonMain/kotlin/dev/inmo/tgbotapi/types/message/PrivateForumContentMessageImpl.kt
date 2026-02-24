package dev.inmo.tgbotapi.types.message

import korlibs.time.DateTime
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.chat.CommonBot
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicCreated
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.PrivateForumContentMessage
import dev.inmo.tgbotapi.types.message.content.MessageContent
import kotlinx.serialization.SerialName

data class PrivateForumContentMessageImpl<T: MessageContent>(
    override val messageId: MessageId,
    override val threadId: MessageThreadId,
    override val threadCreatingInfo: ForumTopicCreated?,
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
    override val cost: Int? = null
) : PrivateForumContentMessage<T> {
}
