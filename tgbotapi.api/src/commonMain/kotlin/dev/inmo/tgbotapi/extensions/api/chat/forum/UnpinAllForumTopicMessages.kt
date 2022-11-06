package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.requests.chat.forum.CloseForumTopic
import dev.inmo.tgbotapi.requests.chat.forum.CreateForumTopic
import dev.inmo.tgbotapi.requests.chat.forum.UnpinAllForumTopicMessages
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.utils.RGBColor
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

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
