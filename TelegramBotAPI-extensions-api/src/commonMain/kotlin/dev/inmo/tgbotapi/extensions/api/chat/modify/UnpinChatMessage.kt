package dev.inmo.tgbotapi.extensions.api.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify.UnpinChatMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat

suspend fun TelegramBot.unpinChatMessage(
    chatId: ChatIdentifier
) = execute(UnpinChatMessage(chatId))

suspend fun TelegramBot.unpinChatMessage(
    chat: PublicChat
) = unpinChatMessage(chat.id)
