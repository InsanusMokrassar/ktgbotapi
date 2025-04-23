package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.abstracts.FromUser
import dev.inmo.tgbotapi.types.*
import korlibs.time.DateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represent a [ChatJoinRequest](https://core.telegram.org/bots/api#chatjoinrequest)
 */
@Serializable
data class ChatJoinRequest(
    @SerialName(chatField)
    val chat: PublicChat,
    @SerialName(fromField)
    override val from: User,
    @SerialName(userChatIdField)
    val userChatId: UserId,
    @SerialName(dateField)
    val date: TelegramDate,
    @SerialName(inviteLinkField)
    val inviteLink: ChatInviteLink? = null,
    @SerialName(bioField)
    val bio: String? = null,
) : FromUser {
    val dateTime: DateTime
        get() = date.asDate
}
