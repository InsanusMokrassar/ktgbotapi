package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.UnpinAllChatMessages
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.Chat

public suspend fun TelegramBot.unpinAllChatMessages(chatId: ChatIdentifier): Boolean = execute(UnpinAllChatMessages(chatId))

public suspend fun TelegramBot.unpinAllChatMessages(chat: Chat): Boolean = unpinAllChatMessages(chat.id)
