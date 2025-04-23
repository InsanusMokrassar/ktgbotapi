package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.forum.HideGeneralForumTopic
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.Chat

public suspend fun TelegramBot.hideGeneralForumTopic(chatId: ChatIdentifier): Boolean = execute(
    HideGeneralForumTopic(chatId),
)

public suspend fun TelegramBot.hideGeneralForumTopic(chat: Chat): Boolean = hideGeneralForumTopic(chat.id)
