package dev.inmo.tgbotapi.requests.stickers.abstracts

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest

interface StickerAction<T : Any> : SimpleRequest<T> {
    val sticker: FileId
}
