package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.forum.CloseGeneralForumTopic
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.Chat

public suspend fun TelegramBot.closeGeneralForumTopic(chatId: ChatIdentifier): Boolean = execute(
    CloseGeneralForumTopic(chatId),
)

public suspend fun TelegramBot.closeGeneralForumTopic(chat: Chat): Boolean = closeGeneralForumTopic(chat.id)
