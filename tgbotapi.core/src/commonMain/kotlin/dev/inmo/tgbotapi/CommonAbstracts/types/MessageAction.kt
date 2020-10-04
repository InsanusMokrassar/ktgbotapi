package dev.inmo.tgbotapi.CommonAbstracts.types

import dev.inmo.tgbotapi.types.MessageIdentifier

interface MessageAction: ChatRequest {
    val messageId: MessageIdentifier
}