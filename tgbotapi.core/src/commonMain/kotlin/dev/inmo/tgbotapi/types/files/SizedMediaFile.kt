package dev.inmo.tgbotapi.types.files

sealed interface SizedMediaFile : TelegramMediaFile {
    val width: Int
    val height: Int
}
