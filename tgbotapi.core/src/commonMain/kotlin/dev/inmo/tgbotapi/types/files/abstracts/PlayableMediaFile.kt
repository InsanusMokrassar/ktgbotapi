package dev.inmo.tgbotapi.types.files.abstracts

interface PlayableMediaFile : TelegramMediaFile {
    val duration: Long?
}
