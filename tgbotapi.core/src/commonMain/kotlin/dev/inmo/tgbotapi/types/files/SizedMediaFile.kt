package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.types.files.TelegramMediaFile

sealed interface SizedMediaFile : TelegramMediaFile {
    val width: Int
    val height: Int
}
