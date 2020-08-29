package com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts

interface TitledSendMessageRequest<T: Any>: SendMessageRequest<T> {
    val title: String?
}
