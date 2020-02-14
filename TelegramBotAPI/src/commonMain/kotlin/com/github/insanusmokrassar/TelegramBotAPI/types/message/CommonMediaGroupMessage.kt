package com.github.insanusmokrassar.TelegramBotAPI.types.message

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MediaGroupContent
import com.soywiz.klock.DateTime

data class CommonMediaGroupMessage(
    override val messageId: MessageIdentifier,
    override val user: User,
    override val chat: Chat,
    override val date: DateTime,
    override val mediaGroupId: MediaGroupIdentifier,
    override val content: MediaGroupContent,
    override val editDate: DateTime?,
    override val forwardInfo: ForwardInfo?,
    override val replyTo: Message?,
    override val replyMarkup: InlineKeyboardMarkup?
) : MediaGroupMessage, FromUserMessage
