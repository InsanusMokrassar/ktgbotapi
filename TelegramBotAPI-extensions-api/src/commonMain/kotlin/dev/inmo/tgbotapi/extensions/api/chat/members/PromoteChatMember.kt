package dev.inmo.tgbotapi.extensions.api.chat.members

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.members.PromoteChatMember
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun TelegramBot.promoteChatMember(
    chatId: ChatIdentifier,
    userId: UserId,
    untilDate: TelegramDate? = null,
    canChangeInfo: Boolean? = null,
    canPostMessages: Boolean? = null,
    canEditMessages: Boolean? = null,
    canDeleteMessages: Boolean? = null,
    canInviteUsers: Boolean? = null,
    canRestrictMembers: Boolean? = null,
    canPinMessages: Boolean? = null,
    canPromoteMembers: Boolean? = null
) = execute(
    PromoteChatMember(
        chatId,
        userId,
        untilDate,
        canChangeInfo,
        canPostMessages,
        canEditMessages,
        canDeleteMessages,
        canInviteUsers,
        canRestrictMembers,
        canPinMessages,
        canPromoteMembers
    )
)

suspend fun TelegramBot.promoteChatMember(
    chat: PublicChat,
    userId: UserId,
    untilDate: TelegramDate? = null,
    canChangeInfo: Boolean? = null,
    canPostMessages: Boolean? = null,
    canEditMessages: Boolean? = null,
    canDeleteMessages: Boolean? = null,
    canInviteUsers: Boolean? = null,
    canRestrictMembers: Boolean? = null,
    canPinMessages: Boolean? = null,
    canPromoteMembers: Boolean? = null
) = promoteChatMember(
    chat.id,
    userId,
    untilDate,
    canChangeInfo,
    canPostMessages,
    canEditMessages,
    canDeleteMessages,
    canInviteUsers,
    canRestrictMembers,
    canPinMessages,
    canPromoteMembers
)

suspend fun TelegramBot.promoteChatMember(
    chatId: ChatId,
    user: User,
    untilDate: TelegramDate? = null,
    canChangeInfo: Boolean? = null,
    canPostMessages: Boolean? = null,
    canEditMessages: Boolean? = null,
    canDeleteMessages: Boolean? = null,
    canInviteUsers: Boolean? = null,
    canRestrictMembers: Boolean? = null,
    canPinMessages: Boolean? = null,
    canPromoteMembers: Boolean? = null
) = promoteChatMember(
    chatId,
    user.id,
    untilDate,
    canChangeInfo,
    canPostMessages,
    canEditMessages,
    canDeleteMessages,
    canInviteUsers,
    canRestrictMembers,
    canPinMessages,
    canPromoteMembers
)

suspend fun TelegramBot.promoteChatMember(
    chat: PublicChat,
    user: User,
    untilDate: TelegramDate? = null,
    canChangeInfo: Boolean? = null,
    canPostMessages: Boolean? = null,
    canEditMessages: Boolean? = null,
    canDeleteMessages: Boolean? = null,
    canInviteUsers: Boolean? = null,
    canRestrictMembers: Boolean? = null,
    canPinMessages: Boolean? = null,
    canPromoteMembers: Boolean? = null
) = promoteChatMember(
    chat.id,
    user.id,
    untilDate,
    canChangeInfo,
    canPostMessages,
    canEditMessages,
    canDeleteMessages,
    canInviteUsers,
    canRestrictMembers,
    canPinMessages,
    canPromoteMembers
)
