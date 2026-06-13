package dev.inmo.tgbotapi.extensions.api.chat.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.get.GetChatAdministrators
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.member.AdministratorChatMember

public suspend fun TelegramBot.getChatAdministrators(
    chatId: ChatIdentifier,
    retrieveOtherBots: Boolean? = null
): List<AdministratorChatMember> = execute(GetChatAdministrators(chatId = chatId, retrieveOtherBots = retrieveOtherBots))

public suspend fun TelegramBot.getChatAdministrators(
    chat: PublicChat,
    retrieveOtherBots: Boolean? = null
): List<AdministratorChatMember> = getChatAdministrators(chat.id, retrieveOtherBots)
