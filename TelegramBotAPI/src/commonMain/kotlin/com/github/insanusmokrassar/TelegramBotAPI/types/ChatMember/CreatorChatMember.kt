package com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember.abstracts.AdministratorChatMember
import com.github.insanusmokrassar.TelegramBotAPI.types.User

data class CreatorChatMember(
    override val user: User,
    override val customTitle: String?
) : AdministratorChatMember {
    override val canBeEdited: Boolean = true
    override val canChangeInfo: Boolean = true
    override val canPostMessages: Boolean = true
    override val canEditMessages: Boolean = true
    override val canRemoveMessages: Boolean = true
    override val canInviteUsers: Boolean = true
    override val canRestrictMembers: Boolean = true
    override val canPinMessages: Boolean = true
    override val canPromoteMembers: Boolean = true
}
