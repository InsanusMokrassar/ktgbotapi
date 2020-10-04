package dev.inmo.tgbotapi.types.files.abstracts

import dev.inmo.tgbotapi.types.files.PhotoSize

interface ThumbedMediaFile : TelegramMediaFile {
    val thumb: PhotoSize?
}