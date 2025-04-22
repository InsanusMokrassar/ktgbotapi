package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.forum.DeleteForumTopic
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.ForumTopic
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.chat.Chat

public suspend fun TelegramBot.deleteForumTopic(
    chatId: ChatIdentifier,
    messageThreadId: MessageThreadId,
): Boolean =
    execute(
        DeleteForumTopic(
            chatId,
            messageThreadId,
        ),
    )

public suspend fun TelegramBot.deleteForumTopic(
    chatId: ChatIdentifier,
    forumTopic: ForumTopic,
): Boolean = deleteForumTopic(chatId, forumTopic.messageThreadId)

public suspend fun TelegramBot.deleteForumTopic(
    chat: Chat,
    messageThreadId: MessageThreadId,
): Boolean = deleteForumTopic(chat.id, messageThreadId)

public suspend fun TelegramBot.deleteForumTopic(
    chat: Chat,
    forumTopic: ForumTopic,
): Boolean = deleteForumTopic(chat.id, forumTopic.messageThreadId)
