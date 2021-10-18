package dev.inmo.tgbotapi.extensions.api.chat.invite_links

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.invite_links.CreateChatInviteLink
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.abstracts.PublicChat

suspend fun TelegramBot.createChatInviteLink(
    chatId: ChatIdentifier,
    expiration: TelegramDate? = null,
    membersLimit: MembersLimit? = null
) = execute(CreateChatInviteLink(chatId, expiration, membersLimit))

suspend fun TelegramBot.createChatInviteLink(
    chat: PublicChat,
    expiration: TelegramDate? = null,
    membersLimit: MembersLimit? = null
) = createChatInviteLink(chat.id, expiration, membersLimit)

suspend fun TelegramBot.createChatInviteLink(
    chatId: ChatIdentifier,
    expiration: DateTime,
    membersLimit: MembersLimit? = null
) = createChatInviteLink(chatId, expiration.toTelegramDate(), membersLimit)

suspend fun TelegramBot.createChatInviteLink(
    chat: PublicChat,
    expiration: DateTime,
    membersLimit: MembersLimit? = null
) = createChatInviteLink(chat.id, expiration.toTelegramDate(), membersLimit)
