package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.forum.DeleteForumTopic
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.ForumTopic
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.chat.Chat

suspend fun TelegramBot.deleteForumTopic(
    chatId: ChatIdentifier,
    messageThreadId: MessageThreadId
) = execute(
    DeleteForumTopic(
        chatId,
        messageThreadId
    )
)

suspend fun TelegramBot.deleteForumTopic(
    chat: Chat,
    messageThreadId: MessageThreadId
) = deleteForumTopic(chat.id, messageThreadId)

suspend fun TelegramBot.deleteForumTopic(
    chat: Chat,
    forumTopic: ForumTopic
) = deleteForumTopic(chat.id, forumTopic.messageThreadId)
