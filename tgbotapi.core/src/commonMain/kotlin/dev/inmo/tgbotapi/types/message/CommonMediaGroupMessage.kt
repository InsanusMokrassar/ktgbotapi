package dev.inmo.tgbotapi.types.message

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaGroupContent

data class CommonMediaGroupMessage<T : MediaGroupContent>(
    override val messageId: MessageIdentifier,
    override val from: User,
    override val chat: Chat,
    override val date: DateTime,
    override val mediaGroupId: MediaGroupIdentifier,
    override val content: T,
    override val editDate: DateTime?,
    override val forwardInfo: ForwardInfo?,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?
) : MediaGroupMessage<T>, FromUserMessage
