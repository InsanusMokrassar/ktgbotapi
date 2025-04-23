package dev.inmo.tgbotapi.extensions.api.chat.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.get.GetChatAdministrators
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.member.AdministratorChatMember

public suspend fun TelegramBot.getChatAdministrators(chatId: ChatIdentifier): List<AdministratorChatMember> = execute(GetChatAdministrators(chatId))

public suspend fun TelegramBot.getChatAdministrators(chat: PublicChat): List<AdministratorChatMember> = getChatAdministrators(chat.id)
