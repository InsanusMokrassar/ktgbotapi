package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.chat.modify.SetChatPhoto
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.abstracts.PublicChat

suspend fun TelegramBot.setChatPhoto(
    chatId: ChatIdentifier,
    photo: MultipartFile
) = execute(SetChatPhoto(chatId, photo))

suspend fun TelegramBot.setChatPhoto(
    chat: PublicChat,
    photo: MultipartFile
) = setChatPhoto(chat.id, photo)
