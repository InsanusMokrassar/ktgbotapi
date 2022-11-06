package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.requests.chat.forum.CloseForumTopic
import dev.inmo.tgbotapi.requests.chat.forum.CreateForumTopic
import dev.inmo.tgbotapi.requests.chat.forum.ReopenForumTopic
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.utils.RGBColor
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

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
