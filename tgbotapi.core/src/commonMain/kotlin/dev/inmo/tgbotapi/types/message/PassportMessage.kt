package dev.inmo.tgbotapi.types.message

import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.FromUserMessage
import dev.inmo.tgbotapi.types.passport.PassportData
import korlibs.time.DateTime

data class PassportMessage(
    override val messageId: MessageId,
    override val chat: PreviewChat,
    override val from: User,
    override val date: DateTime,
    val passportData: PassportData,
) : AccessibleMessage, FromUserMessage
