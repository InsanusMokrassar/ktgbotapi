package dev.inmo.tgbotapi.types.message

import dev.inmo.tgbotapi.abstracts.FromUser
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.chat.User

@ClassCastsIncluded
sealed interface ForwardInfo {
    abstract val dateOfOriginal: TelegramDate

    data class ByAnonymous(
        override val dateOfOriginal: TelegramDate,
        val senderName: String
    ) : ForwardInfo

    data class ByUser(
        override val dateOfOriginal: TelegramDate,
        override val from: User
    ) : ForwardInfo, FromUser

    @ClassCastsIncluded.ExcludeSubName
    sealed interface PublicChat : ForwardInfo {
        val chat: dev.inmo.tgbotapi.types.chat.PublicChat

        /**
         * Represent forward info for the message sent by [channelChat] into some group
         */
        data class SentByChannel(
            override val dateOfOriginal: TelegramDate,
            val channelChat: ChannelChat,
            val signature: String? = null
        ) : PublicChat {
            override val chat: dev.inmo.tgbotapi.types.chat.PublicChat
                get() = channelChat
        }

        /**
         * Represent forward info for the message sent by [channelChat] into that channel
         */
        data class FromChannel(
            override val dateOfOriginal: TelegramDate,
            val messageId: MessageId,
            val channelChat: ChannelChat,
            val signature: String? = null
        ) : PublicChat {
            override val chat: dev.inmo.tgbotapi.types.chat.PublicChat
                get() = channelChat
        }

        data class FromSupergroup(
            override val dateOfOriginal: TelegramDate,
            val group: SupergroupChat
        ) : PublicChat {
            override val chat: dev.inmo.tgbotapi.types.chat.PublicChat
                get() = group
        }
    }
}

fun MessageOrigin.forwardInfo() = when(this) {
    is MessageOrigin.HiddenUser -> ForwardInfo.ByAnonymous(
        date,
        name
    )
    is MessageOrigin.Public.Channel -> ForwardInfo.PublicChat.FromChannel(
        date,
        messageId,
        chat,
        authorSignature
    )
    is MessageOrigin.Public.Sender -> when (chat) {
        is ChannelChat -> ForwardInfo.PublicChat.SentByChannel(
            date,
            chat
        )
        is SupergroupChat -> ForwardInfo.PublicChat.FromSupergroup(
            date,
            chat
        )
    }
    is MessageOrigin.User -> ForwardInfo.ByUser(
        date,
        user
    )
    is MessageOrigin.Unknown -> null
}

fun ForwardInfo.messageOrigin() = when (this) {
    is ForwardInfo.ByAnonymous -> MessageOrigin.HiddenUser(
        senderName,
        dateOfOriginal
    )
    is ForwardInfo.ByUser -> MessageOrigin.User(
        user,
        dateOfOriginal
    )
    is ForwardInfo.PublicChat.FromChannel -> MessageOrigin.Public.Channel(
        channelChat,
        messageId,
        dateOfOriginal,
        signature
    )
    is ForwardInfo.PublicChat.FromSupergroup -> MessageOrigin.Public.Sender(
        group,
        dateOfOriginal
    )
    is ForwardInfo.PublicChat.SentByChannel -> MessageOrigin.Public.Sender(
        channelChat,
        dateOfOriginal
    )
}
