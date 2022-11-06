package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.forum.ReopenForumTopic
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.ForumTopic
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.chat.Chat

suspend fun TelegramBot.reopenForumTopic(
    chatId: ChatIdentifier,
    messageThreadId: MessageThreadId
) = execute(
    ReopenForumTopic(
        chatId,
        messageThreadId
    )
)

suspend fun TelegramBot.reopenForumTopic(
    chat: Chat,
    messageThreadId: MessageThreadId
) = reopenForumTopic(chat.id, messageThreadId)

suspend fun TelegramBot.reopenForumTopic(
    chat: Chat,
    forumTopic: ForumTopic
) = reopenForumTopic(chat.id, forumTopic.messageThreadId)
