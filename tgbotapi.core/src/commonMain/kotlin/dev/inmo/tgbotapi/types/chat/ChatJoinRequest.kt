package dev.inmo.tgbotapi.types.chat

import korlibs.time.DateTime
import dev.inmo.tgbotapi.abstracts.FromUser
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represent a [ChatJoinRequest](https://core.telegram.org/bots/api#chatjoinrequest)
 */
@Serializable
data class ChatJoinRequest(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(chatField)
    val chat: PublicChat,
    @SerialName(fromField)
    override val from: User,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userChatIdField)
    val userChatId: UserId,
    @SerialName(dateField)
    val date: TelegramDate,
    @SerialName(inviteLinkField)
    val inviteLink: ChatInviteLink? = null,
    @SerialName(bioField)
    val bio: String? = null
) : FromUser {
    @Suppress("unused")
    val dateTime: DateTime
        get() = date.asDate
}
