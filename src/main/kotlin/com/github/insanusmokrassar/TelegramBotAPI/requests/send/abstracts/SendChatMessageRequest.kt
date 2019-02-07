package com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ChatRequest

interface SendChatMessageRequest<T: Any> : SimpleRequest<T>, ChatRequest