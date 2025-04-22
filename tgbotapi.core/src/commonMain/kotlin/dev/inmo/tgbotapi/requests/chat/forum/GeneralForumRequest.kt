package dev.inmo.tgbotapi.requests.chat.forum

import dev.inmo.tgbotapi.abstracts.types.ChatRequest

sealed interface GeneralForumRequest<T : Any> : ForumRequest<T>, ChatRequest
