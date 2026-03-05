package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.forum.EditForumTopic
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.CustomEmojiId
import dev.inmo.tgbotapi.types.ForumTopic
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.chat.Chat

public suspend fun TelegramBot.editForumTopic(
    chatId: ChatIdentifier,
    messageThreadId: MessageThreadId,
    name: String? = null,
    iconEmojiId: CustomEmojiId? = null
): Unit = execute(
    EditForumTopic(
        chatId,
        messageThreadId,
        name,
        iconEmojiId
    )
)

public suspend fun TelegramBot.editForumTopic(
    chat: Chat,
    messageThreadId: MessageThreadId,
    name: String? = null,
    iconEmojiId: CustomEmojiId? = null
): Unit = editForumTopic(chat.id, messageThreadId, name, iconEmojiId)

public suspend fun TelegramBot.editForumTopic(
    chatIdentifier: ChatIdentifier,
    forumTopic: ForumTopic,
    iconEmojiId: CustomEmojiId? = forumTopic.iconEmojiId
): Unit = editForumTopic(chatIdentifier, forumTopic.messageThreadId, forumTopic.name, iconEmojiId)
