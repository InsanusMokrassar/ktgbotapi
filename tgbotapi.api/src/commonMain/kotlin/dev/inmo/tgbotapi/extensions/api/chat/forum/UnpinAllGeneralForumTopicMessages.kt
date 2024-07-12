package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.forum.UnpinAllForumTopicMessages
import dev.inmo.tgbotapi.requests.chat.forum.UnpinAllGeneralForumTopicMessages
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.ForumTopic
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.chat.Chat

public suspend fun TelegramBot.unpinAllGeneralForumTopicMessages(
    chatId: ChatIdentifier
): Boolean = execute(
    UnpinAllGeneralForumTopicMessages(
        chatId
    )
)

public suspend fun TelegramBot.unpinAllGeneralForumTopicMessages(
    chat: Chat
): Boolean = unpinAllGeneralForumTopicMessages(chat.id)
