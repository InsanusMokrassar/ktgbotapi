package dev.inmo.tgbotapi.extensions.api.chat.members

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.members.PromoteChatMember
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.User

public suspend fun TelegramBot.promoteChatAdministrator(
    chatId: ChatIdentifier,
    userId: UserId,
    untilDate: TelegramDate? = null,
    isAnonymous: Boolean? = null,
    canChangeInfo: Boolean? = null,
    canDeleteMessages: Boolean? = null,
    canInviteUsers: Boolean? = null,
    canRestrictMembers: Boolean? = null,
    canPromoteMembers: Boolean? = null,
    canManageVideoChats: Boolean? = null,
    canManageChat: Boolean? = null,
    canManageTags: Boolean? = null,
): Boolean = execute(
    PromoteChatMember(
        chatId = chatId,
        userId = userId,
        untilDate = untilDate,
        isAnonymous = isAnonymous,
        canChangeInfo = canChangeInfo,
        canDeleteMessages = canDeleteMessages,
        canInviteUsers = canInviteUsers,
        canRestrictMembers = canRestrictMembers,
        canPromoteMembers = canPromoteMembers,
        canManageVideoChats = canManageVideoChats,
        canManageChat = canManageChat,
        canManageTags = canManageTags
    )
)

public suspend fun TelegramBot.promoteChatAdministrator(
    chat: PublicChat,
    userId: UserId,
    untilDate: TelegramDate? = null,
    isAnonymous: Boolean? = null,
    canChangeInfo: Boolean? = null,
    canDeleteMessages: Boolean? = null,
    canInviteUsers: Boolean? = null,
    canRestrictMembers: Boolean? = null,
    canPromoteMembers: Boolean? = null,
    canManageVideoChats: Boolean? = null,
    canManageChat: Boolean? = null,
    canManageTags: Boolean? = null,
): Boolean = promoteChatAdministrator(
    chatId = chat.id,
    userId = userId,
    untilDate = untilDate,
    isAnonymous = isAnonymous,
    canChangeInfo = canChangeInfo,
    canDeleteMessages = canDeleteMessages,
    canInviteUsers = canInviteUsers,
    canRestrictMembers = canRestrictMembers,
    canPromoteMembers = canPromoteMembers,
    canManageVideoChats = canManageVideoChats,
    canManageChat = canManageChat,
    canManageTags = canManageTags
)

public suspend fun TelegramBot.promoteChatAdministrator(
    chatId: IdChatIdentifier,
    user: User,
    untilDate: TelegramDate? = null,
    isAnonymous: Boolean? = null,
    canChangeInfo: Boolean? = null,
    canDeleteMessages: Boolean? = null,
    canInviteUsers: Boolean? = null,
    canRestrictMembers: Boolean? = null,
    canPromoteMembers: Boolean? = null,
    canManageVideoChats: Boolean? = null,
    canManageChat: Boolean? = null,
    canManageTags: Boolean? = null,
): Boolean = promoteChatAdministrator(
    chatId = chatId,
    userId = user.id,
    untilDate = untilDate,
    isAnonymous = isAnonymous,
    canChangeInfo = canChangeInfo,
    canDeleteMessages = canDeleteMessages,
    canInviteUsers = canInviteUsers,
    canRestrictMembers = canRestrictMembers,
    canPromoteMembers = canPromoteMembers,
    canManageVideoChats = canManageVideoChats,
    canManageChat = canManageChat,
    canManageTags = canManageTags
)

public suspend fun TelegramBot.promoteChatAdministrator(
    chat: PublicChat,
    user: User,
    untilDate: TelegramDate? = null,
    isAnonymous: Boolean? = null,
    canChangeInfo: Boolean? = null,
    canDeleteMessages: Boolean? = null,
    canInviteUsers: Boolean? = null,
    canRestrictMembers: Boolean? = null,
    canPromoteMembers: Boolean? = null,
    canManageVideoChats: Boolean? = null,
    canManageChat: Boolean? = null,
    canManageTags: Boolean? = null,
): Boolean = promoteChatAdministrator(
    chatId = chat.id,
    userId = user.id,
    untilDate = untilDate,
    isAnonymous = isAnonymous,
    canChangeInfo = canChangeInfo,
    canDeleteMessages = canDeleteMessages,
    canInviteUsers = canInviteUsers,
    canRestrictMembers = canRestrictMembers,
    canPromoteMembers = canPromoteMembers,
    canManageVideoChats = canManageVideoChats,
    canManageChat = canManageChat,
    canManageTags = canManageTags
)
