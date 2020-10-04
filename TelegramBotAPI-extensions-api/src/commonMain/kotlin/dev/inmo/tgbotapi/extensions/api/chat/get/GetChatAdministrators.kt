package dev.inmo.tgbotapi.extensions.api.chat.get

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.get.GetChatAdministrators
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun TelegramBot.getChatAdministrators(
    chatId: ChatIdentifier
) = execute(GetChatAdministrators(chatId))

suspend fun TelegramBot.getChatAdministrators(
    chat: PublicChat
) = getChatAdministrators(chat.id)
