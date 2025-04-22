package dev.inmo.tgbotapi.extensions.api.chat.invite_links

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.invite_links.RevokeChatInviteLink
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.ChatInviteLink
import dev.inmo.tgbotapi.types.SecondaryChatInviteLink
import dev.inmo.tgbotapi.types.chat.PublicChat

public suspend fun TelegramBot.revokeChatInviteLink(
    chatId: ChatIdentifier,
    previousLink: String,
): SecondaryChatInviteLink = execute(RevokeChatInviteLink(chatId, previousLink))

public suspend fun TelegramBot.revokeChatInviteLink(
    chat: PublicChat,
    previousLink: String,
): SecondaryChatInviteLink = revokeChatInviteLink(chat.id, previousLink)

public suspend fun TelegramBot.revokeChatInviteLink(
    chatId: ChatIdentifier,
    previousLink: ChatInviteLink,
): SecondaryChatInviteLink = revokeChatInviteLink(chatId, previousLink.inviteLink)

public suspend fun TelegramBot.revokeChatInviteLink(
    chat: PublicChat,
    previousLink: ChatInviteLink,
): SecondaryChatInviteLink = revokeChatInviteLink(chat, previousLink.inviteLink)
