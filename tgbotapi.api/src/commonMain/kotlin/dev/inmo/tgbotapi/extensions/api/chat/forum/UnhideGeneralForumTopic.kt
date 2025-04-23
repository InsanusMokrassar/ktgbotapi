package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.forum.UnhideGeneralForumTopic
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.Chat

public suspend fun TelegramBot.unhideGeneralForumTopic(chatId: ChatIdentifier): Boolean = execute(
    UnhideGeneralForumTopic(chatId),
)

public suspend fun TelegramBot.unhideGeneralForumTopic(chat: Chat): Boolean = unhideGeneralForumTopic(chat.id)
