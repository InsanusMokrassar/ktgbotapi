package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.DeleteChatPhoto
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.PublicChat

public suspend fun TelegramBot.deleteChatPhoto(
    chatId: ChatIdentifier
): Unit = execute(DeleteChatPhoto(chatId))

public suspend fun TelegramBot.deleteChatPhoto(
    chat: PublicChat
): Unit = deleteChatPhoto(chat.id)
