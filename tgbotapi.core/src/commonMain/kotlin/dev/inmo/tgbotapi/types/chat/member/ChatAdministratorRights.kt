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
    val canManageDirectMessages: Boolean

    companion object {
        operator fun invoke(
            canChangeInfo: Boolean = false,
            canPostMessages: Boolean = false,
            canEditMessages: Boolean = false,
            canRemoveMessages: Boolean = false,
            canInviteUsers: Boolean = false,
            canRestrictMembers: Boolean = false,
            canPinMessages: Boolean = false,
            canPromoteMembers: Boolean = false,
            canManageVideoChats: Boolean = false,
            canManageChat: Boolean = false,
            isAnonymous: Boolean = false,
            canManageTopics: Boolean = false,
            canPostStories: Boolean = false,
            canEditStories: Boolean = false,
            canDeleteStories: Boolean = false
        ) = ChatCommonAdministratorRights(
            canChangeInfo = canChangeInfo,
            canPostMessages = canPostMessages,
            canEditMessages = canEditMessages,
            canRemoveMessages = canRemoveMessages,
            canInviteUsers = canInviteUsers,
            canRestrictMembers = canRestrictMembers,
            canPinMessages = canPinMessages,
            canPromoteMembers = canPromoteMembers,
            canManageVideoChats = canManageVideoChats,
            canManageChat = canManageChat,
            isAnonymous = isAnonymous,
            canManageTopics = canManageTopics,
            canPostStories = canPostStories,
            canEditStories = canEditStories,
            canDeleteStories = canDeleteStories
        )
    }
}
