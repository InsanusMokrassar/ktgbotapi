package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.forum.CloseForumTopic
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.ForumTopic
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.chat.Chat

public suspend fun TelegramBot.closeForumTopic(
    chatId: ChatIdentifier,
    messageThreadId: MessageThreadId
): Unit = execute(
    CloseForumTopic(
        chatId,
        messageThreadId
    )
)

public suspend fun TelegramBot.closeForumTopic(
    chat: Chat,
    messageThreadId: MessageThreadId
): Unit = closeForumTopic(chat.id, messageThreadId)

public suspend fun TelegramBot.closeForumTopic(
    chat: Chat,
    forumTopic: ForumTopic
): Unit = closeForumTopic(chat.id, forumTopic.messageThreadId)
