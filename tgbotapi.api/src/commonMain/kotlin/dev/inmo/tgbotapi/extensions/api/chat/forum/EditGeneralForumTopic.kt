package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.forum.EditGeneralForumTopic
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.ForumTopic
import dev.inmo.tgbotapi.types.chat.Chat

public suspend fun TelegramBot.editGeneralForumTopic(
    chatId: ChatIdentifier,
    name: String,
): Boolean =
    execute(
        EditGeneralForumTopic(
            chatId,
            name,
        ),
    )

public suspend fun TelegramBot.editGeneralForumTopic(
    chat: Chat,
    name: String,
): Boolean = editGeneralForumTopic(chat.id, name)

public suspend fun TelegramBot.editGeneralForumTopic(
    chatIdentifier: ChatIdentifier,
    forumTopic: ForumTopic,
): Boolean = editGeneralForumTopic(chatIdentifier, forumTopic.name)
