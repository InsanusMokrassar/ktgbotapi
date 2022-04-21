package dev.inmo.tgbotapi.extensions.api.chat.invite_links

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.invite_links.RevokeChatInviteLink
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.ChatInviteLink
import dev.inmo.tgbotapi.types.chat.PublicChat

suspend fun TelegramBot.revokeChatInviteLink(
    chatId: ChatIdentifier,
    previousLink: String
) = execute(RevokeChatInviteLink(chatId, previousLink))

suspend fun TelegramBot.revokeChatInviteLink(
    chat: PublicChat,
    previousLink: String
) = revokeChatInviteLink(chat.id, previousLink)

suspend fun TelegramBot.revokeChatInviteLink(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink
) = revokeChatInviteLink(chatId, previousLink.inviteLink)

suspend fun TelegramBot.revokeChatInviteLink(
    chat: PublicChat,
    previousLink: ChatInviteLink
) = revokeChatInviteLink(chat, previousLink.inviteLink)
