package dev.inmo.tgbotapi.types.ChatMember.abstracts

sealed interface SpecialChatAdministratorRights {
    val canChangeInfo: Boolean
    val canInviteUsers: Boolean
    val canPinMessages: Boolean
}

interface ChatAdministratorRights : SpecialChatAdministratorRights {
    val isAnonymous: Boolean
    val canManageChat: Boolean
    val canRemoveMessages: Boolean
    val canManageVideoChats: Boolean
    val canRestrictMembers: Boolean
    val canPromoteMembers: Boolean
    val canPostMessages: Boolean
    val canEditMessages: Boolean
}
