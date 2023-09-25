package dev.inmo.tgbotapi.extensions.api.chat.members

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.members.PromoteChatMember
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.User

suspend fun TelegramBot.promoteChatAdministrator(
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
) = execute(
    PromoteChatMember(
        chatId,
        userId,
        untilDate,
        isAnonymous,
        canChangeInfo,
        canDeleteMessages,
        canInviteUsers,
        canRestrictMembers,
        canPromoteMembers,
        canManageVideoChats,
        canManageChat
    )
)

suspend fun TelegramBot.promoteChatAdministrator(
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
) = promoteChatAdministrator(
    chat.id,
    userId,
    untilDate,
    isAnonymous,
    canChangeInfo,
    canDeleteMessages,
    canInviteUsers,
    canRestrictMembers,
    canPromoteMembers,
    canManageVideoChats,
    canManageChat
)

suspend fun TelegramBot.promoteChatAdministrator(
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
) = promoteChatAdministrator(
    chatId,
    user.id,
    untilDate,
    isAnonymous,
    canChangeInfo,
    canDeleteMessages,
    canInviteUsers,
    canRestrictMembers,
    canPromoteMembers,
    canManageVideoChats,
    canManageChat
)

suspend fun TelegramBot.promoteChatAdministrator(
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
) = promoteChatAdministrator(
    chat.id,
    user.id,
    untilDate,
    isAnonymous,
    canChangeInfo,
    canDeleteMessages,
    canInviteUsers,
    canRestrictMembers,
    canPromoteMembers,
    canManageVideoChats,
    canManageChat
)
