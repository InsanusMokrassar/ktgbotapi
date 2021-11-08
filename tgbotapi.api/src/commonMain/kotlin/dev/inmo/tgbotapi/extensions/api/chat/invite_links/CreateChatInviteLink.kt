package dev.inmo.tgbotapi.extensions.api.chat.invite_links

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.invite_links.CreateChatInviteLink
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.abstracts.PublicChat

suspend fun TelegramBot.createChatInviteLinkUnlimited(
    chatId: ChatIdentifier,
    expiration: TelegramDate? = null
) = execute(CreateChatInviteLink.unlimited(chatId, expiration))

suspend fun TelegramBot.createChatInviteLinkUnlimited(
    chat: PublicChat,
    expiration: TelegramDate? = null,
) = createChatInviteLinkUnlimited(chat.id, expiration)

suspend fun TelegramBot.createChatInviteLinkUnlimited(
    chatId: ChatIdentifier,
    expiration: DateTime
) = createChatInviteLinkUnlimited(chatId, expiration.toTelegramDate())

suspend fun TelegramBot.createChatInviteLinkUnlimited(
    chat: PublicChat,
    expiration: DateTime
) = createChatInviteLinkUnlimited(chat.id, expiration.toTelegramDate())

suspend fun TelegramBot.createChatInviteLinkWithLimitedMembers(
    chatId: ChatIdentifier,
    membersLimit: MembersLimit,
    expiration: TelegramDate? = null
) = execute(CreateChatInviteLink.withLimitedMembers(chatId, membersLimit, expiration))

suspend fun TelegramBot.createChatInviteLinkWithLimitedMembers(
    chat: PublicChat,
    membersLimit: MembersLimit,
    expiration: TelegramDate? = null,
) = createChatInviteLinkWithLimitedMembers(chat.id, membersLimit, expiration)

suspend fun TelegramBot.createChatInviteLinkWithLimitedMembers(
    chatId: ChatIdentifier,
    membersLimit: MembersLimit,
    expiration: DateTime,
) = createChatInviteLinkWithLimitedMembers(chatId, membersLimit, expiration.toTelegramDate())

suspend fun TelegramBot.createChatInviteLinkWithLimitedMembers(
    chat: PublicChat,
    membersLimit: MembersLimit,
    expiration: DateTime,
) = createChatInviteLinkWithLimitedMembers(chat.id, membersLimit, expiration.toTelegramDate())

suspend fun TelegramBot.createChatInviteLinkWithJoinRequest(
    chatId: ChatIdentifier,
    expiration: TelegramDate? = null
) = execute(CreateChatInviteLink.withJoinRequest(chatId, expiration))

suspend fun TelegramBot.createChatInviteLinkWithJoinRequest(
    chat: PublicChat,
    expiration: TelegramDate? = null,
) = createChatInviteLinkWithJoinRequest(chat.id, expiration)

suspend fun TelegramBot.createChatInviteLinkWithJoinRequest(
    chatId: ChatIdentifier,
    expiration: DateTime,
) = createChatInviteLinkWithJoinRequest(chatId, expiration.toTelegramDate())

suspend fun TelegramBot.createChatInviteLinkWithJoinRequest(
    chat: PublicChat,
    expiration: DateTime,
) = createChatInviteLinkWithJoinRequest(chat.id, expiration.toTelegramDate())
