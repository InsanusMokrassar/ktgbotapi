package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.forum.UnpinAllForumTopicMessages
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.ForumTopic
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.chat.Chat

suspend fun TelegramBot.unpinAllForumTopicMessages(
    chatId: ChatIdentifier,
    messageThreadId: MessageThreadId
) = execute(
    UnpinAllForumTopicMessages(
        chatId,
        messageThreadId
    )
)

suspend fun TelegramBot.unpinAllForumTopicMessages(
    chat: Chat,
    messageThreadId: MessageThreadId
) = unpinAllForumTopicMessages(chat.id, messageThreadId)

suspend fun TelegramBot.unpinAllForumTopicMessages(
    chat: Chat,
    forumTopic: ForumTopic
) = unpinAllForumTopicMessages(chat.id, forumTopic.messageThreadId)
