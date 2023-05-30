package dev.inmo.tgbotapi.types.files

sealed interface ThumbedMediaFile : TelegramMediaFile {
    val thumbnail: PhotoSize?
}
