package dev.inmo.tgbotapi.types.ChatMember

import dev.inmo.tgbotapi.types.ChatMember.abstracts.AdministratorChatMember
import dev.inmo.tgbotapi.types.User

data class AdministratorChatMemberImpl(
    override val user: User,
    override val canBeEdited: Boolean,
    override val canChangeInfo: Boolean,
    override val canPostMessages: Boolean,
    override val canEditMessages: Boolean,
    override val canRemoveMessages: Boolean,
    override val canInviteUsers: Boolean,
    override val canRestrictMembers: Boolean,
    override val canPinMessages: Boolean,
    override val canPromoteMembers: Boolean,
    override val isAnonymous: Boolean,
    override val customTitle: String?
) : AdministratorChatMember
