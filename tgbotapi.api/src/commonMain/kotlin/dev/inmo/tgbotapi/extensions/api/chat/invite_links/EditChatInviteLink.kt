package dev.inmo.tgbotapi.extensions.api.chat.invite_links

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.invite_links.EditChatInviteLink
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PublicChat
import korlibs.time.DateTime

public suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chatId: ChatIdentifier,
    previousLink: String,
    name: String? = null,
    expiration: TelegramDate? = null,
): ChatInviteLinkUnlimited = execute(EditChatInviteLink.unlimited(chatId, previousLink, name, expiration))

public suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chat: PublicChat,
    previousLink: String,
    name: String? = null,
    expiration: TelegramDate? = null,
): ChatInviteLinkUnlimited = editChatInviteLinkUnlimited(chat.id, previousLink, name, expiration)

public suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chatId: ChatIdentifier,
    previousLink: String,
    expiration: DateTime,
    name: String? = null,
): ChatInviteLinkUnlimited = editChatInviteLinkUnlimited(chatId, previousLink, name, expiration.toTelegramDate())

public suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chat: PublicChat,
    previousLink: String,
    expiration: DateTime,
    name: String? = null,
): ChatInviteLinkUnlimited = editChatInviteLinkUnlimited(chat.id, previousLink, name, expiration.toTelegramDate())

public suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chatId: ChatIdentifier,
    previousLink: String,
    membersLimit: MembersLimit,
    name: String? = null,
    expiration: TelegramDate? = null,
): ChatInviteLinkWithLimitedMembers = execute(EditChatInviteLink.withLimitedMembers(chatId, previousLink, membersLimit, name, expiration))

public suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chat: PublicChat,
    previousLink: String,
    membersLimit: MembersLimit,
    name: String? = null,
    expiration: TelegramDate? = null,
): ChatInviteLinkWithLimitedMembers = editChatInviteLinkWithLimitedMembers(chat.id, previousLink, membersLimit, name, expiration)

public suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chatId: ChatIdentifier,
    previousLink: String,
    membersLimit: MembersLimit,
    expiration: DateTime,
    name: String? = null,
): ChatInviteLinkWithLimitedMembers =
    editChatInviteLinkWithLimitedMembers(
        chatId,
        previousLink,
        membersLimit,
        name,
        expiration.toTelegramDate(),
    )

public suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chat: PublicChat,
    previousLink: String,
    membersLimit: MembersLimit,
    expiration: DateTime,
    name: String? = null,
): ChatInviteLinkWithLimitedMembers =
    editChatInviteLinkWithLimitedMembers(
        chat.id,
        previousLink,
        membersLimit,
        name,
        expiration.toTelegramDate(),
    )

public suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chatId: ChatIdentifier,
    previousLink: String,
    name: String? = null,
    expiration: TelegramDate? = null,
): ChatInviteLinkWithJoinRequest = execute(EditChatInviteLink.withJoinRequest(chatId, previousLink, name, expiration))

public suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chat: PublicChat,
    previousLink: String,
    name: String? = null,
    expiration: TelegramDate? = null,
): ChatInviteLinkWithJoinRequest = editChatInviteLinkWithJoinRequest(chat.id, previousLink, name, expiration)

public suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chatId: ChatIdentifier,
    previousLink: String,
    expiration: DateTime,
    name: String? = null,
): ChatInviteLinkWithJoinRequest = editChatInviteLinkWithJoinRequest(chatId, previousLink, name, expiration.toTelegramDate())

public suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chat: PublicChat,
    previousLink: String,
    expiration: DateTime,
    name: String? = null,
): ChatInviteLinkWithJoinRequest = editChatInviteLinkWithJoinRequest(chat.id, previousLink, name, expiration.toTelegramDate())

public suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink,
    name: String? = null,
    expiration: TelegramDate? = null,
): ChatInviteLinkUnlimited = editChatInviteLinkUnlimited(chatId, previousLink.inviteLink, name, expiration)

public suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    name: String? = null,
    expiration: TelegramDate? = null,
): ChatInviteLinkUnlimited = editChatInviteLinkUnlimited(chat.id, previousLink, name, expiration)

public suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink,
    expiration: DateTime,
    name: String? = null,
): ChatInviteLinkUnlimited = editChatInviteLinkUnlimited(chatId, previousLink, name, expiration.toTelegramDate())

public suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    expiration: DateTime,
    name: String? = null,
): ChatInviteLinkUnlimited = editChatInviteLinkUnlimited(chat.id, previousLink, name, expiration.toTelegramDate())

public suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink,
    membersLimit: MembersLimit,
    name: String? = null,
    expiration: TelegramDate? = null,
): ChatInviteLinkWithLimitedMembers = editChatInviteLinkWithLimitedMembers(chatId, previousLink.inviteLink, membersLimit, name, expiration)

public suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    membersLimit: MembersLimit,
    name: String? = null,
    expiration: TelegramDate? = null,
): ChatInviteLinkWithLimitedMembers = editChatInviteLinkWithLimitedMembers(chat.id, previousLink, membersLimit, name, expiration)

public suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink,
    membersLimit: MembersLimit,
    expiration: DateTime,
    name: String? = null,
): ChatInviteLinkWithLimitedMembers =
    editChatInviteLinkWithLimitedMembers(
        chatId,
        previousLink,
        membersLimit,
        name,
        expiration.toTelegramDate(),
    )

public suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    membersLimit: MembersLimit,
    expiration: DateTime,
    name: String? = null,
): ChatInviteLinkWithLimitedMembers =
    editChatInviteLinkWithLimitedMembers(
        chat.id,
        previousLink,
        membersLimit,
        name,
        expiration.toTelegramDate(),
    )

public suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink,
    name: String? = null,
    expiration: TelegramDate? = null,
): ChatInviteLinkWithJoinRequest = editChatInviteLinkWithJoinRequest(chatId, previousLink.inviteLink, name, expiration)

public suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    name: String? = null,
    expiration: TelegramDate? = null,
): ChatInviteLinkWithJoinRequest = editChatInviteLinkWithJoinRequest(chat.id, previousLink, name, expiration)

public suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink,
    expiration: DateTime,
    name: String? = null,
): ChatInviteLinkWithJoinRequest = editChatInviteLinkWithJoinRequest(chatId, previousLink, name, expiration.toTelegramDate())

public suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    expiration: DateTime,
    name: String? = null,
): ChatInviteLinkWithJoinRequest = editChatInviteLinkWithJoinRequest(chat.id, previousLink, name, expiration.toTelegramDate())

// Subscriptions

public suspend fun TelegramBot.editChatSubscriptionInviteLink(
    chatId: ChatIdentifier,
    previousLink: String,
    name: String,
): ChatInviteLinkUnlimited = execute(EditChatInviteLink.subscription(chatId, previousLink, name))

public suspend fun TelegramBot.editChatSubscriptionInviteLink(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink,
    name: String,
): ChatInviteLinkUnlimited = editChatSubscriptionInviteLink(chatId, previousLink.inviteLink, name)

public suspend fun TelegramBot.editChatSubscriptionInviteLink(
    chat: PublicChat,
    previousLink: String,
    name: String,
): ChatInviteLinkUnlimited = editChatSubscriptionInviteLink(chat.id, previousLink, name)

public suspend fun TelegramBot.editChatSubscriptionInviteLink(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    name: String,
): ChatInviteLinkUnlimited = editChatSubscriptionInviteLink(chat.id, previousLink, name)
