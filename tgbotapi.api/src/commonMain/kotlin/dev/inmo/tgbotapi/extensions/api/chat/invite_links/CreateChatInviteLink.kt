package dev.inmo.tgbotapi.extensions.api.chat.invite_links

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.invite_links.CreateChatInviteLink
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PublicChat

suspend fun TelegramBot.createChatInviteLinkUnlimited(
    chatId: ChatIdentifier,
    name: String? = null,
    expiration: TelegramDate? = null
) = execute(CreateChatInviteLink.unlimited(chatId, name, expiration))

suspend fun TelegramBot.createChatInviteLinkUnlimited(
    chat: PublicChat,
    name: String? = null,
    expiration: TelegramDate? = null,
) = createChatInviteLinkUnlimited(chat.id, name, expiration)

suspend fun TelegramBot.createChatInviteLinkUnlimited(
    chatId: ChatIdentifier,
    expiration: DateTime,
    name: String? = null,
) = createChatInviteLinkUnlimited(chatId, name, expiration.toTelegramDate())

suspend fun TelegramBot.createChatInviteLinkUnlimited(
    chat: PublicChat,
    expiration: DateTime,
    name: String? = null
) = createChatInviteLinkUnlimited(chat.id, name, expiration.toTelegramDate())

suspend fun TelegramBot.createChatInviteLinkWithLimitedMembers(
    chatId: ChatIdentifier,
    membersLimit: MembersLimit,
    name: String? = null,
    expiration: TelegramDate? = null
) = execute(CreateChatInviteLink.withLimitedMembers(chatId, membersLimit, name, expiration))

suspend fun TelegramBot.createChatInviteLinkWithLimitedMembers(
    chat: PublicChat,
    membersLimit: MembersLimit,
    name: String? = null,
    expiration: TelegramDate? = null,
) = createChatInviteLinkWithLimitedMembers(chat.id, membersLimit, name, expiration)

suspend fun TelegramBot.createChatInviteLinkWithLimitedMembers(
    chatId: ChatIdentifier,
    membersLimit: MembersLimit,
    expiration: DateTime,
    name: String? = null,
) = createChatInviteLinkWithLimitedMembers(chatId, membersLimit, name, expiration.toTelegramDate())

suspend fun TelegramBot.createChatInviteLinkWithLimitedMembers(
    chat: PublicChat,
    membersLimit: MembersLimit,
    expiration: DateTime,
    name: String? = null,
) = createChatInviteLinkWithLimitedMembers(chat.id, membersLimit, name, expiration.toTelegramDate())

suspend fun TelegramBot.createChatInviteLinkWithJoinRequest(
    chatId: ChatIdentifier,
    name: String? = null,
    expiration: TelegramDate? = null
) = execute(CreateChatInviteLink.withJoinRequest(chatId, name, expiration))

suspend fun TelegramBot.createChatInviteLinkWithJoinRequest(
    chat: PublicChat,
    name: String? = null,
    expiration: TelegramDate? = null,
) = createChatInviteLinkWithJoinRequest(chat.id, name, expiration)

suspend fun TelegramBot.createChatInviteLinkWithJoinRequest(
    chatId: ChatIdentifier,
    expiration: DateTime,
    name: String? = null,
) = createChatInviteLinkWithJoinRequest(chatId, name, expiration.toTelegramDate())

suspend fun TelegramBot.createChatInviteLinkWithJoinRequest(
    chat: PublicChat,
    expiration: DateTime,
    name: String? = null,
) = createChatInviteLinkWithJoinRequest(chat.id, name, expiration.toTelegramDate())
