package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.UnpinChatMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.abstracts.PublicChat

suspend fun TelegramBot.unpinChatMessage(
    chatId: ChatIdentifier
) = execute(UnpinChatMessage(chatId))

suspend fun TelegramBot.unpinChatMessage(
    chat: PublicChat
) = unpinChatMessage(chat.id)
