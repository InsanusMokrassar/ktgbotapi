package dev.inmo.tgbotapi.types.chat.member

sealed interface SpecialChatAdministratorRights {
    val canChangeInfo: Boolean
    val canInviteUsers: Boolean
    val canPinMessages: Boolean
    val canManageTopics: Boolean
}

sealed interface ChatAdministratorRights : SpecialChatAdministratorRights {
    val isAnonymous: Boolean
    val canManageChat: Boolean
    val canRemoveMessages: Boolean
    val canManageVideoChats: Boolean
    val canRestrictMembers: Boolean
    val canPromoteMembers: Boolean
    val canPostMessages: Boolean
    val canEditMessages: Boolean
    val canPostStories: Boolean
    val canEditStories: Boolean
    val canDeleteStories: Boolean
}
