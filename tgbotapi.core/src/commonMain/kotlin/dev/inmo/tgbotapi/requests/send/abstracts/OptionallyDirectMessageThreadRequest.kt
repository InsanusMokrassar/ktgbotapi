package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.types.DirectMessageThreadId

interface OptionallyDirectMessageThreadRequest {
    val directMessageThreadId: DirectMessageThreadId?
}
