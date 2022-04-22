package dev.inmo.tgbotapi.types.media

sealed interface DuratedTelegramMedia : TelegramMedia {
    val duration: Long?
}
