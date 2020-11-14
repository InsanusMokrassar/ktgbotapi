package dev.inmo.tgbotapi.types.ChatMember

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.ChatMember.abstracts.AdministratorChatMember
import kotlinx.serialization.*

@Serializable
data class AdministratorChatMemberImpl(
    @SerialName(userField)
    override val user: User,
    @SerialName(canBeEditedField)
    override val canBeEdited: Boolean = false,
    @SerialName(canChangeInfoField)
    override val canChangeInfo: Boolean = false,
    @SerialName(canPostMessagesField)
    override val canPostMessages: Boolean = false,
    @SerialName(canEditMessagesField)
    override val canEditMessages: Boolean = false,
    @SerialName(canDeleteMessagesField)
    override val canRemoveMessages: Boolean = false,
    @SerialName(canInviteUsersField)
    override val canInviteUsers: Boolean = false,
    @SerialName(canRestrictMembersField)
    override val canRestrictMembers: Boolean = false,
    @SerialName(canPinMessagesField)
    override val canPinMessages: Boolean = false,
    @SerialName(canPromoteMembersField)
    override val canPromoteMembers: Boolean = false,
    @SerialName(isAnonymousField)
    override val isAnonymous: Boolean = false,
    @SerialName(customTitleField)
    override val customTitle: String? = null
) : AdministratorChatMember {
    @SerialName(statusField)
    @Required
    private val type: String = "administrator"
}
