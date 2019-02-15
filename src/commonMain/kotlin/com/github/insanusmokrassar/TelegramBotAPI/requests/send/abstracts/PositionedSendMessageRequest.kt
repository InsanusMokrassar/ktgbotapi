package com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts

interface PositionedSendMessageRequest<T: Any>: SendMessageRequest<T> {
    val latitude: Double
    val longitude: Double
}