package com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts

interface SizedSendMessageRequest<T: Any> : SendMessageRequest<T> {
    val width: Int?
    val height: Int?
}