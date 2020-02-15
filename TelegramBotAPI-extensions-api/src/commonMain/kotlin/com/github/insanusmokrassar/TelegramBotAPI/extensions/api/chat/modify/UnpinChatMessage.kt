package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify.UnpinChatMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun RequestsExecutor.unpinChatMessage(
    chatId: ChatIdentifier
) = execute(UnpinChatMessage(chatId))

suspend fun RequestsExecutor.unpinChatMessage(
    chat: PublicChat
) = unpinChatMessage(chat.id)
