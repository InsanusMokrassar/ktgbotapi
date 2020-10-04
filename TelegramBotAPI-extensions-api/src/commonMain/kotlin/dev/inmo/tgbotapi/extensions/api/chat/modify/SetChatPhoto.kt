package dev.inmo.tgbotapi.extensions.api.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify.SetChatPhoto
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun TelegramBot.setChatPhoto(
    chatId: ChatIdentifier,
    photo: MultipartFile
) = execute(SetChatPhoto(chatId, photo))

suspend fun TelegramBot.setChatPhoto(
    chat: PublicChat,
    photo: MultipartFile
) = setChatPhoto(chat.id, photo)
