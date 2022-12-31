package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.forum.EditForumTopic
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.CustomEmojiId
import dev.inmo.tgbotapi.types.ForumTopic
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.chat.Chat

suspend fun TelegramBot.editForumTopic(
    chatId: ChatIdentifier,
    messageThreadId: MessageThreadId,
    name: String? = null,
    iconEmojiId: CustomEmojiId? = null
) = execute(
    EditForumTopic(
        chatId,
        messageThreadId,
        name,
        iconEmojiId
    )
)

suspend fun TelegramBot.editForumTopic(
    chat: Chat,
    messageThreadId: MessageThreadId,
    name: String? = null,
    iconEmojiId: CustomEmojiId? = null
) = editForumTopic(chat.id, messageThreadId, name, iconEmojiId)

suspend fun TelegramBot.editForumTopic(
    chatIdentifier: ChatIdentifier,
    forumTopic: ForumTopic,
    iconEmojiId: CustomEmojiId? = forumTopic.iconEmojiId
) = editForumTopic(chatIdentifier, forumTopic.messageThreadId, forumTopic.name, iconEmojiId)
