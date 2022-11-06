package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.requests.chat.forum.CreateForumTopic
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.utils.RGBColor
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

suspend fun TelegramBot.createForumTopic(
    chatId: ChatIdentifier,
    name: String,
    color: RGBColor,
    iconEmojiId: CustomEmojiId? = null
) = execute(
    CreateForumTopic(
        chatId,
        name,
        color,
        iconEmojiId
    )
)

suspend fun TelegramBot.createForumTopic(
    chat: Chat,
    name: String,
    color: RGBColor,
    iconEmojiId: CustomEmojiId? = null
) = createForumTopic(chat.id, name, color, iconEmojiId)
