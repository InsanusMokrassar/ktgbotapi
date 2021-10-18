package dev.inmo.tgbotapi.extensions.api.chat.invite_links

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.invite_links.EditChatInviteLink
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.abstracts.PublicChat

suspend fun TelegramBot.editChatInviteLink(
    chatId: ChatIdentifier,
    previousLink: String,
    expiration: TelegramDate? = null,
    membersLimit: MembersLimit? = null
) = execute(EditChatInviteLink(chatId, previousLink, expiration, membersLimit))

suspend fun TelegramBot.editChatInviteLink(
    chat: PublicChat,
    previousLink: String,
    expiration: TelegramDate? = null,
    membersLimit: MembersLimit? = null
) = editChatInviteLink(chat.id, previousLink, expiration, membersLimit)

suspend fun TelegramBot.editChatInviteLink(
    chatId: ChatIdentifier,
    previousLink: String,
    expiration: DateTime,
    membersLimit: MembersLimit? = null
) = editChatInviteLink(chatId, previousLink, expiration.toTelegramDate(), membersLimit)

suspend fun TelegramBot.editChatInviteLink(
    chat: PublicChat,
    previousLink: String,
    expiration: DateTime,
    membersLimit: MembersLimit? = null
) = editChatInviteLink(chat.id, previousLink, expiration.toTelegramDate(), membersLimit)

suspend fun TelegramBot.editChatInviteLink(
    chat: ChatIdentifier,
    previousLink: ChatInviteLink,
    expiration: TelegramDate? = previousLink.expirationDateTime ?.toTelegramDate(),
    membersLimit: MembersLimit? = previousLink.membersLimit
) = editChatInviteLink(chat, previousLink.inviteLink, expiration, membersLimit)

suspend fun TelegramBot.editChatInviteLink(
    chat: ChatIdentifier,
    previousLink: ChatInviteLink,
    expiration: DateTime,
    membersLimit: MembersLimit? = previousLink.membersLimit
) = editChatInviteLink(chat, previousLink.inviteLink, expiration, membersLimit)

suspend fun TelegramBot.editChatInviteLink(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    expiration: TelegramDate? = previousLink.expirationDateTime ?.toTelegramDate(),
    membersLimit: MembersLimit? = previousLink.membersLimit
) = editChatInviteLink(chat, previousLink.inviteLink, expiration, membersLimit)

suspend fun TelegramBot.editChatInviteLink(
    chat: PublicChat,
    previousLink: ChatInviteLink,
    expiration: DateTime,
    membersLimit: MembersLimit? = previousLink.membersLimit
) = editChatInviteLink(chat, previousLink.inviteLink, expiration, membersLimit)
