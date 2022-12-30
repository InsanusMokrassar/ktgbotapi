package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.forum.EditForumTopic
import dev.inmo.tgbotapi.requests.chat.forum.EditGeneralForumTopic
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.CustomEmojiId
import dev.inmo.tgbotapi.types.ForumTopic
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.chat.Chat

suspend fun TelegramBot.editGeneralForumTopic(
    chatId: ChatIdentifier,
    name: String
) = execute(
    EditGeneralForumTopic(
        chatId,
        name
    )
)

suspend fun TelegramBot.editGeneralForumTopic(
    chat: Chat,
    name: String
) = editGeneralForumTopic(chat.id, name)

suspend fun TelegramBot.editGeneralForumTopic(
    chatIdentifier: ChatIdentifier,
    forumTopic: ForumTopic,
) = editGeneralForumTopic(chatIdentifier, forumTopic.name)
