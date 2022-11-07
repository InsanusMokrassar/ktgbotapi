package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.MessageThreadId

interface SendChatMessageRequest<T: Any> : SimpleRequest<T>, ChatRequest
