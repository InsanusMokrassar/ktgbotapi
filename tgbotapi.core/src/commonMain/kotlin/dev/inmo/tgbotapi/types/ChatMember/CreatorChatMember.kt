package dev.inmo.tgbotapi.types.ChatMember

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.ChatMember.abstracts.AdministratorChatMember
import kotlinx.serialization.*

@Serializable
data class CreatorChatMember(
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
    override val canManageVoiceChats: Boolean = true
    @Transient
    override val canManageChat: Boolean = true
    @SerialName(statusField)
    @Required
    private val type: String = "creator"
}
