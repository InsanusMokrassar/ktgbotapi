package dev.inmo.tgbotapi.types.files

sealed interface ThumbedMediaFile : TelegramMediaFile {
    val thumbnail: PhotoSize?

    @Deprecated("Renamed (in telegram bot api)", ReplaceWith("thumbnail"))
    val thumb: PhotoSize?
        get() = thumbnail
}
