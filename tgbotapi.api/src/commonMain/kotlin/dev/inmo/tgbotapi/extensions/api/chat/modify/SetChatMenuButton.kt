package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.abstracts.PrivateChat

suspend fun TelegramBot.setChatMenuButton(
    chatId: ChatId,
    menuButton: MenuButton
) = execute(SetChatMenuButton(chatId, menuButton))

suspend fun TelegramBot.setChatMenuButton(
    chat: PrivateChat,
    menuButton: MenuButton
) = setChatMenuButton(chat.id, menuButton)
