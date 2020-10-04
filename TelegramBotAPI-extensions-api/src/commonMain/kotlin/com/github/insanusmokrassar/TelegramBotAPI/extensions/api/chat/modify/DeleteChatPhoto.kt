package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify.DeleteChatPhoto
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun TelegramBot.deleteChatPhoto(
    chatId: ChatIdentifier
) = execute(DeleteChatPhoto(chatId))

suspend fun TelegramBot.deleteChatPhoto(
    chat: PublicChat
) = deleteChatPhoto(chat.id)
