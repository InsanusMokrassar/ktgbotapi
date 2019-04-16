package com.github.insanusmokrassar.TelegramBotAPI.types.message

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.User
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MessageContent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.payments.abstracts.PaymentInfo
import org.joda.time.DateTime

data class CommonMessageImpl<T: MessageContent>(
    override val messageId: MessageIdentifier,
    override val user: User,
    override val chat: Chat,
    override val content: T,
    override val date: DateTime,
    override val editDate: DateTime?,
    override val forwarded: ForwardedMessage?,
    override val replyTo: Message?,
    val paymentInfo: PaymentInfo?
) : Message, CommonMessage<T>, FromUserMessage