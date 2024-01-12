package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.User
import kotlinx.serialization.*

@Serializable
data class OwnerChatMember(
    override val user: User,
    @SerialName(isAnonymousField)
    override val isAnonymous: Boolean = false,
    @SerialName(customTitleField)
    override val customTitle: String? = null
) : AdministratorChatMember {
    @Transient
    override val canBeEdited: Boolean = true
    @Transient
    override val canChangeInfo: Boolean = true
    @Transient
    override val canPostMessages: Boolean = true
    @Transient
    override val canEditMessages: Boolean = true
    @Transient
    override val canRemoveMessages: Boolean = true
    @Transient
    override val canInviteUsers: Boolean = true
    @Transient
    override val canRestrictMembers: Boolean = true
    @Transient
    override val canPinMessages: Boolean = true
    @Transient
    override val canPromoteMembers: Boolean = true
    @Transient
    override val canManageVideoChats: Boolean = true
    @Transient
    override val canManageChat: Boolean = true
    @Transient
    override val canManageTopics: Boolean = true
    @Transient
    override val canPostStories: Boolean = true
    @Transient
    override val canEditStories: Boolean = true
    @Transient
    override val canDeleteStories: Boolean = true

    @SerialName(statusField)
    @Required
    @EncodeDefault
    override val status: ChatMember.Status = ChatMember.Status.Creator
}
