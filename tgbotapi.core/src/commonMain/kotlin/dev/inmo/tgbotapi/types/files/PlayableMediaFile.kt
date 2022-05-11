package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.types.files.TelegramMediaFile

sealed interface PlayableMediaFile : TelegramMediaFile {
    val duration: Long?
}
