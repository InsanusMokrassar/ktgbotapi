package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.types.ParseMode.ParseMode

interface TextableSendMessageRequest<T: Any>: SendMessageRequest<T> {
    val text: String?
    val parseMode: ParseMode?
}