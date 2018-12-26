package com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode

interface TextableSendMessageRequest<T: Any>: SendMessageRequest<T> {
    val text: String?
    val parseMode: ParseMode?
}