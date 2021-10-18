package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.DeleteChatPhoto
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.abstracts.PublicChat

suspend fun TelegramBot.deleteChatPhoto(
    chatId: ChatIdentifier
) = execute(DeleteChatPhoto(chatId))

suspend fun TelegramBot.deleteChatPhoto(
    chat: PublicChat
) = deleteChatPhoto(chat.id)
