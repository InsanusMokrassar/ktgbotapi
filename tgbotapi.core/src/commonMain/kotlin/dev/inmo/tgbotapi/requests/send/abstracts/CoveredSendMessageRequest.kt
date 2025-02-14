package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.requests.abstracts.FileId

interface CoveredSendMessageRequest<T: Any>: SendMessageRequest<T> {
    /**
     * Duration of media, usually in seconds
     */
    val cover: String?
}
