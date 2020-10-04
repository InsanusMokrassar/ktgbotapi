package dev.inmo.tgbotapi.requests.send.abstracts

interface DuratedSendMessageRequest<T: Any>: SendMessageRequest<T> {
    /**
     * Duration of media, usually in seconds
     */
    val duration: Long?
}