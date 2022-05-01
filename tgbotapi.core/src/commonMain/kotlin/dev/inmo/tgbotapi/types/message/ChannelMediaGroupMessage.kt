package dev.inmo.tgbotapi.types.message

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.types.MediaGroupIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent

data class ChannelMediaGroupMessage<T : MediaGroupContent>(
    override val messageId: MessageIdentifier,
    override val chat: Chat,
    override val date: DateTime,
    override val mediaGroupId: MediaGroupIdentifier,
    override val content: T,
    override val editDate: DateTime?,
    override val hasProtectedContent: Boolean,
    override val forwardInfo: ForwardInfo?,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?
) : MediaGroupMessage<T>
