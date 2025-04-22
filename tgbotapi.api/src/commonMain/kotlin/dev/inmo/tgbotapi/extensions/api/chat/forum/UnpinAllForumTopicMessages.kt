package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.forum.UnpinAllForumTopicMessages
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.ForumTopic
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.chat.Chat

public suspend fun TelegramBot.unpinAllForumTopicMessages(
    chatId: ChatIdentifier,
    messageThreadId: MessageThreadId,
): Boolean =
    execute(
        UnpinAllForumTopicMessages(
            chatId,
            messageThreadId,
        ),
    )

public suspend fun TelegramBot.unpinAllForumTopicMessages(
    chat: Chat,
    messageThreadId: MessageThreadId,
): Boolean = unpinAllForumTopicMessages(chat.id, messageThreadId)

public suspend fun TelegramBot.unpinAllForumTopicMessages(
    chat: Chat,
    forumTopic: ForumTopic,
): Boolean = unpinAllForumTopicMessages(chat.id, forumTopic.messageThreadId)
