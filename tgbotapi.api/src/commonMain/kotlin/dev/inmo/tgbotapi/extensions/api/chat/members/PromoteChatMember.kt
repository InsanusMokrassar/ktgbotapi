package dev.inmo.tgbotapi.extensions.api.chat.members

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.members.PromoteChatMember
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.User

@Warning("This method is too common. Use it with caution")
public suspend fun TelegramBot.promoteChatMember(
    chatId: ChatIdentifier,
    userId: UserId,
    untilDate: TelegramDate? = null,
    isAnonymous: Boolean? = null,
    canChangeInfo: Boolean? = null,
    canPostMessages: Boolean? = null,
    canEditMessages: Boolean? = null,
    canDeleteMessages: Boolean? = null,
    canInviteUsers: Boolean? = null,
    canRestrictMembers: Boolean? = null,
    canPinMessages: Boolean? = null,
    canPromoteMembers: Boolean? = null,
    canManageVideoChats: Boolean? = null,
    canManageChat: Boolean? = null,
    canManageTopics: Boolean? = null,
    canPostStories: Boolean? = null,
    canEditStories: Boolean? = null,
    canDeleteStories: Boolean? = null
): Boolean = execute(
    PromoteChatMember(
        chatId = chatId,
        userId = userId,
        untilDate = untilDate,
        isAnonymous = isAnonymous,
        canChangeInfo = canChangeInfo,
        canPostMessages = canPostMessages,
        canEditMessages = canEditMessages,
        canDeleteMessages = canDeleteMessages,
        canInviteUsers = canInviteUsers,
        canRestrictMembers = canRestrictMembers,
        canPinMessages = canPinMessages,
        canPromoteMembers = canPromoteMembers,
        canManageVideoChats = canManageVideoChats,
        canManageChat = canManageChat,
        canManageTopics = canManageTopics,
        canPostStories = canPostStories,
        canEditStories = canEditStories,
        canDeleteStories = canDeleteStories
    )
)

@Warning("This method is too common. Use it with caution")
public suspend fun TelegramBot.promoteChatMember(
    chat: PublicChat,
    userId: UserId,
    untilDate: TelegramDate? = null,
    isAnonymous: Boolean? = null,
    canChangeInfo: Boolean? = null,
    canPostMessages: Boolean? = null,
    canEditMessages: Boolean? = null,
    canDeleteMessages: Boolean? = null,
    canInviteUsers: Boolean? = null,
    canRestrictMembers: Boolean? = null,
    canPinMessages: Boolean? = null,
    canPromoteMembers: Boolean? = null,
    canManageVideoChats: Boolean? = null,
    canManageChat: Boolean? = null,
    canManageTopics: Boolean? = null,
    canPostStories: Boolean? = null,
    canEditStories: Boolean? = null,
    canDeleteStories: Boolean? = null
): Boolean = promoteChatMember(
    chat.id,
    userId,
    untilDate = untilDate,
    isAnonymous = isAnonymous,
    canChangeInfo = canChangeInfo,
    canPostMessages = canPostMessages,
    canEditMessages = canEditMessages,
    canDeleteMessages = canDeleteMessages,
    canInviteUsers = canInviteUsers,
    canRestrictMembers = canRestrictMembers,
    canPinMessages = canPinMessages,
    canPromoteMembers = canPromoteMembers,
    canManageVideoChats = canManageVideoChats,
    canManageChat = canManageChat,
    canManageTopics = canManageTopics,
    canPostStories = canPostStories,
    canEditStories = canEditStories,
    canDeleteStories = canDeleteStories
)

@Warning("This method is too common. Use it with caution")
public suspend fun TelegramBot.promoteChatMember(
    chatId: IdChatIdentifier,
    user: User,
    untilDate: TelegramDate? = null,
    isAnonymous: Boolean? = null,
    canChangeInfo: Boolean? = null,
    canPostMessages: Boolean? = null,
    canEditMessages: Boolean? = null,
    canDeleteMessages: Boolean? = null,
    canInviteUsers: Boolean? = null,
    canRestrictMembers: Boolean? = null,
    canPinMessages: Boolean? = null,
    canPromoteMembers: Boolean? = null,
    canManageVideoChats: Boolean? = null,
    canManageChat: Boolean? = null,
    canManageTopics: Boolean? = null,
    canPostStories: Boolean? = null,
    canEditStories: Boolean? = null,
    canDeleteStories: Boolean? = null
): Boolean = promoteChatMember(
    chatId,
    user.id,
    untilDate = untilDate,
    isAnonymous = isAnonymous,
    canChangeInfo = canChangeInfo,
    canPostMessages = canPostMessages,
    canEditMessages = canEditMessages,
    canDeleteMessages = canDeleteMessages,
    canInviteUsers = canInviteUsers,
    canRestrictMembers = canRestrictMembers,
    canPinMessages = canPinMessages,
    canPromoteMembers = canPromoteMembers,
    canManageVideoChats = canManageVideoChats,
    canManageChat = canManageChat,
    canManageTopics = canManageTopics,
    canPostStories = canPostStories,
    canEditStories = canEditStories,
    canDeleteStories = canDeleteStories
)

@Warning("This method is too common. Use it with caution")
public suspend fun TelegramBot.promoteChatMember(
    chat: PublicChat,
    user: User,
    untilDate: TelegramDate? = null,
    isAnonymous: Boolean? = null,
    canChangeInfo: Boolean? = null,
    canPostMessages: Boolean? = null,
    canEditMessages: Boolean? = null,
    canDeleteMessages: Boolean? = null,
    canInviteUsers: Boolean? = null,
    canRestrictMembers: Boolean? = null,
    canPinMessages: Boolean? = null,
    canPromoteMembers: Boolean? = null,
    canManageVideoChats: Boolean? = null,
    canManageChat: Boolean? = null,
    canManageTopics: Boolean? = null,
    canPostStories: Boolean? = null,
    canEditStories: Boolean? = null,
    canDeleteStories: Boolean? = null
): Boolean = promoteChatMember(
    chat.id,
    user.id,
    untilDate = untilDate,
    isAnonymous = isAnonymous,
    canChangeInfo = canChangeInfo,
    canPostMessages = canPostMessages,
    canEditMessages = canEditMessages,
    canDeleteMessages = canDeleteMessages,
    canInviteUsers = canInviteUsers,
    canRestrictMembers = canRestrictMembers,
    canPinMessages = canPinMessages,
    canPromoteMembers = canPromoteMembers,
    canManageVideoChats = canManageVideoChats,
    canManageChat = canManageChat,
    canManageTopics = canManageTopics,
    canPostStories = canPostStories,
    canEditStories = canEditStories,
    canDeleteStories = canDeleteStories
)
