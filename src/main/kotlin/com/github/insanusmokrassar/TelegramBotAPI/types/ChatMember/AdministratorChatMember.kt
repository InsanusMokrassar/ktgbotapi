package com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember

interface AdministratorChatMember : ChatMember {
    val canBeEdited: Boolean
    val canChangeInfo: Boolean
    val canPostMessages: Boolean
    val canEditMessages: Boolean
    val canRemoveMessages: Boolean
    val canInviteUsers: Boolean
    val canRestrictMembers: Boolean
    val canPinMessages: Boolean
    val canPromoteMembers: Boolean
}