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
