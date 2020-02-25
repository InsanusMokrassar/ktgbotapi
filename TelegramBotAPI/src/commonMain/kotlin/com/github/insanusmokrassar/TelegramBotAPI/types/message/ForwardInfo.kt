package com.github.insanusmokrassar.TelegramBotAPI.types.message

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.ChannelChat
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat

@Deprecated(
    "Renamed",
    ReplaceWith(
        "ForwardInfo",
        "com.github.insanusmokrassar.TelegramBotAPI.types.message.ForwardInfo"
    )
)
typealias ForwardedMessage = ForwardInfo
sealed class ForwardInfo {
    abstract val dateOfOriginal: TelegramDate
}

@Deprecated(
    "Renamed",
    ReplaceWith(
        "AnonymousForwardInfo",
        "com.github.insanusmokrassar.TelegramBotAPI.types.message.AnonymousForwardInfo"
    )
)
typealias AnonymousForwardedMessage = AnonymousForwardInfo
data class AnonymousForwardInfo(
    override val dateOfOriginal: TelegramDate,
    val senderName: String
) : ForwardInfo()

@Deprecated(
    "Renamed",
    ReplaceWith(
        "UserForwardInfo",
        "com.github.insanusmokrassar.TelegramBotAPI.types.message.UserForwardInfo"
    )
)
typealias UserForwardedMessage = UserForwardInfo
data class UserForwardInfo(
    override val dateOfOriginal: TelegramDate,
    val from: User
) : ForwardInfo()

@Deprecated(
    "Renamed",
    ReplaceWith(
        "ForwardFromChannelInfo",
        "com.github.insanusmokrassar.TelegramBotAPI.types.message.ForwardFromChannelInfo"
    )
)
typealias ForwardedFromChannelMessage = ForwardFromChannelInfo
data class ForwardFromChannelInfo(
    override val dateOfOriginal: TelegramDate,
    val messageId: MessageIdentifier,
    val channelChat: ChannelChat,
    val signature: String? = null
) : ForwardInfo()
