package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.forum.ReopenForumTopic
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.ForumTopic
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.chat.Chat

public suspend fun TelegramBot.reopenForumTopic(
    chatId: ChatIdentifier,
    messageThreadId: MessageThreadId,
): Boolean =
    execute(
        ReopenForumTopic(
            chatId,
            messageThreadId,
        ),
    )

public suspend fun TelegramBot.reopenForumTopic(
    chat: Chat,
    messageThreadId: MessageThreadId,
): Boolean = reopenForumTopic(chat.id, messageThreadId)

public suspend fun TelegramBot.reopenForumTopic(
    chat: Chat,
    forumTopic: ForumTopic,
): Boolean = reopenForumTopic(chat.id, forumTopic.messageThreadId)
