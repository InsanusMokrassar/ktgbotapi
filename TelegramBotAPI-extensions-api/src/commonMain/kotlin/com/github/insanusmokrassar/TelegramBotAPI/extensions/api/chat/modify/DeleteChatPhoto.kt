package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify.DeleteChatPhoto
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun RequestsExecutor.deleteChatPhoto(
    chatId: ChatIdentifier
) = execute(DeleteChatPhoto(chatId))

suspend fun RequestsExecutor.deleteChatPhoto(
    chat: PublicChat
) = deleteChatPhoto(chat.id)
