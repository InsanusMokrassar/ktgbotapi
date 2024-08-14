package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.PreviewChat

interface WithSenderChatMessage {
    val senderChat: PreviewChat
}
