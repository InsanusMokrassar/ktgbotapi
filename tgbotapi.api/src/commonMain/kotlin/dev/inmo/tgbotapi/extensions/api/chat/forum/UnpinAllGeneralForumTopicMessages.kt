package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.forum.UnpinAllForumTopicMessages
import dev.inmo.tgbotapi.requests.chat.forum.UnpinAllGeneralForumTopicMessages
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.ForumTopic
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.chat.Chat

suspend fun TelegramBot.unpinAllGeneralForumTopicMessages(
    chatId: ChatIdentifier
) = execute(
    UnpinAllGeneralForumTopicMessages(
        chatId
    )
)

suspend fun TelegramBot.unpinAllGeneralForumTopicMessages(
    chat: Chat
) = unpinAllGeneralForumTopicMessages(chat.id)
