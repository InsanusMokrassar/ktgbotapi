package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.chat.Chat

data class UnknownChatType(
    override val id: ChatId,
    val raw: String
) : Chat
