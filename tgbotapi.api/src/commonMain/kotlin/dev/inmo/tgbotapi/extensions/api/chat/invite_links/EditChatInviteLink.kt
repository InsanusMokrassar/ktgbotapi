package dev.inmo.tgbotapi.extensions.api.chat.invite_links

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.invite_links.EditChatInviteLink
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.abstracts.PublicChat

suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chatId: ChatIdentifier,
    previousLink: String,
    expiration: TelegramDate? = null
) = execute(EditChatInviteLink.unlimited(chatId, previousLink, expiration))

suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chat: PublicChat,
    previousLink: String,
    expiration: TelegramDate? = null,
) = editChatInviteLinkUnlimited(chat.id, previousLink, expiration)

suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chatId: ChatIdentifier,
    previousLink: String,
    expiration: DateTime
) = editChatInviteLinkUnlimited(chatId, previousLink, expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chat: PublicChat,
    previousLink: String,
    expiration: DateTime
) = editChatInviteLinkUnlimited(chat.id, previousLink, expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chatId: ChatIdentifier,
    previousLink: String,
    membersLimit: MembersLimit,
    expiration: TelegramDate? = null
) = execute(EditChatInviteLink.withLimitedMembers(chatId, previousLink, membersLimit, expiration))

suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chat: PublicChat,
    previousLink: String,
    membersLimit: MembersLimit,
    expiration: TelegramDate? = null,
) = editChatInviteLinkWithLimitedMembers(chat.id, previousLink, membersLimit, expiration)

suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chatId: ChatIdentifier,
    previousLink: String,
    membersLimit: MembersLimit,
    expiration: DateTime,
) = editChatInviteLinkWithLimitedMembers(chatId, previousLink, membersLimit, expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chat: PublicChat,
    previousLink: String,
    membersLimit: MembersLimit,
    expiration: DateTime,
) = editChatInviteLinkWithLimitedMembers(chat.id, previousLink, membersLimit, expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chatId: ChatIdentifier,
    previousLink: String,
    expiration: TelegramDate? = null
) = execute(EditChatInviteLink.withJoinRequest(chatId, previousLink, expiration))

suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chat: PublicChat,
    previousLink: String,
    expiration: TelegramDate? = null,
) = editChatInviteLinkWithJoinRequest(chat.id, previousLink, expiration)

suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chatId: ChatIdentifier,
    previousLink: String,
    expiration: DateTime,
) = editChatInviteLinkWithJoinRequest(chatId, previousLink, expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chat: PublicChat,
    previousLink: String,
    expiration: DateTime,
) = editChatInviteLinkWithJoinRequest(chat.id, previousLink, expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink,
    expiration: TelegramDate? = null
) = editChatInviteLinkUnlimited(chatId, previousLink.inviteLink, expiration)

suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    expiration: TelegramDate? = null,
) = editChatInviteLinkUnlimited(chat.id, previousLink, expiration)

suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink,
    expiration: DateTime
) = editChatInviteLinkUnlimited(chatId, previousLink, expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    expiration: DateTime
) = editChatInviteLinkUnlimited(chat.id, previousLink, expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink,
    membersLimit: MembersLimit,
    expiration: TelegramDate? = null
) = editChatInviteLinkWithLimitedMembers(chatId, previousLink.inviteLink, membersLimit, expiration)

suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    membersLimit: MembersLimit,
    expiration: TelegramDate? = null,
) = editChatInviteLinkWithLimitedMembers(chat.id, previousLink, membersLimit, expiration)

suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink,
    membersLimit: MembersLimit,
    expiration: DateTime,
) = editChatInviteLinkWithLimitedMembers(chatId, previousLink, membersLimit, expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    membersLimit: MembersLimit,
    expiration: DateTime,
) = editChatInviteLinkWithLimitedMembers(chat.id, previousLink, membersLimit, expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink,
    expiration: TelegramDate? = null
) = editChatInviteLinkWithJoinRequest(chatId, previousLink.inviteLink, expiration)

suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    expiration: TelegramDate? = null,
) = editChatInviteLinkWithJoinRequest(chat.id, previousLink, expiration)

suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink,
    expiration: DateTime,
) = editChatInviteLinkWithJoinRequest(chatId, previousLink, expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    expiration: DateTime,
) = editChatInviteLinkWithJoinRequest(chat.id, previousLink, expiration.toTelegramDate())
