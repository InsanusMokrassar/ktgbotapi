package dev.inmo.tgbotapi.types.message

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.FromUserMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.passport.PassportData

data class PassportMessage(
    override val messageId: MessageIdentifier,
    override val chat: Chat,
    override val from: User,
    override val date: DateTime,
    val passportData: PassportData
) : Message, FromUserMessage
