package dev.inmo.tgbotapi.types.files

sealed interface PlayableMediaFile : TelegramMediaFile {
    val duration: Long?
}
