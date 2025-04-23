package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.abstracts.WithPreviewChat
import dev.inmo.tgbotapi.abstracts.WithUser
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.chat.PreviewUser
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatMemberUpdated(
    @SerialName(chatField)
    override val chat: PreviewChat,
    @SerialName(fromField)
    override val user: PreviewUser,
    @SerialName(dateField)
    val date: TelegramDate,
    @SerialName(oldChatMemberField)
    val oldChatMemberState: ChatMember,
    @SerialName(newChatMemberField)
    val newChatMemberState: ChatMember,
    @SerialName(inviteLinkField)
    val inviteLink: ChatInviteLink? = null,
    @SerialName(viaChatFolderInviteLinkField)
    val viaChatFolderInviteLink: Boolean? = false,
    @SerialName(viaJoinRequestField)
    val viaJoinRequest: Boolean? = false,
) : WithPreviewChat, WithUser {
    val member: PreviewUser
        get() = newChatMemberState.user
}
