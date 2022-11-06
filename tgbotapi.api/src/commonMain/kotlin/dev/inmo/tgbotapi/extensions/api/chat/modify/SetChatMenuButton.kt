package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.SetChatMenuButton
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.MenuButton
import dev.inmo.tgbotapi.types.chat.PrivateChat

suspend fun TelegramBot.setChatMenuButton(
    chatId: ChatId,
    menuButton: MenuButton
) = execute(SetChatMenuButton(chatId, menuButton))

suspend fun TelegramBot.setChatMenuButton(
    chat: PrivateChat,
    menuButton: MenuButton
) = setChatMenuButton(chat.id, menuButton)
