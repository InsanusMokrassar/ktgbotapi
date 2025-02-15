package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.InputFile

interface CoveredSendMessageRequest<T: Any>: SendMessageRequest<T> {
    val cover: InputFile?
}
