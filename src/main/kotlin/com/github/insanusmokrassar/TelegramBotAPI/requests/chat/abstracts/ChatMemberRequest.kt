package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*

interface ChatMemberRequest<T: Any> : ChatRequest, SimpleRequest<T> {
    val userId: UserId
}