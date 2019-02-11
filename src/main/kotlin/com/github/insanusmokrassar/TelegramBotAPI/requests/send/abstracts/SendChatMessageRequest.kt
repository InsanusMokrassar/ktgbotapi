package com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest

interface SendChatMessageRequest<T: Any> : SimpleRequest<T>, ChatRequest