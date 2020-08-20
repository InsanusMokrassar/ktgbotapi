package com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember.abstracts.AdministratorChatMember
import com.github.insanusmokrassar.TelegramBotAPI.types.User

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
    override val customTitle: String?
) : AdministratorChatMember
