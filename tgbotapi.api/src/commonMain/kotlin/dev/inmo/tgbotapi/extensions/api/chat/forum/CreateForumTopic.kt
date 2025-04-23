package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.forum.CreateForumTopic
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.CustomEmojiId
import dev.inmo.tgbotapi.types.ForumTopic
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.utils.RGBColor

public suspend fun TelegramBot.createForumTopic(
    chatId: ChatIdentifier,
    name: String,
    color: RGBColor,
    iconEmojiId: CustomEmojiId? = null,
): ForumTopic = execute(
    CreateForumTopic(
        chatId,
        name,
        color,
        iconEmojiId,
    ),
)

public suspend fun TelegramBot.createForumTopic(
    chat: Chat,
    name: String,
    color: RGBColor,
    iconEmojiId: CustomEmojiId? = null,
): ForumTopic = createForumTopic(chat.id, name, color, iconEmojiId)
