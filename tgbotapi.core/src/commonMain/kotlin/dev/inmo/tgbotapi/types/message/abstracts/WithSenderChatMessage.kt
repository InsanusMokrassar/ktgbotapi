package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.Chat

interface WithSenderChatMessage {
    val senderChat: Chat
}
