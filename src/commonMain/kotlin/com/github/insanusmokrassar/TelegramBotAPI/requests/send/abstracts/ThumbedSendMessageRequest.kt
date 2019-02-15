package com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts

interface ThumbedSendMessageRequest<T: Any>: SendMessageRequest<T> {
    val thumb: String?
}