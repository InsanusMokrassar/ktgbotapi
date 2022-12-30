package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.forum.ReopenForumTopic
import dev.inmo.tgbotapi.requests.chat.forum.ReopenGeneralForumTopic
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.ForumTopic
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.chat.Chat

suspend fun TelegramBot.reopenGeneralForumTopic(
    chatId: ChatIdentifier
) = execute(
    ReopenGeneralForumTopic(chatId)
)

suspend fun TelegramBot.reopenGeneralForumTopic(
    chat: Chat
) = reopenGeneralForumTopic(chat.id)
