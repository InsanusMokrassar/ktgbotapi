package dev.inmo.tgbotapi.requests.chat.abstracts

import dev.inmo.tgbotapi.CommonAbstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.UserId

interface ChatMemberRequest<T: Any> : ChatRequest, SimpleRequest<T> {
    val userId: UserId
}