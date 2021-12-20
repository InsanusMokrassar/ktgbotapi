package dev.inmo.tgbotapi.requests.chat.abstracts

import dev.inmo.tgbotapi.CommonAbstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.UserId

interface ChatSenderRequest : ChatRequest, SimpleRequest<Boolean> {
    val senderChatId: ChatId
}
