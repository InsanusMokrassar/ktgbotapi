package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.abstracts.WithChat
import dev.inmo.tgbotapi.abstracts.WithUser
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.chat.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatMemberUpdated(
    @SerialName(chatField)
    override val chat: Chat,
    @SerialName(fromField)
    override val user: User,
    @SerialName(dateField)
    val date: TelegramDate,
    @SerialName(oldChatMemberField)
    val oldChatMemberState: ChatMember,
    @SerialName(newChatMemberField)
    val newChatMemberState: ChatMember,
    @SerialName(inviteLinkField)
    val inviteLink: ChatInviteLink? = null,
    @SerialName(viaChatFolderInviteLinkField)
    val viaChatFolderInviteLink: Boolean? = inviteLink is ChatFolderInviteLink
) : WithChat, WithUser
