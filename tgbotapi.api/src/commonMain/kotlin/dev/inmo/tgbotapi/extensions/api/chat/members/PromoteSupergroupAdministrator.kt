package dev.inmo.tgbotapi.extensions.api.chat.members

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.members.PromoteSupergroupAdministrator
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.User

public suspend fun TelegramBot.promoteSupergroupAdministrator(
    chatId: ChatIdentifier,
    userId: UserId,
    untilDate: TelegramDate? = null,
    isAnonymous: Boolean? = null,
    canChangeInfo: Boolean? = null,
    canDeleteMessages: Boolean? = null,
    canInviteUsers: Boolean? = null,
    canRestrictMembers: Boolean? = null,
    canPinMessages: Boolean? = null,
    canPromoteMembers: Boolean? = null,
    canManageVideoChats: Boolean? = null,
    canManageChat: Boolean? = null,
    canManageTopics: Boolean? = null,
): Boolean =
    execute(
        PromoteSupergroupAdministrator(
            chatId = chatId,
            userId = userId,
            untilDate = untilDate,
            isAnonymous = isAnonymous,
            canChangeInfo = canChangeInfo,
            canDeleteMessages = canDeleteMessages,
            canInviteUsers = canInviteUsers,
            canRestrictMembers = canRestrictMembers,
            canPinMessages = canPinMessages,
            canPromoteMembers = canPromoteMembers,
            canManageVideoChats = canManageVideoChats,
            canManageChat = canManageChat,
            canManageTopics = canManageTopics,
        ),
    )

public suspend fun TelegramBot.promoteSupergroupAdministrator(
    chat: PublicChat,
    userId: UserId,
    untilDate: TelegramDate? = null,
    isAnonymous: Boolean? = null,
    canChangeInfo: Boolean? = null,
    canDeleteMessages: Boolean? = null,
    canInviteUsers: Boolean? = null,
    canRestrictMembers: Boolean? = null,
    canPinMessages: Boolean? = null,
    canPromoteMembers: Boolean? = null,
    canManageVideoChats: Boolean? = null,
    canManageChat: Boolean? = null,
    canManageTopics: Boolean? = null,
): Boolean =
    promoteSupergroupAdministrator(
        chat.id,
        userId,
        untilDate = untilDate,
        isAnonymous = isAnonymous,
        canChangeInfo = canChangeInfo,
        canDeleteMessages = canDeleteMessages,
        canInviteUsers = canInviteUsers,
        canRestrictMembers = canRestrictMembers,
        canPinMessages = canPinMessages,
        canPromoteMembers = canPromoteMembers,
        canManageVideoChats = canManageVideoChats,
        canManageChat = canManageChat,
        canManageTopics = canManageTopics,
    )

public suspend fun TelegramBot.promoteSupergroupAdministrator(
    chatId: IdChatIdentifier,
    user: User,
    untilDate: TelegramDate? = null,
    isAnonymous: Boolean? = null,
    canChangeInfo: Boolean? = null,
    canDeleteMessages: Boolean? = null,
    canInviteUsers: Boolean? = null,
    canRestrictMembers: Boolean? = null,
    canPinMessages: Boolean? = null,
    canPromoteMembers: Boolean? = null,
    canManageVideoChats: Boolean? = null,
    canManageChat: Boolean? = null,
    canManageTopics: Boolean? = null,
): Boolean =
    promoteSupergroupAdministrator(
        chatId,
        user.id,
        untilDate = untilDate,
        isAnonymous = isAnonymous,
        canChangeInfo = canChangeInfo,
        canDeleteMessages = canDeleteMessages,
        canInviteUsers = canInviteUsers,
        canRestrictMembers = canRestrictMembers,
        canPinMessages = canPinMessages,
        canPromoteMembers = canPromoteMembers,
        canManageVideoChats = canManageVideoChats,
        canManageChat = canManageChat,
        canManageTopics = canManageTopics,
    )

public suspend fun TelegramBot.promoteSupergroupAdministrator(
    chat: PublicChat,
    user: User,
    untilDate: TelegramDate? = null,
    isAnonymous: Boolean? = null,
    canChangeInfo: Boolean? = null,
    canDeleteMessages: Boolean? = null,
    canInviteUsers: Boolean? = null,
    canRestrictMembers: Boolean? = null,
    canPinMessages: Boolean? = null,
    canPromoteMembers: Boolean? = null,
    canManageVideoChats: Boolean? = null,
    canManageChat: Boolean? = null,
    canManageTopics: Boolean? = null,
): Boolean =
    promoteSupergroupAdministrator(
        chat.id,
        user.id,
        untilDate = untilDate,
        isAnonymous = isAnonymous,
        canChangeInfo = canChangeInfo,
        canDeleteMessages = canDeleteMessages,
        canInviteUsers = canInviteUsers,
        canRestrictMembers = canRestrictMembers,
        canPinMessages = canPinMessages,
        canPromoteMembers = canPromoteMembers,
        canManageVideoChats = canManageVideoChats,
        canManageChat = canManageChat,
        canManageTopics = canManageTopics,
    )
