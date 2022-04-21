package dev.inmo.tgbotapi.types.chat

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.CommonAbstracts.FromUser
import dev.inmo.tgbotapi.types.*
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
    @SerialName(dateField)
    val date: TelegramDate,
    @SerialName(inviteLinkField)
    val inviteLink: ChatInviteLink,
    @SerialName(bioField)
    val bio: String? = null
) : FromUser {
    val dateTime: DateTime
        get() = date.asDate
}
