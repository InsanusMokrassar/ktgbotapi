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

sealed class PublicForwardedMessage : ForwardedMessage() {
    abstract val messageId: MessageIdentifier
    abstract val from: User?
}

data class CommonForwardedMessage(
    override val messageId: MessageIdentifier,
    override val dateOfOriginal: TelegramDate,
    override val from: User
) : PublicForwardedMessage()

data class ForwardedFromChannelMessage(
    override val messageId: MessageIdentifier,
    override val dateOfOriginal: TelegramDate,
    override val from: User?,
    val channelChat: Chat,
    val signature: String? = null
) : PublicForwardedMessage()
