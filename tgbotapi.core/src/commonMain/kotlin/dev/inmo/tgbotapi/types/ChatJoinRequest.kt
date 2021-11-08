package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.CommonAbstracts.FromUser
import dev.inmo.tgbotapi.types.chat.abstracts.PublicChat
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
) : FromUser
