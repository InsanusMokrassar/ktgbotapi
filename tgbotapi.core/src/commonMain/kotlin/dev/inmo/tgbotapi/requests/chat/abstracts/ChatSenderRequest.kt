package dev.inmo.tgbotapi.requests.chat.abstracts

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.IdChatIdentifier

interface ChatSenderRequest : ChatRequest, SimpleRequest<Boolean> {
    val senderChatId: IdChatIdentifier
}
