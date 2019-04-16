package com.github.insanusmokrassar.TelegramBotAPI.types.message

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat

sealed class ForwardedMessage {
    abstract val messageId: MessageIdentifier
    abstract val dateOfOriginal: TelegramDate
    abstract val from: User?
}

data class CommonForwardedMessage(
    override val messageId: MessageIdentifier,
    override val dateOfOriginal: TelegramDate,
    override val from: User
) : ForwardedMessage()

data class ForwardedFromChannelMessage(
    override val messageId: MessageIdentifier,
    override val dateOfOriginal: TelegramDate,
    override val from: User?,
    val channelChat: Chat,
    val signature: String? = null
) : ForwardedMessage()
