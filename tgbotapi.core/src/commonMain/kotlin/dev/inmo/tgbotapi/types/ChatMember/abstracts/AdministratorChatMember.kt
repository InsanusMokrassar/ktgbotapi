package dev.inmo.tgbotapi.types.ChatMember.abstracts

interface AdministratorChatMember : SpecialRightsChatMember {
    val canBeEdited: Boolean
    val canPostMessages: Boolean
    val canEditMessages: Boolean
    val canRemoveMessages: Boolean
    val canRestrictMembers: Boolean
    val canPromoteMembers: Boolean
    val customTitle: String?
}