package dev.inmo.tgbotapi.types.message

import dev.inmo.tgbotapi.CommonAbstracts.FromUser
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.chat.User

sealed class ForwardInfo {
    abstract val dateOfOriginal: TelegramDate
}

data class AnonymousForwardInfo(
    override val dateOfOriginal: TelegramDate,
    val senderName: String
) : ForwardInfo()

data class UserForwardInfo(
    override val dateOfOriginal: TelegramDate,
    override val from: User
) : ForwardInfo(), FromUser

sealed class ForwardFromPublicChatInfo : ForwardInfo() {
    abstract val chat: PublicChat
}

data class ForwardFromChannelInfo(
    override val dateOfOriginal: TelegramDate,
    val messageId: MessageIdentifier,
    val channelChat: ChannelChat,
    val signature: String? = null
) : ForwardFromPublicChatInfo() {
    override val chat: PublicChat
        get() = channelChat
}

data class ForwardFromSupergroupInfo(
    override val dateOfOriginal: TelegramDate,
    val group: SupergroupChat
) : ForwardFromPublicChatInfo() {
    override val chat: PublicChat
        get() = group
}
