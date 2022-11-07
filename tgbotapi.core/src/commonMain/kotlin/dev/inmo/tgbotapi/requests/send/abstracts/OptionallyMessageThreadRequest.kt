package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.types.MessageThreadId

interface OptionallyMessageThreadRequest {
    val threadId: MessageThreadId?
}
