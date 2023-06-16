package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.internal.ClassCastsExcluded
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@ClassCastsExcluded
data class ChatAdministratorRightsImpl(
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
    @SerialName(canManageVideoChatsField)
    override val canManageVideoChats: Boolean = false,
    @SerialName(canManageChatField)
    override val canManageChat: Boolean = false,
    @SerialName(isAnonymousField)
    override val isAnonymous: Boolean = false,
    @SerialName(canManageTopicsField)
    override val canManageTopics: Boolean = false
) : ChatAdministratorRights
