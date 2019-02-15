package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.UserId

interface ChatMemberRequest<T: Any> : ChatRequest, SimpleRequest<T> {
    val userId: UserId
}