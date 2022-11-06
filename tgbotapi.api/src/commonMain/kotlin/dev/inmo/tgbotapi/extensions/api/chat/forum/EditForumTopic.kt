package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.requests.chat.forum.CreateForumTopic
import dev.inmo.tgbotapi.requests.chat.forum.EditForumTopic
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.utils.RGBColor
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

suspend fun TelegramBot.editForumTopic(
    chatId: ChatIdentifier,
    messageThreadId: MessageThreadId,
    name: String,
    iconEmojiId: CustomEmojiId
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
    name: String,
    iconEmojiId: CustomEmojiId
) = editForumTopic(chat.id, messageThreadId, name, iconEmojiId)

suspend fun TelegramBot.editForumTopic(
    chatIdentifier: ChatIdentifier,
    forumTopic: ForumTopic,
    iconEmojiId: CustomEmojiId = forumTopic.iconEmojiId ?: error("Icon emoji id in forum topic should be presented when edit forum topic basing on other forum topic object")
) = editForumTopic(chatIdentifier, forumTopic.messageThreadId, forumTopic.name, iconEmojiId)
