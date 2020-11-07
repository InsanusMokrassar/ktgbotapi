package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.abstracts.Chat

interface WithSenderChatMessage {
    val senderChat: Chat
}