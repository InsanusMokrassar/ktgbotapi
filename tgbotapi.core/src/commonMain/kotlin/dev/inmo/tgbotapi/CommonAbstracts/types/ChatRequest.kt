package dev.inmo.tgbotapi.CommonAbstracts.types

import dev.inmo.tgbotapi.types.ChatIdentifier

interface ChatRequest {
    val chatId: ChatIdentifier
}