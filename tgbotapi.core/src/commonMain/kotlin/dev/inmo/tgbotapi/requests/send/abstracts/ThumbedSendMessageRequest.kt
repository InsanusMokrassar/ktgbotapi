package dev.inmo.tgbotapi.requests.send.abstracts

interface ThumbedSendMessageRequest<T: Any>: SendMessageRequest<T> {
    val thumbnail: String?
}
