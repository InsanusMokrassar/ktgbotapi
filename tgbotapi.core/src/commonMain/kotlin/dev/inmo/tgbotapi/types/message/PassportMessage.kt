package dev.inmo.tgbotapi.types.message

import korlibs.time.DateTime
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.FromUserMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.passport.PassportData

data class PassportMessage(
    override val messageId: MessageId,
    override val chat: Chat,
    override val from: User,
    override val date: DateTime,
    val passportData: PassportData
) : Message, FromUserMessage
