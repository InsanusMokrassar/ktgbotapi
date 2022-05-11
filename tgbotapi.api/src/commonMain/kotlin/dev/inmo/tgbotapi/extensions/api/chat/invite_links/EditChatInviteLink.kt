package dev.inmo.tgbotapi.extensions.api.chat.invite_links

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.invite_links.EditChatInviteLink
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PublicChat

suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chatId: ChatIdentifier,
    previousLink: String,
    name: String? = null,
    expiration: TelegramDate? = null
) = execute(EditChatInviteLink.unlimited(chatId, previousLink, name, expiration))

suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chat: PublicChat,
    previousLink: String,
    name: String? = null,
    expiration: TelegramDate? = null,
) = editChatInviteLinkUnlimited(chat.id, previousLink, name, expiration)

suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chatId: ChatIdentifier,
    previousLink: String,
    expiration: DateTime,
    name: String? = null,
) = editChatInviteLinkUnlimited(chatId, previousLink, name , expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chat: PublicChat,
    previousLink: String,
    expiration: DateTime,
    name: String? = null,
) = editChatInviteLinkUnlimited(chat.id, previousLink, name , expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chatId: ChatIdentifier,
    previousLink: String,
    membersLimit: MembersLimit,
    name: String? = null,
    expiration: TelegramDate? = null
) = execute(EditChatInviteLink.withLimitedMembers(chatId, previousLink, membersLimit, name, expiration))

suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chat: PublicChat,
    previousLink: String,
    membersLimit: MembersLimit,
    name: String? = null,
    expiration: TelegramDate? = null,
) = editChatInviteLinkWithLimitedMembers(chat.id, previousLink, membersLimit, name, expiration)

suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chatId: ChatIdentifier,
    previousLink: String,
    membersLimit: MembersLimit,
    expiration: DateTime,
    name: String? = null,
) = editChatInviteLinkWithLimitedMembers(chatId, previousLink, membersLimit, name , expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chat: PublicChat,
    previousLink: String,
    membersLimit: MembersLimit,
    expiration: DateTime,
    name: String? = null,
) = editChatInviteLinkWithLimitedMembers(chat.id, previousLink, membersLimit, name , expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chatId: ChatIdentifier,
    previousLink: String,
    name: String? = null,
    expiration: TelegramDate? = null
) = execute(EditChatInviteLink.withJoinRequest(chatId, previousLink, name, expiration))

suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chat: PublicChat,
    previousLink: String,
    name: String? = null,
    expiration: TelegramDate? = null,
) = editChatInviteLinkWithJoinRequest(chat.id, previousLink, name, expiration)

suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chatId: ChatIdentifier,
    previousLink: String,
    expiration: DateTime,
    name: String? = null,
) = editChatInviteLinkWithJoinRequest(chatId, previousLink, name , expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chat: PublicChat,
    previousLink: String,
    expiration: DateTime,
    name: String? = null,
) = editChatInviteLinkWithJoinRequest(chat.id, previousLink, name , expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink,
    name: String? = null,
    expiration: TelegramDate? = null
) = editChatInviteLinkUnlimited(chatId, previousLink.inviteLink, name, expiration)

suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    name: String? = null,
    expiration: TelegramDate? = null,
) = editChatInviteLinkUnlimited(chat.id, previousLink, name, expiration)

suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink,
    expiration: DateTime,
    name: String? = null,
) = editChatInviteLinkUnlimited(chatId, previousLink, name, expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkUnlimited(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    expiration: DateTime,
    name: String? = null,
) = editChatInviteLinkUnlimited(chat.id, previousLink, name , expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink,
    membersLimit: MembersLimit,
    name: String? = null,
    expiration: TelegramDate? = null
) = editChatInviteLinkWithLimitedMembers(chatId, previousLink.inviteLink, membersLimit, name, expiration)

suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    membersLimit: MembersLimit,
    name: String? = null,
    expiration: TelegramDate? = null,
) = editChatInviteLinkWithLimitedMembers(chat.id, previousLink, membersLimit, name, expiration)

suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink,
    membersLimit: MembersLimit,
    expiration: DateTime,
    name: String? = null,
) = editChatInviteLinkWithLimitedMembers(chatId, previousLink, membersLimit, name , expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkWithLimitedMembers(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    membersLimit: MembersLimit,
    expiration: DateTime,
    name: String? = null,
) = editChatInviteLinkWithLimitedMembers(chat.id, previousLink, membersLimit, name , expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink,
    name: String? = null,
    expiration: TelegramDate? = null
) = editChatInviteLinkWithJoinRequest(chatId, previousLink.inviteLink, name, expiration)

suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    name: String? = null,
    expiration: TelegramDate? = null,
) = editChatInviteLinkWithJoinRequest(chat.id, previousLink, name, expiration)

suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink,
    expiration: DateTime,
    name: String? = null,
) = editChatInviteLinkWithJoinRequest(chatId, previousLink, name , expiration.toTelegramDate())

suspend fun TelegramBot.editChatInviteLinkWithJoinRequest(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    expiration: DateTime,
    name: String? = null,
) = editChatInviteLinkWithJoinRequest(chat.id, previousLink, name , expiration.toTelegramDate())
