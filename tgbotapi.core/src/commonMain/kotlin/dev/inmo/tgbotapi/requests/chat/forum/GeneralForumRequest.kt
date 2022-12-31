package dev.inmo.tgbotapi.requests.chat.forum

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest

sealed interface GeneralForumRequest<T : Any> : ForumRequest<T>, ChatRequest
