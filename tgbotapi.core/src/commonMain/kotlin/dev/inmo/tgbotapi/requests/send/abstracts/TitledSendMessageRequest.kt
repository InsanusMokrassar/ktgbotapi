package dev.inmo.tgbotapi.requests.send.abstracts

interface TitledSendMessageRequest<T : Any> : SendMessageRequest<T> {
    val title: String?
}
