package dev.inmo.tgbotapi.requests.send.abstracts

interface PositionedSendMessageRequest<T: Any>: SendMessageRequest<T> {
    val latitude: Double
    val longitude: Double
}