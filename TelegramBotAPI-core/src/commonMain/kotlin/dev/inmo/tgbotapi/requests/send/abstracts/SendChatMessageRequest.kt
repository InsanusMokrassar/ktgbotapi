package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.CommonAbstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest

interface SendChatMessageRequest<T: Any> : SimpleRequest<T>, ChatRequest