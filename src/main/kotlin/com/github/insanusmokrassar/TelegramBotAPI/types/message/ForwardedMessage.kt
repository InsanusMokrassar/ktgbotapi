package com.github.insanusmokrassar.TelegramBotAPI.types.message

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat

sealed class ForwardedMessage {
    abstract val dateOfOriginal: TelegramDate
}

data class AnonymousForwardedMessage(
    override val dateOfOriginal: TelegramDate,
    val senderName: String
) : ForwardedMessage()

data class UserForwardedMessage(
    override val dateOfOriginal: TelegramDate,
    val from: User
) : ForwardedMessage()

@Deprecated(
    "Renamed according to correct meaning",
    ReplaceWith("UserForwardedMessage", "com.github.insanusmokrassar.TelegramBotAPI.types.message.UserForwardedMessage")
)
typealias CommonForwardedMessage = UserForwardedMessage

data class ForwardedFromChannelMessage(
    override val dateOfOriginal: TelegramDate,
    val messageId: MessageIdentifier,
    val channelChat: Chat,
    val signature: String? = null
) : ForwardedMessage()
