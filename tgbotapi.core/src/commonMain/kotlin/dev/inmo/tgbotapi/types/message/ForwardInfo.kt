package dev.inmo.tgbotapi.types.message

import dev.inmo.tgbotapi.abstracts.FromUser
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.chat.User

sealed interface ForwardInfo {
    abstract val dateOfOriginal: TelegramDate
}

data class AnonymousForwardInfo(
    override val dateOfOriginal: TelegramDate,
    val senderName: String
) : ForwardInfo

data class UserForwardInfo(
    override val dateOfOriginal: TelegramDate,
    override val from: User
) : ForwardInfo, FromUser

sealed interface ForwardFromPublicChatInfo : ForwardInfo {
    val chat: PublicChat

    /**
     * Represent forward info for the message sent by [channelChat] into some group
     */
    data class SentByChannel(
        override val dateOfOriginal: TelegramDate,
        val channelChat: ChannelChat,
        val signature: String? = null
    ) : ForwardFromPublicChatInfo {
        override val chat: PublicChat
            get() = channelChat
    }

    /**
     * Represent forward info for the message sent by [channelChat] into that channel
     */
    data class FromChannel(
        override val dateOfOriginal: TelegramDate,
        val messageId: MessageIdentifier,
        val channelChat: ChannelChat,
        val signature: String? = null
    ) : ForwardFromPublicChatInfo {
        override val chat: PublicChat
            get() = channelChat
    }

    data class FromSupergroup(
        override val dateOfOriginal: TelegramDate,
        val group: SupergroupChat
    ) : ForwardFromPublicChatInfo {
        override val chat: PublicChat
            get() = group
    }
}

@Deprecated("Replaced", ReplaceWith("ForwardFromPublicChatInfo.FromChannel", "dev.inmo.tgbotapi.types.message.FromChannel"))
typealias ForwardFromChannelInfo = ForwardFromPublicChatInfo.FromChannel

@Deprecated("Replaced", ReplaceWith("ForwardFromPublicChatInfo.FromSupergroup", "dev.inmo.tgbotapi.types.message.ForwardFromPublicChatInfo"))
typealias ForwardFromSupergroupInfo = ForwardFromPublicChatInfo.FromSupergroup
