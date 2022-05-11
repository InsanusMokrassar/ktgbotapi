package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.types.files.PhotoSize
import dev.inmo.tgbotapi.types.files.TelegramMediaFile

sealed interface ThumbedMediaFile : TelegramMediaFile {
    val thumb: PhotoSize?
}
