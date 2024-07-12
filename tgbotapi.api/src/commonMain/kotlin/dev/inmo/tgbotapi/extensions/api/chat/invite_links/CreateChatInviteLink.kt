package dev.inmo.tgbotapi.extensions.api.chat.invite_links

import korlibs.time.DateTime
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.invite_links.CreateChatInviteLink
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PublicChat

public suspend fun TelegramBot.createChatInviteLinkUnlimited(
    chatId: ChatIdentifier,
    name: String? = null,
    expiration: TelegramDate? = null
): ChatInviteLinkUnlimited = execute(CreateChatInviteLink.unlimited(chatId, name, expiration))

public suspend fun TelegramBot.createChatInviteLinkUnlimited(
    chat: PublicChat,
    name: String? = null,
    expiration: TelegramDate? = null,
): ChatInviteLinkUnlimited = createChatInviteLinkUnlimited(chat.id, name, expiration)

public suspend fun TelegramBot.createChatInviteLinkUnlimited(
    chatId: ChatIdentifier,
    expiration: DateTime,
    name: String? = null,
): ChatInviteLinkUnlimited = createChatInviteLinkUnlimited(chatId, name, expiration.toTelegramDate())

public suspend fun TelegramBot.createChatInviteLinkUnlimited(
    chat: PublicChat,
    expiration: DateTime,
    name: String? = null
): ChatInviteLinkUnlimited = createChatInviteLinkUnlimited(chat.id, name, expiration.toTelegramDate())

public suspend fun TelegramBot.createChatInviteLinkWithLimitedMembers(
    chatId: ChatIdentifier,
    membersLimit: MembersLimit,
    name: String? = null,
    expiration: TelegramDate? = null
): ChatInviteLinkWithLimitedMembers = execute(CreateChatInviteLink.withLimitedMembers(chatId, membersLimit, name, expiration))

public suspend fun TelegramBot.createChatInviteLinkWithLimitedMembers(
    chat: PublicChat,
    membersLimit: MembersLimit,
    name: String? = null,
    expiration: TelegramDate? = null,
): ChatInviteLinkWithLimitedMembers = createChatInviteLinkWithLimitedMembers(chat.id, membersLimit, name, expiration)

public suspend fun TelegramBot.createChatInviteLinkWithLimitedMembers(
    chatId: ChatIdentifier,
    membersLimit: MembersLimit,
    expiration: DateTime,
    name: String? = null,
): ChatInviteLinkWithLimitedMembers = createChatInviteLinkWithLimitedMembers(chatId, membersLimit, name, expiration.toTelegramDate())

public suspend fun TelegramBot.createChatInviteLinkWithLimitedMembers(
    chat: PublicChat,
    membersLimit: MembersLimit,
    expiration: DateTime,
    name: String? = null,
): ChatInviteLinkWithLimitedMembers = createChatInviteLinkWithLimitedMembers(chat.id, membersLimit, name, expiration.toTelegramDate())

public suspend fun TelegramBot.createChatInviteLinkWithJoinRequest(
    chatId: ChatIdentifier,
    name: String? = null,
    expiration: TelegramDate? = null
): ChatInviteLinkWithJoinRequest = execute(CreateChatInviteLink.withJoinRequest(chatId, name, expiration))

public suspend fun TelegramBot.createChatInviteLinkWithJoinRequest(
    chat: PublicChat,
    name: String? = null,
    expiration: TelegramDate? = null,
): ChatInviteLinkWithJoinRequest = createChatInviteLinkWithJoinRequest(chat.id, name, expiration)

public suspend fun TelegramBot.createChatInviteLinkWithJoinRequest(
    chatId: ChatIdentifier,
    expiration: DateTime,
    name: String? = null,
): ChatInviteLinkWithJoinRequest = createChatInviteLinkWithJoinRequest(chatId, name, expiration.toTelegramDate())

public suspend fun TelegramBot.createChatInviteLinkWithJoinRequest(
    chat: PublicChat,
    expiration: DateTime,
    name: String? = null,
): ChatInviteLinkWithJoinRequest = createChatInviteLinkWithJoinRequest(chat.id, name, expiration.toTelegramDate())
