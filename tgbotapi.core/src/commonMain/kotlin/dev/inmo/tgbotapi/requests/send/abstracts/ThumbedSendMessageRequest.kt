package dev.inmo.tgbotapi.requests.send.abstracts

interface ThumbedSendMessageRequest<T: Any>: SendMessageRequest<T> {
    val thumbnail: String?

    @Deprecated("Renamed in telegram bot api", ReplaceWith("thumbnail"))
    val thumb: String?
        get() = thumbnail
}
